package nazar.hr.myfinances.data.repository_implementations

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import nazar.hr.myfinances.data.local_db.Database
import nazar.hr.myfinances.data.local_db.entity.toCurrency
import nazar.hr.myfinances.data.local_db.entity.toCurrencyEntity
import nazar.hr.myfinances.domain.errors.DataBaseErrors
import nazar.hr.myfinances.domain.model.currency.Currency
import nazar.hr.myfinances.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    database: Database
) : CurrencyRepository {

    private val currDao = database.currencyDao

    override fun getCurrenciesFlow(): Flow<List<Currency>> =
        currDao.getAllFlow().distinctUntilChanged()
            .map { currencies -> currencies.map { currency -> currency.toCurrency() } }

    override fun getCurrencyByIdFlow(currencyId: Int): Flow<Currency?> =
        currDao.getCurrencyByIdFlow(currencyId).map { it?.toCurrency() }

    override fun getMainCurrencyFlow(): Flow<Currency?> =
        currDao.getMainCurrencyFlow().distinctUntilChanged().map { it?.toCurrency() }

    override suspend fun getCurrencyById(currencyId: Int): Currency? =
        currDao.get(currencyId)?.toCurrency()

    override suspend fun editCurrency(currencyId: Int, currencyData: Currency.EditCurrencyData) {
        currDao.update(currencyData.toCurrencyEntity(currencyId))
    }

    override suspend fun setAsMainCurrency(currencyId: Int) {
        currDao.setAsMainCurrency(currencyId)
    }

    override suspend fun unsetAsMainCurrency(currencyId: Int) {
        currDao.unsetAsMainCurrency(currencyId)
    }

    override suspend fun createCurrency(currencyData: Currency.CreateCurrencyData) {
        try {
            currDao.create(currencyData.toCurrencyEntity())
        } catch (e: SQLiteConstraintException) {
            if (e.message?.contains("UNIQUE constraint failed") == true) {
                throw DataBaseErrors.UniqueConstraintError(e.message.orEmpty())
            } else {
                throw DataBaseErrors.UnexpectedError(e.message.orEmpty())
            }
        } catch (e: SQLiteException) {
            throw DataBaseErrors.UnexpectedError(e.message.orEmpty())
        }
    }

    override suspend fun removeCurrency(currencyId: Int) {
        currDao.delete(currencyId)
    }
}