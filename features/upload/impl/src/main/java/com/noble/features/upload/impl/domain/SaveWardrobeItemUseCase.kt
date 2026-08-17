package com.noble.features.upload.impl.domain

import com.noble.features.upload.api.domain.IUploadRepository
import com.noble.features.upload.api.domain.NewWardrobeItem
import javax.inject.Inject

internal class SaveWardrobeItemUseCase @Inject constructor(
    private val repository: IUploadRepository,
) {
    suspend fun run(item: NewWardrobeItem) {
        repository.saveItem(item)
    }
}