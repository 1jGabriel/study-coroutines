package br.com.study_coroutines.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.study_coroutines.databinding.ListFragmentBinding
import br.com.study_coroutines.domain.model.Character
import br.com.study_coroutines.ui.ViewAction
import br.com.study_coroutines.ui.adapter.GenericAdapter
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.ext.android.inject

class ListFragment : Fragment(), GenericAdapter.AppAdapterListener<Character> {

    private val viewModel: MainViewModel by inject()
    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ListFragmentBinding.inflate(inflater, container, false).apply {
        binding = this
        viewState = viewModel.viewState
        lifecycleOwner = viewLifecycleOwner
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatchAction(ViewAction.Init)

        swipe.setOnRefreshListener {
            viewModel.dispatchAction(ViewAction.Refresh)
            swipe.isRefreshing = false
        }
    }

    override fun onItemClick(model: Character, position: Int) {
        Toast.makeText(requireContext(), model.id.toString(), Toast.LENGTH_SHORT).show()
    }
}
