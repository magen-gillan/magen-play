/*
 * Copyright (c) 2026 Magen Gillan
 *
 * Dialog for advanced AI translation settings: model name + custom API endpoint.
 * Lets users override the provider defaults for self-hosted or proxy setups.
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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AiTranslationAdvancedDialog : AbsSettingsDialog() {

    @Composable
    override fun Content() {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val modelKey = remember { Keys.aiTranslationModel }
        val endpointKey = remember { Keys.aiTranslationApiEndpoint }
        val modelPref = remember { Settings(context)[modelKey] }
        val endpointPref = remember { Settings(context)[endpointKey] }
        var model by remember { mutableStateOf(modelPref.data) }
        var endpoint by remember { mutableStateOf(endpointPref.data) }

        SettingsDialog(
            modifier = Modifier,
            title = stringResource(R.string.pref_title_ai_advanced),
            actions = listOf(
                ActionItem(
                    Icons.Default.Refresh,
                    textRes = R.string.action_reset,
                    onClick = {
                        coroutineScope.launch(Dispatchers.IO) {
                            modelPref.reset()
                            endpointPref.reset()
                        }
                        dismiss()
                    }
                ),
                ActionItem(
                    Icons.Default.Check,
                    textRes = android.R.string.ok,
                    onClick = {
                        coroutineScope.launch(Dispatchers.IO) {
                            modelPref.edit { model.trim() }
                            endpointPref.edit { endpoint.trim() }
                        }
                        dismiss()
                    }
                ),
            )
        ) {
            Column(
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(stringResource(R.string.pref_summary_ai_model))
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = model,
                    onValueChange = { model = it },
                    label = { Text(stringResource(R.string.pref_title_ai_model)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(),
                )
                Spacer(Modifier.height(16.dp))
                Text(stringResource(R.string.pref_summary_ai_endpoint))
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = endpoint,
                    onValueChange = { endpoint = it },
                    label = { Text(stringResource(R.string.pref_title_ai_endpoint)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
                    colors = TextFieldDefaults.outlinedTextFieldColors(),
                )
            }
        }
    }
}
