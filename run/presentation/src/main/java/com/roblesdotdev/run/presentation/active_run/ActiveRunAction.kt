package com.roblesdotdev.run.presentation.active_run

sealed interface ActiveRunAction {
    data object OnToggleRunClick: ActiveRunAction
    data object OnFinishRunAction: ActiveRunAction
    data object OnResumeClick: ActiveRunAction
    data object OnBackClick: ActiveRunAction
}