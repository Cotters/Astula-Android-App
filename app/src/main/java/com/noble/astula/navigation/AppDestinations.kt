package com.noble.astula.navigation

import androidx.navigation3.runtime.NavKey
import com.noble.account.api.AccountScreen
import com.noble.astula.R

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Home", R.drawable.ic_home),
    UPLOAD("Upload", R.drawable.ic_add),
    PROFILE("Profile", R.drawable.ic_account_box);

    fun toNavKey(): NavKey {
        return when (this) {
            HOME -> AppScreen.Wardrobe
            UPLOAD -> AppScreen.Upload
            PROFILE -> AccountScreen
        }
    }
}