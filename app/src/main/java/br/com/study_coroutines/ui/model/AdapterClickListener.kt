package br.com.study_coroutines.ui.model

interface AdapterClickListener<T> {
    fun onItemClick(model: T)
}
