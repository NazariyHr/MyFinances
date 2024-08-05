package nazar.hr.myfinances.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import nazar.hr.myfinances.data.local_db.dao.CurrencyDao
import nazar.hr.myfinances.data.local_db.entity.CurrencyEntity

@Database(
    entities = [CurrencyEntity::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "local_database.db"
    }

    abstract val currencyDao: CurrencyDao
}