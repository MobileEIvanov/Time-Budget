package com.timebudget.ui.trackactivity

sealed class TrackUiModel{
    data class InputState(val description: String, val interval: String, val intervalType: Int) : TrackUiModel()
}