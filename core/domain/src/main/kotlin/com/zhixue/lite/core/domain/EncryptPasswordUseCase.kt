package com.zhixue.lite.core.domain

import java.math.BigInteger
import java.security.KeyFactory
import java.security.spec.RSAPublicKeySpec
import javax.crypto.Cipher
import javax.inject.Inject

private const val ZHIXUE_RSA_KEY_MODULES =
    "00ccd806a03c7391ee8f884f5902102d95f6d534d597ac42219dd8a79b1465e186c0162a6771b55e7be7422c4af494ba0112ede4eb00fc751723f2c235ca419876e7103ea904c29522b72d754f66ff1958098396f17c6cd2c9446e8c2bb5f4000a9c1c6577236a57e270bef07e7fe7bbec1f0e8993734c8bd4750e01feb21b6dc9"
private const val ZHIXUE_RSA_KEY_PUBLIC_EXPONENT = "010001"

class EncryptPasswordUseCase @Inject constructor() {
    @OptIn(ExperimentalStdlibApi::class)
    operator fun invoke(password: String): String {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        val publicKey = KeyFactory.getInstance("RSA").generatePublic(
            RSAPublicKeySpec(
                BigInteger(ZHIXUE_RSA_KEY_MODULES.hexToByteArray()),
                BigInteger(ZHIXUE_RSA_KEY_PUBLIC_EXPONENT.hexToByteArray())
            )
        )
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return cipher.doFinal(password.toByteArray()).toHexString()
    }
}