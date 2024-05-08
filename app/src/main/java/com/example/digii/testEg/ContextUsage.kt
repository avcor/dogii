package com.example.digii.testEg

import android.content.Context
import com.google.gson.Gson

class ContextUsage {

    var quoteList = emptyArray<JsonFileModel>()
    private var currentQuoteIndex = 0

    fun populateQuoteFromAssets(context: Context, fileName: String){
        val inputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        quoteList = gson.fromJson(json, Array<JsonFileModel>::class.java)
    }
    fun populateQuote(quotes: Array<JsonFileModel>){
        quoteList = quotes
    }

    fun getNextQuote(): JsonFileModel{
        if(currentQuoteIndex == quoteList.size-1)
            return quoteList[currentQuoteIndex]
        return quoteList[++currentQuoteIndex]
    }

    fun getPreviousQuote(): JsonFileModel{
        if(currentQuoteIndex == 0){
            return quoteList[0]
        }
        return quoteList[--currentQuoteIndex]
    }
}