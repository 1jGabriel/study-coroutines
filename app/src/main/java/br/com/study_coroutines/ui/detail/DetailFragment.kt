package br.com.study_coroutines.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.study_coroutines.R
import br.com.study_coroutines.databinding.DetailFragmentBinding
import org.koin.android.ext.android.inject

class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by inject()
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DetailFragmentBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = viewLifecycleOwner
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }
}
