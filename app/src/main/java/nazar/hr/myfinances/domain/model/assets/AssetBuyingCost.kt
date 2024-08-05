package nazar.hr.myfinances.domain.model.assets

import nazar.hr.myfinances.domain.model.currency.Currency

data class AssetBuyingCost(
    val asset: Asset,
    val buyingCurrency: Currency,
    val buyingCost: Float
)
