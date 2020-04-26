package br.com.study_coroutines.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.study_coroutines.databinding.DetailFragmentBinding
import org.koin.android.ext.android.inject

class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by inject()
    private lateinit var binding: DetailFragmentBinding
    private var characterId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DetailFragmentBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = viewLifecycleOwner
        viewState = viewModel.viewState
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            characterId = DetailFragmentArgs.fromBundle(it).characterId
            viewModel.dispatchAction(ViewAction.Init("$characterId"))
        }
    }
}
