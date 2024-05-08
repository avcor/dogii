package com.example.digii.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.example.digii.data.local.model.LocalPostDataEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostDataDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var postDataDao: PostDataDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        postDataDao = database.postDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertPostData_expectSinglePost () = runBlocking {
        val data = LocalPostDataEntity("id", "https", 0, "txt", "publish", null)
        postDataDao.insertPostData(data)
        launch {
            delay(100)
            postDataDao.insertPostData(
                LocalPostDataEntity()
            )
        }
        postDataDao.getAllLivePostData().test{
            val list = awaitItem()
            Assert.assertEquals(1, list.size)
        }
    }

    @Test
    fun deletePostData_expectedDeletedPost () = runBlocking {
        val toBeDelPost = LocalPostDataEntity()
        postDataDao.insertPostData(toBeDelPost)
        val list = postDataDao.getAllPostData()

        postDataDao.deletePost(toBeDelPost)
        val listBefore = postDataDao.getAllPostData()

        Assert.assertEquals(list.size, listBefore.size + 1)
    }
}