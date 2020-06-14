package br.com.study_coroutines.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PersonagesLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<PersonagesLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PersonagesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PersonagesLoadStateViewHolder {
        return PersonagesLoadStateViewHolder.create(parent, retry)
    }
}