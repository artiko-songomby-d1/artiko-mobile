package com.onlydevs.artiko.data.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplates
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import com.onlydevs.artiko.data.artikoJson
import java.util.Base64
import kotlinx.coroutines.flow.first

private const val KEYSET_NAME = "artiko_session_keyset"
private const val KEYSET_PREFS = "artiko_session_keyset_prefs"
private const val MASTER_KEY_URI = "android-keystore://artiko_session_master_key"
private val ASSOCIATED_DATA = "artiko-session".toByteArray()
private val SESSION_KEY = stringPreferencesKey("session")

val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

/** An AES-256-GCM key protected by the Android Keystore, used to encrypt the session at rest. */
fun createSessionAead(context: Context): Aead {
    AeadConfig.register()
    return AndroidKeysetManager.Builder()
        .withSharedPref(context, KEYSET_NAME, KEYSET_PREFS)
        .withKeyTemplate(KeyTemplates.get("AES256_GCM"))
        .withMasterKeyUri(MASTER_KEY_URI)
        .build()
        .keysetHandle
        .getPrimitive(RegistryConfiguration.get(), Aead::class.java)
}

/** Keeps the session in DataStore, encrypted with Tink, so tokens never sit on the disk in clear text. */
class TinkSessionStore(private val dataStore: DataStore<Preferences>, private val aead: Aead) : SessionStore {
    override suspend fun read(): Session? = dataStore.data.first()[SESSION_KEY]?.let(::decrypt)

    override suspend fun save(session: Session) {
        val sealed = aead.encrypt(artikoJson.encodeToString(session).toByteArray(), ASSOCIATED_DATA)
        dataStore.edit { it[SESSION_KEY] = Base64.getEncoder().encodeToString(sealed) }
    }

    override suspend fun clear() {
        dataStore.edit { it.remove(SESSION_KEY) }
    }

    // A session that cannot be decrypted (the key was reset) is treated as signed out.
    private fun decrypt(stored: String): Session? = runCatching {
        val plain = aead.decrypt(Base64.getDecoder().decode(stored), ASSOCIATED_DATA)
        artikoJson.decodeFromString<Session>(String(plain))
    }.getOrNull()
}
