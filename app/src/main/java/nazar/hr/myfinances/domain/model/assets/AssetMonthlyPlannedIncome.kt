package nazar.hr.myfinances.domain.model.assets

import nazar.hr.myfinances.domain.model.currency.Currency

data class AssetMonthlyPlannedIncome(
    val asset: Asset,
    val dayOfMonth: Int,
    val income: Income
)

sealed class Income {
    data class FixedAmount(
        val currency: Currency,
        val amount: Float
    ) : Income()

    data class PercentOfAssetCost(
        val currency: Currency,
        val assetCost: Float,
        val percent: Float
    ) : Income()
}
