package br.com.study_coroutines.ui

sealed class ViewAction {
    object Init : ViewAction()
    object Refresh : ViewAction()
}
