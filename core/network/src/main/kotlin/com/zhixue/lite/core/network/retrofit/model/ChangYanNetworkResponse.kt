package com.zhixue.lite.core.network.retrofit.model

import com.zhixue.lite.core.network.exception.ApiResponseException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(ChangYanNetworkResponseSerializer::class)
internal data class ChangYanNetworkResponse<T>(
    @SerialName("data")
    val data: T
)

private class ChangYanNetworkResponseSerializer<T>(
    private val serializer: KSerializer<T>
) : KSerializer<ChangYanNetworkResponse<T>> {

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, value: ChangYanNetworkResponse<T>) {}

    override fun deserialize(decoder: Decoder): ChangYanNetworkResponse<T> {

        check(decoder is JsonDecoder)

        val element = decoder.decodeJsonElement().jsonObject

        val code = element["code"]!!.jsonPrimitive.content
        val message = element["message"]!!.jsonPrimitive.content

        if (code != "success") {
            throw ApiResponseException(message)
        }

        return ChangYanNetworkResponse(
            decoder.json.decodeFromJsonElement(serializer, element["data"]!!)
        )
    }
}