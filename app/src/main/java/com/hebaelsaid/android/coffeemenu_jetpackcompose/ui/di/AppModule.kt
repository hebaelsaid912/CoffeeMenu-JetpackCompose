package com.hebaelsaid.android.coffeemenu_jetpackcompose.ui.di

import android.content.Context
import androidx.room.Room
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.local.database.CoffeeMenuDatabase
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.remote.CoffeeApiInterface
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.repository.CoffeeApiRepoImpl
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.repository.HotCoffeeDBRepoImpl
import com.hebaelsaid.android.coffeemenu_jetpackcompose.data.repository.IcedCoffeeDBRepoImpl
import com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository.CoffeeHotDaoRepo
import com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository.CoffeeIcedDaoRepo
import com.hebaelsaid.android.coffeemenu_jetpackcompose.domain.repository.CoffeeApiRepo
import com.hebaelsaid.android.coffeemenu_jetpackcompose.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoffeeApiInterface(): CoffeeApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoffeeApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideCoffeeDatabase(@ApplicationContext appContext: Context): CoffeeMenuDatabase {
        return Room.databaseBuilder(
            appContext, CoffeeMenuDatabase::class.java,
            "coffee_menu.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoffeeRepository(api: CoffeeApiInterface): CoffeeApiRepo {
        return CoffeeApiRepoImpl(api = api)
    }
    @Provides
    @Singleton
    fun provideCoffeeHotDaoRepository(coffeeMenuDatabase: CoffeeMenuDatabase): CoffeeHotDaoRepo {
        return HotCoffeeDBRepoImpl(coffeeMenuDatabase)
    }
    @Provides
    @Singleton
    fun provideCoffeeIcedDaoRepository(coffeeMenuDatabase: CoffeeMenuDatabase): CoffeeIcedDaoRepo {
        return IcedCoffeeDBRepoImpl(coffeeMenuDatabase)
    }
}