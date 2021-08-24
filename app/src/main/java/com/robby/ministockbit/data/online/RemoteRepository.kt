package com.robby.ministockbit.data.online

import com.robby.ministockbit.model.CryptoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteRepository {

    @GET("data/top/totaltoptiervolfull")
    fun getAllFilter(@Query("limit") limit: Int,
                     @Query("page") pageNum: Int,
                     @Query("tsym") tsym: String): Deferred<CryptoResponse>
}