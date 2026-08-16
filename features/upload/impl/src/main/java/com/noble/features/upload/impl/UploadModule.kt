package com.noble.features.upload.impl

import com.noble.features.upload.api.domain.IUploadRepository
import com.noble.features.upload.impl.data.UploadRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UploadModule {

    @Binds
    abstract fun bindUploadRepository(impl: UploadRepository): IUploadRepository

}