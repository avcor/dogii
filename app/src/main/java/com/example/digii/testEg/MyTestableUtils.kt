package com.example.digii.testEg

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyTestableUtils (private val dispatcher: CoroutineDispatcher) {

    suspend fun getUserName(): String{
        delay(1000)
        return "Orange"
    }

    suspend fun getUser(): String{
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
        }
        return "Orange - Object"
    }

    suspend fun getAddress(): String{
        withContext(dispatcher){
            delay(1000)
        }
        return "Orange - Address"
    }
}