package com.noble.astula

import android.app.Application
import com.noble.features.wardrobe.WardrobeViewModel

class AstulaApplication: Application() {
    lateinit var diContainer: DIContainer

    override fun onCreate() {
        super.onCreate()
        diContainer = DIContainer()

        setupDependencies()
    }

    private fun setupDependencies() {
        val wardrobeViewModel = WardrobeViewModel()
        diContainer.register(WardrobeViewModel::class.java, wardrobeViewModel)
    }
}