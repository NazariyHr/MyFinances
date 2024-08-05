package nazar.hr.myfinances.domain.repository

import kotlinx.coroutines.flow.Flow
import nazar.hr.myfinances.domain.model.currency.Currency

interface CurrencyRepository {
    fun getCurrenciesFlow(currencyId: Int): Flow<List<Currency>>
    fun getCurrencyByIdFlow(currencyId: Int): Flow<Currency?>
    suspend fun getCurrencyById(currencyId: Int): Currency?
    suspend fun editCurrency(currencyId: Int, currencyData: Currency.EditCurrencyData)
    suspend fun createCurrency(currencyData: Currency.CreateCurrencyData)
    suspend fun removeCurrency(currencyId: Int)
}