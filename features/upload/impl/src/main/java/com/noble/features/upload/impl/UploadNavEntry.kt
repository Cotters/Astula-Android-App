package com.noble.features.upload.impl

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.noble.features.upload.api.UploadScreen

fun EntryProviderScope<NavKey>.uploadScreenEntry() {
    entry<UploadScreen> {
        UploadScreen()
    }
}
