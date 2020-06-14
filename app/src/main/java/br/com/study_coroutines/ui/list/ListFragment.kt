package br.com.study_coroutines.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import br.com.study_coroutines.databinding.ListFragmentBinding
import br.com.study_coroutines.ui.adapter.PersonagesAdapter
import br.com.study_coroutines.ui.adapter.PersonagesLoadStateAdapter
import br.com.study_coroutines.ui.model.AdapterClickListener
import br.com.study_coroutines.ui.model.CharacterUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class ListFragment : Fragment(), AdapterClickListener<CharacterUi> {

    private val viewModel: ListCharactersViewModel by inject()
    private lateinit var binding: ListFragmentBinding
    private lateinit var adapter: PersonagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ListFragmentBinding.inflate(inflater, container, false).apply {
        binding = this
        lifecycleOwner = viewLifecycleOwner
    }.root

    private fun setupAdapter() {
        adapter = PersonagesAdapter(this@ListFragment)
        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PersonagesLoadStateAdapter { adapter.retry() },
            footer = PersonagesLoadStateAdapter { adapter.retry() }
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        lifecycleScope.launchWhenCreated {
            viewModel.getCharacters().collectLatest {
                adapter.submitData(it)
            }
        }

        binding.swipe.setOnRefreshListener {
            adapter.refresh()
            binding.swipe.isRefreshing = false
        }
    }

    override fun onItemClick(model: CharacterUi) {
        val action = ListFragmentDirections.callDetail()
        action.characterId = model.id
        Navigation.findNavController(binding.root).navigate(action)
    }
}
