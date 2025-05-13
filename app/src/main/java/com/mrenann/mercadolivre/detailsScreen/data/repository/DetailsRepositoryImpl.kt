package com.mrenann.mercadolivre.detailsScreen.data.repository

import com.mrenann.mercadolivre.core.data.remote.mappers.toDomain
import com.mrenann.mercadolivre.core.domain.model.Category
import com.mrenann.mercadolivre.core.domain.model.ProductDetails
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.detailsScreen.domain.repository.DetailsRepository
import com.mrenann.mercadolivre.detailsScreen.domain.source.DetailsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailsRepositoryImpl(
    private val dataSource: DetailsDataSource
) : DetailsRepository {

    override fun getItem(id: String): Flow<Resource<ProductDetails>> {
        return dataSource.getItem(id).map { resource ->
            resource.map { response ->
                response.toDomain()
            }
        }
    }

    override fun getItemCategory(id: String): Flow<Resource<Category>> {
        return dataSource.getItemCategory(id).map { resource ->
            resource.map { response ->
                response.toDomain()
            }
        }
    }

    override fun getItemDescription(id: String): Flow<Resource<String>> {
        return dataSource.getItemDescription(id).map { resource ->
            resource.map { response ->
                response.toDomain()
            }
        }
    }
}