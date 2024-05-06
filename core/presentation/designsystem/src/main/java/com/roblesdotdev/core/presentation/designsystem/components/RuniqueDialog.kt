package com.roblesdotdev.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.roblesdotdev.core.presentation.designsystem.RuniqueAppTheme

@Composable
fun RuniqueDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onDismiss: () -> Unit,
    primaryButton: @Composable RowScope.() -> Unit = {},
    secondaryButton: @Composable RowScope.() -> Unit = {},
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = description,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                secondaryButton()
                primaryButton()
            }
        }
    }

}

@Preview
@Composable
private fun RuniqueDialogPreview() {
    RuniqueAppTheme {
        RuniqueDialog(
            title = "Demo Dialog",
            description = "This is a short description for a demo dialog",
            onDismiss = {},
            primaryButton = {
                RuniqueActionButton(text = "Click Me", onClick = {}, modifier = Modifier.weight(1f))
            },
            secondaryButton = {
                RuniqueOutlinedActionButton(
                    text = "Click Me",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }
        )
    }
}