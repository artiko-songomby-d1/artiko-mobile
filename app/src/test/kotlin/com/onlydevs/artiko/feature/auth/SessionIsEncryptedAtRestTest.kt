package com.onlydevs.artiko.feature.auth

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplates
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.aead.AeadConfig
import com.onlydevs.artiko.data.auth.Session
import com.onlydevs.artiko.data.auth.TinkSessionStore
import java.io.File
import java.nio.file.Files
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

class SessionIsEncryptedAtRestTest {
    private fun newAead(): Aead {
        AeadConfig.register()
        return KeysetHandle.generateNew(KeyTemplates.get("AES256_GCM"))
            .getPrimitive(RegistryConfiguration.get(), Aead::class.java)
    }

    private val file = File(Files.createTempDirectory("session").toFile(), "session.preferences_pb")
    private val dataStore = PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO)) { file }
    private val session = Session("secret-session-token", "Rado", "rado@example.test", "a.b.c", 99)

    @Test
    fun theSessionComesBackButTheTokenIsNeverStoredInClearText() = runBlocking {
        val store = TinkSessionStore(dataStore, newAead())

        store.save(session)

        assertEquals(session, store.read())
        val raw = dataStore.data.first()[stringPreferencesKey("session")].orEmpty()
        assertFalse(raw.contains("secret-session-token"))
        assertFalse(file.readText(Charsets.ISO_8859_1).contains("secret-session-token"))
    }

    @Test
    fun aSessionThatCannotBeDecryptedMeansSignedOut() = runBlocking {
        TinkSessionStore(dataStore, newAead()).save(session)

        assertNull(TinkSessionStore(dataStore, newAead()).read()) // another key, as after a reset
    }

    @Test
    fun clearingRemovesTheSession() = runBlocking {
        val store = TinkSessionStore(dataStore, newAead())
        store.save(session)

        store.clear()

        assertNull(store.read())
    }
}
