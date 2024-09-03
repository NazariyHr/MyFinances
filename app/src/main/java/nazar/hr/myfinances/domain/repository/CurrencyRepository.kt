package nazar.hr.myfinances.domain.repository

import kotlinx.coroutines.flow.Flow
import nazar.hr.myfinances.domain.errors.DataBaseErrors
import nazar.hr.myfinances.domain.model.currency.Currency

interface CurrencyRepository {
    fun getCurrenciesFlow(): Flow<List<Currency>>
    fun getCurrencyByIdFlow(currencyId: Int): Flow<Currency?>
    fun getMainCurrencyFlow(): Flow<Currency?>
    suspend fun getCurrencyById(currencyId: Int): Currency?
    suspend fun editCurrency(currencyId: Int, currencyData: Currency.EditCurrencyData)
    suspend fun setAsMainCurrency(currencyId: Int)
    suspend fun unsetAsMainCurrency(currencyId: Int)

    @Throws(DataBaseErrors::class)
    suspend fun createCurrency(currencyData: Currency.CreateCurrencyData)
    suspend fun removeCurrency(currencyId: Int)
}