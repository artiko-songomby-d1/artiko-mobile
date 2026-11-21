package com.onlydevs.artiko.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.R
import com.onlydevs.artiko.data.auth.AuthFailure
import com.onlydevs.artiko.data.auth.SignedInUser
import com.onlydevs.artiko.ui.ArtikoButton
import com.onlydevs.artiko.ui.ArtikoTextField
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private fun AuthFailure.message(): Int = when (this) {
    AuthFailure.Offline -> R.string.auth_offline
    AuthFailure.InvalidCredentials -> R.string.auth_invalid_credentials
    AuthFailure.AlreadyRegistered -> R.string.auth_already_registered
    AuthFailure.WeakPassword -> R.string.auth_weak_password
    AuthFailure.Rejected -> R.string.auth_rejected
    AuthFailure.Unavailable -> R.string.auth_unavailable
}

/** The signed-in account with a sign out button, or the form to sign in or to create an account. */
@Composable
fun AccountPage(
    user: SignedInUser?,
    state: AccountState,
    onSignIn: (email: String, password: String) -> Unit,
    onSignUp: (name: String, email: String, password: String) -> Unit,
    onSignOut: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (user != null) {
            Text(stringResource(R.string.signed_in_as, user.name), style = ArtikoText.meta)
            Text(user.email)
            ArtikoButton(text = stringResource(R.string.sign_out), onClick = onSignOut)
        } else {
            SignInForm(state, onSignIn, onSignUp)
        }
    }
}

@Composable
private fun SignInForm(
    state: AccountState,
    onSignIn: (String, String) -> Unit,
    onSignUp: (String, String, String) -> Unit,
) {
    var creating by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val ready = email.isNotBlank() && password.isNotEmpty() && (!creating || name.isNotBlank()) && !state.busy

    Text(stringResource(R.string.signed_out_hint), style = ArtikoText.meta)
    if (creating) ArtikoTextField(name, { name = it }, label = stringResource(R.string.field_name))
    ArtikoTextField(
        email,
        { email = it },
        label = stringResource(R.string.field_email),
        keyboardType = KeyboardType.Email,
    )
    ArtikoTextField(
        password,
        { password = it },
        label = stringResource(R.string.field_password),
        keyboardType = KeyboardType.Password,
        hidden = true,
    )
    state.failure?.let { Text(stringResource(it.message()), style = ArtikoText.meta) }
    if (state.busy) Text(stringResource(R.string.auth_wait), style = ArtikoText.meta)
    ArtikoButton(
        text = stringResource(if (creating) R.string.create_account else R.string.sign_in),
        onClick = { if (creating) onSignUp(name, email, password) else onSignIn(email, password) },
        enabled = ready,
    )
    ExpositionChip(
        title = stringResource(if (creating) R.string.sign_in else R.string.create_account),
        onClick = { creating = !creating },
    )
}

@Preview(showBackground = true)
@Composable
private fun AccountPagePreview() {
    ArtikoTheme {
        AccountPage(null, AccountState(), onSignIn = { _, _ -> }, onSignUp = { _, _, _ -> }, onSignOut = {})
    }
}
