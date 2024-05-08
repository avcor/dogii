package com.example.digii.testEg

import android.content.Context
import android.content.res.AssetManager
import com.nhaarman.mockitokotlin2.anyVararg
import com.nhaarman.mockitokotlin2.doReturn
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ContextUsageTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var assetsManager: AssetManager

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun test(){
        val testStream = ContextUsage::class.java.getResourceAsStream("/quotes.json")
        doReturn(assetsManager).`when`(context).assets
        Mockito.`when`(context.assets.open(anyVararg())).thenReturn(testStream)

        val sut = ContextUsage()
        sut.populateQuoteFromAssets(context, "")
        val quote = sut.getNextQuote()

        Assert.assertEquals(quote.name, "Grace")
    }
}