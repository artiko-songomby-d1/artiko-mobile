package com.onlydevs.artiko.data.auth

import com.onlydevs.artiko.data.artikoJson
import java.util.Base64
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull

/** The `exp` claim (Unix seconds) of a JWT, read without checking the signature; null when it has none. */
fun jwtExpiresAt(jwt: String): Long? = runCatching {
    val payload = Base64.getUrlDecoder().decode(jwt.split(".")[1])
    artikoJson.parseToJsonElement(String(payload)).jsonObject["exp"]?.jsonPrimitive?.longOrNull
}.getOrNull()
