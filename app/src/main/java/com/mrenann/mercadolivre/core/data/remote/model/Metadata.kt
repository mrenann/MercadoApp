package com.mrenann.mercadolivre.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("additional_bank_interest")
    val additionalBankInterest: Boolean? = false,
    @SerializedName("meliplus_installments")
    val meliplusInstallments: Boolean? = false
)