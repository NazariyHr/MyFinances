package nazar.hr.myfinances.domain.model.assets

import nazar.hr.myfinances.domain.model.Currency

data class AssetSellingCost(
    val asset: Asset,
    val sellingCurrency: Currency,
    val sellingCost: Float
)
