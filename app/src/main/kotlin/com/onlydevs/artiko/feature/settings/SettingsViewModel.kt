package com.onlydevs.artiko.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onlydevs.artiko.data.auth.AuthException
import com.onlydevs.artiko.data.auth.AuthFailure
import com.onlydevs.artiko.data.auth.AuthRepository
import com.onlydevs.artiko.data.settings.AppSettings
import com.onlydevs.artiko.data.settings.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/** What the account page shows besides the account itself: a request in progress, or why it failed. */
data class AccountState(val busy: Boolean = false, val failure: AuthFailure? = null)

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
    private val auth: AuthRepository,
) : ViewModel() {
    val settings: StateFlow<AppSettings> =
        settingsRepository.settings.stateIn(viewModelScope, SharingStarted.Eagerly, AppSettings())
    val user = auth.user

    private val mutableAccount = MutableStateFlow(AccountState())
    val account: StateFlow<AccountState> = mutableAccount.asStateFlow()

    fun change(update: (AppSettings) -> AppSettings) {
        viewModelScope.launch { settingsRepository.update(update) }
    }

    fun signIn(email: String, password: String) = authenticate { auth.signIn(email.trim(), password) }

    fun signUp(name: String, email: String, password: String) =
        authenticate { auth.signUp(name.trim(), email.trim(), password) }

    fun signOut() {
        viewModelScope.launch { auth.signOut() }
    }

    private fun authenticate(call: suspend () -> Unit) {
        viewModelScope.launch {
            mutableAccount.value = AccountState(busy = true)
            mutableAccount.value = try {
                call()
                AccountState()
            } catch (failed: AuthException) {
                AccountState(failure = failed.failure)
            }
        }
    }
}
