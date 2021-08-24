package com.robby.ministockbit.model

data class CryptoRequest(
    var pageNum: Int,
    var limit: Int = 10,
    var tsym: String = "USD"
)