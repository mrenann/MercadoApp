package com.mrenann.mercadolivre.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.mrenann.mercadolivre.core.data.remote.model.AvailableFilter
import com.mrenann.mercadolivre.core.data.remote.model.AvailableSort
import com.mrenann.mercadolivre.core.data.remote.model.Filter
import com.mrenann.mercadolivre.core.data.remote.model.Paging
import com.mrenann.mercadolivre.core.data.remote.model.PdpTracking
import com.mrenann.mercadolivre.core.data.remote.model.RankingIntrospection
import com.mrenann.mercadolivre.core.data.remote.model.Result
import com.mrenann.mercadolivre.core.data.remote.model.Sort

data class SearchQueryResponse(
    @SerializedName("available_filters")
    val availableFilters: List<AvailableFilter>? = listOf(),
    @SerializedName("available_sorts")
    val availableSorts: List<AvailableSort>? = listOf(),
    @SerializedName("country_default_time_zone")
    val countryDefaultTimeZone: String? = "",
    @SerializedName("filters")
    val filters: List<Filter>? = listOf(),
    @SerializedName("paging")
    val paging: Paging? = Paging(),
    @SerializedName("pdp_tracking")
    val pdpTracking: PdpTracking? = PdpTracking(),
    @SerializedName("query")
    val query: String? = "",
    @SerializedName("ranking_introspection")
    val rankingIntrospection: RankingIntrospection? = RankingIntrospection(),
    @SerializedName("results")
    val results: List<Result>? = listOf(),
    @SerializedName("site_id")
    val siteId: String? = "",
    @SerializedName("sort")
    val sort: Sort? = Sort(),
    @SerializedName("user_context")
    val userContext: Any? = Any()
)