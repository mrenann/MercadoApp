package com.mrenann.mercadolivre.detailsScreen.data.usecase

import com.mrenann.mercadolivre.core.domain.model.DetailedProduct
import com.mrenann.mercadolivre.detailsScreen.domain.repository.DetailsRepository
import com.mrenann.mercadolivre.detailsScreen.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetProductUseCaseImpl(
    private val repository: DetailsRepository
) : GetProductUseCase {
    override suspend fun invoke(id: String): Flow<DetailedProduct> {
        val itemFlow = repository.getItem(id)
        val categoryFlow = repository.getItemCategory(id)
        val descriptionFlow = repository.getItemDescription(id)

        return combine(
            itemFlow,
            categoryFlow,
            descriptionFlow
        ) { itemResource, categoryResource, descriptionResource ->
            DetailedProduct(
                item = itemResource,
                category = categoryResource,
                description = descriptionResource
            )
        }
    }
}