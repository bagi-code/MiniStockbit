package com.robby.ministockbit.model

import com.google.gson.annotations.SerializedName

data class CryptoResponse(
    @SerializedName("Data")
    val `data`: List<DataResponse?>?,
    @SerializedName("HasWarning")
    val hasWarning: Boolean?,
    @SerializedName("Message")
    val message: String?,
    @SerializedName("MetaData")
    val metaData: MetaDataResponse?,
    @SerializedName("Type")
    val type: Int?
) {
    fun toClean() = Crypto(
        data = data?.map { it?.toClean() } as List<Data>,
        hasWarning = false,
        message = message.orEmpty(),
        metaData = metaData?.toClean() ?: MetaData(),
        type = type ?: 0
    )
}

data class DataResponse(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfoResponse?,
    @SerializedName("DISPLAY")
    val display: DisplayResponse?,
    @SerializedName("RAW")
    val rawResponse: RawResponse?
) {
    fun toClean() = Data(
        coinInfo = coinInfo?.toClean() ?: CoinInfo(),
        dISPLAY = display?.toClean() ?: DISPLAY(),
        rAW = rawResponse?.toClean() ?: RAW()
    )
}

data class CoinInfoResponse(
    @SerializedName("FullName")
    val fullName: String?,
    @SerializedName("ImageUrl")
    val imageUrl: String?,
    @SerializedName("Name")
    val name: String?,
    @SerializedName("Id")
    val id: String?,
) {
    fun toClean() = CoinInfo(
        fullName =fullName.orEmpty(),
        imageUrl =imageUrl.orEmpty(),
        name =name.orEmpty(),
        id =id.orEmpty()
    )
}

data class WeissResponse(
    @SerializedName("MarketPerformanceRating")
    val marketPerformanceRating: String?,
    @SerializedName("Rating")
    val rating: String?,
    @SerializedName("TechnologyAdoptionRating")
    val technologyAdoptionRating: String?
) {
    fun toClean() = Weiss (
        marketPerformanceRating = marketPerformanceRating.orEmpty(),
        rating = rating.orEmpty(),
        technologyAdoptionRating = technologyAdoptionRating.orEmpty()
    )
}

data class DisplayResponse(
    @SerializedName("USD")
    val uSD: USDResponse?
) {
    fun toClean() = DISPLAY(
        uSD = uSD?.toClean() ?: USD()
    )
}

data class USDResponse(
    @SerializedName("PRICE")
    val pRICE: String?
) {
    fun toClean() = USD(
        pRICE = pRICE.orEmpty()
    )
}

data class RawResponse(
    @SerializedName("USD")
    val usdRawResponse: USDRAWResponse?
) {
    fun toClean() = RAW(
        uSD = usdRawResponse?.toClean() ?: USDRAW()
    )
}

data class USDRAWResponse(
    @SerializedName("CHANGEHOUR")
    val changeHour: Double?,
    @SerializedName("CHANGEPCTHOUR")
    val changePCTHour: Double?
) {
    fun toClean() = USDRAW(
        changeHour = changeHour ?: 0.0,
        changePCTHour = changePCTHour ?:0.0
    )
}

data class MetaDataResponse(
    @SerializedName("Count")
    val count: Int?
) {
    fun toClean() = MetaData(
        count = count ?: 0
    )
}
