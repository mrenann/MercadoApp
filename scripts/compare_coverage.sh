#!/bin/bash
set -e

# Caminhos dos relatórios
BRANCH_REPORT="$1"
MAIN_REPORT="$2"

# Função para extrair métricas do XML
extract_metrics() {
    local report_file="$1"
    local metric_type="$2"

    # Extrai valores usando grep e awk
    local covered=$(grep -A 1 "<counter type=\"$metric_type\"" "$report_file" | grep -Eo 'covered="[0-9]+"' | awk -F\" '{print $2}')
    local missed=$(grep -A 1 "<counter type=\"$metric_type\"" "$report_file" | grep -Eo 'missed="[0-9]+"' | awk -F\" '{print $2}')

    # Calcula cobertura
    local total=$((covered + missed))
    local coverage=0
    if [ $total -gt 0 ]; then
        coverage=$(awk "BEGIN {printf \"%.2f\", ($covered / $total) * 100}")
    fi

    echo $coverage
}

# Extrai métricas da branch
BRANCH_LINE=$(extract_metrics "$BRANCH_REPORT" "LINE")
BRANCH_BRANCH=$(extract_metrics "$BRANCH_REPORT" "BRANCH")

# Extrai métricas da main
MAIN_LINE=$(extract_metrics "$MAIN_REPORT" "LINE")
MAIN_BRANCH=$(extract_metrics "$MAIN_REPORT" "BRANCH")

# Calcula diferenças
LINE_DIFF=$(awk "BEGIN {printf \"%.2f\", $BRANCH_LINE - $MAIN_LINE}")
BRANCH_DIFF=$(awk "BEGIN {printf \"%.2f\", $BRANCH_BRANCH - $MAIN_BRANCH}")

# Gera relatório Markdown
echo "## 📊 Comparação de Cobertura

| Métrica         | Branch       | Main        | Diferença  |
|-----------------|--------------|-------------|------------|
| Linhas         | ${BRANCH_LINE}% | ${MAIN_LINE}% | ${LINE_DIFF:+$LINE_DIFF%} |
| Ramos (Branches) | ${BRANCH_BRANCH}% | ${MAIN_BRANCH}% | ${BRANCH_DIFF:+$BRANCH_DIFF%} |

_Última atualização: $(date +'%d/%m/%Y %H:%M')_" > report.md