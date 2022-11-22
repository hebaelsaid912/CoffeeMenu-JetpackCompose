package com.hebaelsaid.android.coffeemenu_jetbackcompose.di


import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.remote.CoffeeApiInterface
import com.hebaelsaid.android.coffeemenu_jetbackcompose.data.repository.CoffeeRepositoryImpl
import com.hebaelsaid.android.coffeemenu_jetbackcompose.domain.repository.CoffeeRepoImpl
import com.hebaelsaid.android.coffeemenu_jetbackcompose.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

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
    fun provideCoffeeRepository(api: CoffeeApiInterface): CoffeeRepoImpl {
        return CoffeeRepositoryImpl(api = api)
    }
}