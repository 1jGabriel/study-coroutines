package br.com.pagingcompose.ui

sealed class ViewAction {
    object Init : ViewAction()
    object Refresh : ViewAction()
}
