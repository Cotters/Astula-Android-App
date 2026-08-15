package com.noble.astula.navigation

import androidx.navigation3.runtime.NavKey
import com.noble.account.api.AccountScreen
import com.noble.astula.R
import com.noble.features.upload.api.UploadScreen
import com.noble.features.wardrobe.api.WardrobeScreen

enum class BottomNavDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Home", R.drawable.ic_home),
    UPLOAD("Upload", R.drawable.ic_add),
    ACCOUNT("Account", R.drawable.ic_account_box);

    fun toNavKey(): NavKey {
        return when (this) {
            HOME -> WardrobeScreen
            UPLOAD -> UploadScreen
            ACCOUNT -> AccountScreen
        }
    }
}