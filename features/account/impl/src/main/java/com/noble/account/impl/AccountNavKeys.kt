package com.noble.account.impl

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.noble.account.api.AccountScreen

fun EntryProviderScope<NavKey>.accountEntry() {
    entry<AccountScreen> {
        AccountScreen()
    }
}