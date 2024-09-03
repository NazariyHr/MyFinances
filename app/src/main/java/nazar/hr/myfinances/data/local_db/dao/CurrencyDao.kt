package nazar.hr.myfinances.data.local_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import nazar.hr.myfinances.data.local_db.entity.CurrencyEntity

@Dao
interface CurrencyDao {
    @Insert
    suspend fun create(currency: CurrencyEntity)

    @Query("select * from currencyentity")
    fun getAllFlow(): Flow<List<CurrencyEntity>>

    @Query("select * from currencyentity where id = :id")
    fun getCurrencyByIdFlow(id: Int): Flow<CurrencyEntity?>

    @Query("select * from currencyentity where isMain = 1")
    fun getMainCurrencyFlow(): Flow<CurrencyEntity?>

    @Query("select * from currencyentity where id = :id")
    suspend fun get(id: Int): CurrencyEntity?

    @Update
    suspend fun update(currency: CurrencyEntity)

    @Query("delete from currencyentity where id = :id")
    suspend fun delete(id: Int)


    @Query("update currencyentity set isMain = case when id = :id then 1 else 0 end")
    suspend fun setAsMainCurrency(id: Int)

    @Query("update currencyentity set isMain = 0 where id = :id")
    suspend fun unsetAsMainCurrency(id: Int)
}