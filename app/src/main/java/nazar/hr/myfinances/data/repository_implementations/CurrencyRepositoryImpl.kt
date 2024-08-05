package nazar.hr.myfinances.data.repository_implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import nazar.hr.myfinances.data.local_db.Database
import nazar.hr.myfinances.data.local_db.entity.toCurrency
import nazar.hr.myfinances.data.local_db.entity.toCurrencyEntity
import nazar.hr.myfinances.domain.model.currency.Currency
import nazar.hr.myfinances.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    database: Database
) : CurrencyRepository {

    private val currDao = database.currencyDao

    override fun getCurrenciesFlow(currencyId: Int): Flow<List<Currency>> =
        currDao.getAllFlow().distinctUntilChanged()
            .map { currencies -> currencies.map { currency -> currency.toCurrency() } }

    override fun getCurrencyByIdFlow(currencyId: Int): Flow<Currency?> =
        currDao.getFlow(currencyId).map { it?.toCurrency() }

    override suspend fun getCurrencyById(currencyId: Int): Currency? =
        currDao.get(currencyId)?.toCurrency()

    override suspend fun editCurrency(currencyId: Int, currencyData: Currency.EditCurrencyData) {
        currDao.update(currencyData.toCurrencyEntity(currencyId))
    }

    override suspend fun createCurrency(currencyData: Currency.CreateCurrencyData) {
        currDao.create(currencyData.toCurrencyEntity())
    }

    override suspend fun removeCurrency(currencyId: Int) {
        currDao.delete(currencyId)
    }
}