package com.noble.features.upload.api.domain

interface IUploadRepository {
    suspend fun saveItem(item: NewWardrobeItem)
}