@file:OptIn(ExperimentalMaterial3Api::class)

package com.roblesdotdev.run.presentation.run_overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roblesdotdev.core.presentation.designsystem.AnalyticsIcon
import com.roblesdotdev.core.presentation.designsystem.LogoIcon
import com.roblesdotdev.core.presentation.designsystem.LogoutIcon
import com.roblesdotdev.core.presentation.designsystem.RunIcon
import com.roblesdotdev.core.presentation.designsystem.RuniqueAppTheme
import com.roblesdotdev.core.presentation.designsystem.components.RuniqueFloatingActionButton
import com.roblesdotdev.core.presentation.designsystem.components.RuniqueScaffold
import com.roblesdotdev.core.presentation.designsystem.components.RuniqueToolbar
import com.roblesdotdev.core.presentation.designsystem.components.util.DropDownItem
import com.roblesdotdev.run.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunOverviewScreenRoot(
    viewModel: RunOverviewViewModel = koinViewModel(),
    onStartRunClick: () -> Unit,
) {
    RunOverviewScreen(
        onAction =  { action ->
            when (action) {
                RunOverviewAction.OnStartClick -> onStartRunClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun RunOverviewScreen(
    onAction: (RunOverviewAction) -> Unit,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    RuniqueScaffold(
        topAppBar = {
            RuniqueToolbar(
                showBackButton = false,
                title = stringResource(id = R.string.runique),
                scrollBehavior = scrollBehavior,
                menuItems = listOf(
                    DropDownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(id = R.string.analytics),
                    ),
                    DropDownItem(
                        icon = LogoutIcon,
                        title = stringResource(id = R.string.logout),
                    ),
                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }
                },
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            RuniqueFloatingActionButton(
                icon = RunIcon,
                onClick = {
                    onAction(RunOverviewAction.OnStartClick)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "List of items")
        }
    }
}

@Preview
@Composable
private fun RunOverviewScreenPreview() {
    RuniqueAppTheme {
        RunOverviewScreen(onAction = {})
    }
}