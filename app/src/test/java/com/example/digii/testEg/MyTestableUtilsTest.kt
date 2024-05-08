package com.example.digii.testEg

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyTestableUtilsTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun getUserName(){
        val sut = MyTestableUtils(mainCoroutineRule.testDispatcher)
        runTest{
            Assert.assertEquals(sut.getUserName(), "Orange")
        }
    }

    @Test
    fun getUserObj(){
        val sut = MyTestableUtils(mainCoroutineRule.testDispatcher)
        runTest {
            Assert.assertEquals(sut.getUser(),"Orange - Object")
        }
    }

    @Test
    fun getAddress(){
        val sut = MyTestableUtils(mainCoroutineRule.testDispatcher)
        runTest {
            Assert.assertEquals(sut.getAddress(), "Orange - Address")
        }
    }
}