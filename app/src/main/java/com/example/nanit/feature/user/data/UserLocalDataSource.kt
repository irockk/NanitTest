package com.example.nanit.feature.user.data

import android.content.Context
import androidx.datastore.core.DataStoreFactory.create
import com.example.nanit.core.createDataStoreSerializer
import com.example.nanit.feature.user.data.models.UserDataModel
import org.koin.core.annotation.Singleton
import java.io.File

@Singleton
class UserLocalDataSource(context: Context) {
    companion object {
        private const val DATA_STORE_NAME = "user_data"
    }

    private val dataStore = create(
        serializer = createDataStoreSerializer<UserDataModel?>(null),
        produceFile = { File(context.filesDir, "datastore/$DATA_STORE_NAME") },
    )

    fun getUser() = dataStore.data

    suspend fun updateUser(user: UserDataModel) {
        dataStore.updateData {
            UserDataModel(
                name = user.name,
                birthday = user.birthday,
                picture = user.picture
            )
        }
    }
}