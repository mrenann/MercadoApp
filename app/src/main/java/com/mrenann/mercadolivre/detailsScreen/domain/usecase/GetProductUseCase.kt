package com.mrenann.mercadolivre.detailsScreen.domain.usecase

import com.mrenann.mercadolivre.core.domain.model.DetailedProduct
import com.mrenann.mercadolivre.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetProductUseCase {
    suspend operator fun invoke(id: String): Flow<Resource<DetailedProduct>>
}