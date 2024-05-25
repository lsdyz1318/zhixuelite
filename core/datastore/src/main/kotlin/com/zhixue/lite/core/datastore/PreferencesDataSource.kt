package com.zhixue.lite.core.datastore

import androidx.datastore.core.DataStore
import com.zhixue.lite.core.datastore.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>
) {
    val userPreferences: Flow<UserPreferences> = userPreferencesDataStore.data

    suspend fun setUserId(id: String) {
        userPreferencesDataStore.updateData {
            it.copy(id = id)
        }
    }

    suspend fun setUserToken(token: String) {
        userPreferencesDataStore.updateData {
            it.copy(token = token)
        }
    }

    suspend fun setUserTicket(ticket: String) {
        userPreferencesDataStore.updateData {
            it.copy(ticket = ticket)
        }
    }
}