/*
 * Copyright (c) 2026 Magen Gillan
 *
 * Dialog for entering the AI translation API key.
 * Uses a simple TextField; the key is stored in DataStore.
 */

package player.phonograph.ui.modules.setting.dialog

import player.phonograph.R
import player.phonograph.settings.Keys
import player.phonograph.settings.Settings
import player.phonograph.ui.compose.components.ActionItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AiTranslationApiKeyDialog : AbsSettingsDialog() {

    @Composable
    override fun Content() {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val key = remember { Keys.aiTranslationApiKey }
        val preference = remember { Settings(context)[key] }
        var apiKey by remember { mutableStateOf(preference.data) }
        var showKey by remember { mutableStateOf(false) }

        SettingsDialog(
            modifier = Modifier,
            title = stringResource(R.string.pref_title_ai_api_key),
            actions = listOf(
                ActionItem(
                    Icons.Default.Check,
                    textRes = android.R.string.ok,
                    onClick = {
                        coroutineScope.launch(Dispatchers.IO) {
                            preference.edit { apiKey.trim() }
                        }
                        dismiss()
                    }
                ),
            )
        ) {
            Column(
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(stringResource(R.string.pref_summary_ai_api_key))
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = apiKey,
                    onValueChange = { apiKey = it },
                    label = { Text(stringResource(R.string.pref_title_ai_api_key)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation =
                        if (showKey) VisualTransformation.None
                        else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showKey = !showKey }) {
                            // Use painterResource (vector drawable) instead of material-icons-extended
                            // (which is not in the dependency set).
                            Icon(
                                painter = painterResource(
                                    if (showKey) android.R.drawable.ic_menu_view
                                    else android.R.drawable.ic_partial_secure
                                ),
                                contentDescription = null,
                            )
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(),
                )
                Spacer(Modifier.height(8.dp))
                Text(stringResource(R.string.tip_ai_api_key_safety))
            }
        }
    }
}
