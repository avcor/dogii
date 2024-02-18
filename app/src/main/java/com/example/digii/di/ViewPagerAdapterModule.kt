package com.example.digii.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.digii.adapter.ViewPagerAdapter
import com.example.digii.annotation.FeednSaveFragAdapter
import com.example.digii.ui.fragments.FeedFragment
import com.example.digii.ui.fragments.SavePostFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewPagerAdapterModule {

    @FeednSaveFragAdapter
    @Provides
    @ActivityScoped
    fun makeAdapter(
        @ActivityContext context: Context,
        feedFragment: FeedFragment,
        savePostFragment: SavePostFragment
    ): ViewPagerAdapter {

        val act = context as AppCompatActivity
        val fragments = listOf(feedFragment, savePostFragment)

        return ViewPagerAdapter(act.supportFragmentManager, act.lifecycle, fragments)
    }

}