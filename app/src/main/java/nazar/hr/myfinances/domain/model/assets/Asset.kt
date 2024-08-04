package nazar.hr.myfinances.domain.model.assets

data class Asset(
    val id: Int,
    val name: String,
    val description: String,
    val type: AssetType
)

enum class AssetType {
    Business,
    Crypto,
    Stocks,
    Securities,
    Insurance,
    Other
}