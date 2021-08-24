package com.robby.ministockbit.model

import com.robby.ministockbit.data.local.CryptoEntity

data class Crypto(
    val `data`: List<Data> = listOf(),
    val hasWarning: Boolean = false,
    val message: String = "",
    val metaData: MetaData,
    val type: Int = 0
) {
    fun toConvertCrptyoModel() : List<CryptoEntity> {
        var dataTemp : ArrayList<CryptoEntity> = ArrayList()
        data.forEach {
            dataTemp.add(
                CryptoEntity(
                id = it.coinInfo.id.toLong(),
                name = it.coinInfo.name,
                fullname = it.coinInfo.fullName,
                price = it.dISPLAY.uSD.pRICE,
                changeHour = it.rAW.uSD.changeHour.toString(),
                changePCTHour = it.rAW.uSD.changePCTHour.toString(),
            )
            )
        }
        return dataTemp
    }
}

data class Data(
    val coinInfo: CoinInfo,
    val dISPLAY: DISPLAY,
    val rAW: RAW
)

data class CoinInfo(
    val fullName: String="",
    val imageUrl: String="",
    val name: String="",
    val id: String="",
)

data class Weiss(
    val marketPerformanceRating: String="",
    val rating: String="",
    val technologyAdoptionRating: String=""
)

data class DISPLAY(
    val uSD: USD = USD()
)

data class RAW(
    val uSD: USDRAW = USDRAW()
)

data class USD(
    val pRICE: String=""
)

data class USDRAW(
    val changeHour: Double=0.0,
    val changePCTHour: Double=0.0
)

data class MetaData(
    val count: Int=0
)