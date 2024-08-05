package nazar.hr.myfinances.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nazar.hr.myfinances.data.local_db.Database
import nazar.hr.myfinances.data.repository_implementations.CurrencyRepositoryImpl
import nazar.hr.myfinances.domain.repository.CurrencyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
data object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            Database.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideCurrencyRepository(
        database: Database
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(database)
    }
}