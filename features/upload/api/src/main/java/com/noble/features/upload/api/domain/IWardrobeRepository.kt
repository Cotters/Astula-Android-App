package com.noble.features.upload.api.domain

interface IWardrobeRepository {
    suspend fun saveItem(item: NewWardrobeItem)
}