package br.com.study_coroutines.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import br.com.study_coroutines.R
import br.com.study_coroutines.databinding.LoadStateFooterViewBinding
import br.com.study_coroutines.ui.extension.setVisibility

class PersonagesLoadStateViewHolder(
    private val binding: LoadStateFooterViewBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.setVisibility(loadState is LoadState.Loading)
        binding.retryButton.setVisibility(loadState !is LoadState.Loading)
        binding.errorMsg.setVisibility(loadState !is LoadState.Loading)
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PersonagesLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_footer_view, parent, false)
            val binding = LoadStateFooterViewBinding.bind(view)
            return PersonagesLoadStateViewHolder(binding, retry)
        }
    }
}