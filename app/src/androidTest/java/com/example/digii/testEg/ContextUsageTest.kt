package com.example.digii.testEg

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import org.junit.Assert
import org.junit.Before

import org.junit.Test
import java.io.FileNotFoundException

class ContextUsageTest {

    lateinit var quoteManager: ContextUsage
    lateinit var context: Context
    @Before
    fun setUp(){
        quoteManager = ContextUsage()
        context = ApplicationProvider.getApplicationContext()
    }
    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets() {
        quoteManager.populateQuoteFromAssets(context, "")
    }

    @Test(expected = JsonSyntaxException::class)
    fun populateQuoteFromAssets_JSON_Exc(){
        quoteManager.populateQuoteFromAssets(context, "invalidJsonData.json")
    }

    @Test
    fun testPopulateQuoteFromAsset_ValidJson_expect_10_item(){
        quoteManager.populateQuoteFromAssets(context, "jsonData.json")
        Assert.assertEquals(8, quoteManager.quoteList.size)
    }

    @Test
    fun testPreviousQuote_expect_CorrectQuote(){
        val obj2 = JsonFileModel("hey",10)
        quoteManager.populateQuote(arrayOf(
            obj2,
            JsonFileModel("1,", 9),
            JsonFileModel("3," ,3)
        ))
        val prevQuote = quoteManager.getPreviousQuote()
        Assert.assertEquals(JsonFileModel("hey",10), prevQuote)
    }

    @Test
    fun testNextQuote_expected_CoorectQuote(){
        val obj2 = JsonFileModel("hey",10)
        quoteManager.populateQuote(arrayOf(
            JsonFileModel("1,", 9),
            obj2,
            JsonFileModel("3," ,3)
        ))
        val quote = quoteManager.getNextQuote()
        Assert.assertEquals(quote.toString(), obj2.toString())
    }
}
