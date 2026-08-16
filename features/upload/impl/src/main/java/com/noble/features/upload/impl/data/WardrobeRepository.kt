package com.noble.features.upload.impl.data

import com.noble.features.upload.api.domain.IWardrobeRepository
import com.noble.features.upload.api.domain.NewWardrobeItem
import javax.inject.Inject

class WardrobeRepository @Inject constructor(
    // TODO: Add API and Mappers.
    //  Add Room database for offline-first caching.
    //  Good time to practice writing tests too!
    //  ViewModel and Repository. :)
): IWardrobeRepository {
    override suspend fun saveItem(item: NewWardrobeItem) {
        TODO("Not yet implemented")
    }
}