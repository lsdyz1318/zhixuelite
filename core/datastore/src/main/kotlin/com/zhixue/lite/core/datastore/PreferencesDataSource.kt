package com.zhixue.lite.core.datastore

import androidx.datastore.core.DataStore
import com.zhixue.lite.core.datastore.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>
) {
    val userPreferences: Flow<UserPreferences> = userPreferencesDataStore.data

    suspend fun setUserId(userId: String) {
        userPreferencesDataStore.updateData {
            it.copy(id = userId)
        }
    }

    suspend fun setUserToken(userToken: String) {
        userPreferencesDataStore.updateData {
            it.copy(token = userToken)
        }
    }

    suspend fun setUserTicket(userTicket: String) {
        userPreferencesDataStore.updateData {
            it.copy(ticket = userTicket)
        }
    }
}