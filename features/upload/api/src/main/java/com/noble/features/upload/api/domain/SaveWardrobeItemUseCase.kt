package com.noble.features.upload.api.domain

import javax.inject.Inject

class SaveWardrobeItemUseCase @Inject constructor(
    private val repository: IWardrobeRepository,
) {
    suspend fun run(item: NewWardrobeItem) {
        repository.saveItem(item)
    }
}