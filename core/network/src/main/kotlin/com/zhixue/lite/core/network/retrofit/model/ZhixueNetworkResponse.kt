package com.zhixue.lite.core.network.retrofit.model

import com.zhixue.lite.core.network.exception.ApiResponseException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(ZhixueNetworkResponseSerializer::class)
internal data class ZhixueNetworkResponse<T>(
    @SerialName("result")
    val result: T?
)

private class ZhixueNetworkResponseSerializer<T>(
    private val serializer: KSerializer<T>
) : KSerializer<ZhixueNetworkResponse<T?>> {

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, value: ZhixueNetworkResponse<T?>) {}

    override fun deserialize(decoder: Decoder): ZhixueNetworkResponse<T?> {

        check(decoder is JsonDecoder)

        val element = decoder.decodeJsonElement().jsonObject

        val errorCode = element["errorCode"]!!.jsonPrimitive.int
        val errorInfo = element["errorInfo"]!!.jsonPrimitive.content

        if (errorCode != 0) {
            throw ApiResponseException(errorInfo)
        }

        return ZhixueNetworkResponse(
            when (val result = element["result"]!!) {
                // 如果result返回的是Json对象或数组则正常解析
                is JsonObject, is JsonArray -> {
                    decoder.json.decodeFromJsonElement(serializer, result)
                }
                // 如果result返回的是其他类型，则尝试将其内容进行解析
                else -> {
                    val content = result.jsonPrimitive.content
                    if (content.isEmpty()) {
                        decoder.json.decodeFromString(serializer, content)
                    } else {
                        null // 如果result内容为空，则返回null
                    }
                }
            }
        )
    }
}