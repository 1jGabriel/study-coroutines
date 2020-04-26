package br.com.study_coroutines.ui.model

interface AppAdapterListener<T> {
    fun onItemClick(model: T, position: Int)
}