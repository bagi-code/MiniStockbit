package org.pbreakers.mobile.androidtest.udacity.app.di

import android.app.Application
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import com.robby.ministockbit.App
import com.robby.ministockbit.BuildConfig
import com.robby.ministockbit.data.local.CryptoDao
import com.robby.ministockbit.data.local.CryptoDatabase
import com.robby.ministockbit.data.online.CryptoRepository
import com.robby.ministockbit.data.online.RemoteRepository
import com.robby.ministockbit.model.CryptoViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    single { CryptoViewModel(get()) }
}

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): RemoteRepository {
        return retrofit.create(RemoteRepository::class.java)
    }
    single { provideUserApi(get()) }
}

val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
            builder.connectTimeout(2, TimeUnit.MINUTES)
            builder.readTimeout(2, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(App.getApp()))
        }
        builder.cache(cache)

        return builder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application): CryptoDatabase {
        return Room.databaseBuilder(application, CryptoDatabase::class.java, "eds.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: CryptoDatabase): CryptoDao {
        return database.cryptoDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    fun provideUserRepository(api: RemoteRepository, dao: CryptoDao): CryptoRepository {
        return CryptoRepository(api, dao)
    }
    single { provideUserRepository(get(), get()) }
}