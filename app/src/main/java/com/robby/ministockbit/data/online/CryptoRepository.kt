package com.robby.ministockbit.data.online

import com.robby.ministockbit.data.local.CryptoDao
import com.robby.ministockbit.model.CryptoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoRepository(private val remoteRepository: RemoteRepository,
                       private val userDao: CryptoDao) {

    val data = userDao.findAll()

    suspend fun refresh(param: CryptoRequest) {
        withContext(Dispatchers.IO) {
            val dataOnline = remoteRepository.getAllFilter(param.limit, param.pageNum, param.tsym).await()
            val dataFilter = dataOnline.toClean().toConvertCrptyoModel()
            userDao.add(dataFilter)
        }
    }

}