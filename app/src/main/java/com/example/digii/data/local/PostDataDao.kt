package com.example.digii.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digii.data.local.model.LocalPostDataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface PostDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostData(postData: LocalPostDataEntity)

    @Query("SELECT * FROM posts")
    suspend fun getAllPostData(): List<LocalPostDataEntity>

    @Query("SELECT * FROM posts")
    fun getAllLivePostData(): Flow<List<LocalPostDataEntity>>

    @Delete
    suspend fun deletePost(post: LocalPostDataEntity)
}