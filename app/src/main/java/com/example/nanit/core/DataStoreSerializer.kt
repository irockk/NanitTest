package com.example.nanit.core

import androidx.datastore.core.Serializer
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

inline fun <reified T> createDataStoreSerializer(defaultValue: T) = object : Serializer<T> {
    private val gson = GsonBuilder().create()

    override val defaultValue: T = defaultValue

    override suspend fun readFrom(input: InputStream): T = try {
        val json = input.readBytes().decodeToString()
        gson.fromJson(json, T::class.java)
    } catch (exception: JsonSyntaxException) {
        defaultValue
    }

    override suspend fun writeTo(t: T, output: OutputStream) {
        if (t != null) {
            withContext(Dispatchers.IO) {
                val json = gson.toJson(t)
                output.write(json.encodeToByteArray())
            }
        }
    }
}