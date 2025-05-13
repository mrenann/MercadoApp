package com.mrenann.mercadolivre.detailsScreen.data.usecase

import com.mrenann.mercadolivre.core.domain.model.DetailedProduct
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.detailsScreen.domain.repository.DetailsRepository
import com.mrenann.mercadolivre.detailsScreen.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetProductUseCaseImpl(
    private val repository: DetailsRepository
) : GetProductUseCase {
    override suspend fun invoke(id: String): Flow<Resource<DetailedProduct>> {
        val itemFlow = repository.getItem(id)
        val categoryFlow = repository.getItemCategory(id)
        val descriptionFlow = repository.getItemDescription(id)

        return combine(
            itemFlow,
            categoryFlow,
            descriptionFlow
        ) { itemRes, categoryRes, descRes ->

            val item = if (itemRes is Resource.Success) itemRes.data else null
            val category = if (categoryRes is Resource.Success) categoryRes.data else null
            val description = if (descRes is Resource.Success) descRes.data else null

            val errorMessages = listOfNotNull(
                (itemRes as? Resource.Error)?.message,
                (categoryRes as? Resource.Error)?.message,
                (descRes as? Resource.Error)?.message
            )
            when {
                itemRes is Resource.Loading || categoryRes is Resource.Loading || descRes is Resource.Loading -> {
                    Resource.Loading
                }

                errorMessages.isNotEmpty() -> {
                    Resource.Success(
                        DetailedProduct(
                            item = item,
                            category = category,
                            description = description,
                            errors = errorMessages
                        )
                    )
                }

                else -> {
                    Resource.Success(
                        DetailedProduct(
                            item = item,
                            category = category,
                            description = description
                        )
                    )
                }
            }
        }
    }
}
