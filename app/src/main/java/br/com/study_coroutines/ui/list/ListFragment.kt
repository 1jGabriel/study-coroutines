package br.com.study_coroutines.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import br.com.study_coroutines.databinding.ListFragmentBinding
import br.com.study_coroutines.domain.model.Character
import br.com.study_coroutines.ui.ViewAction
import br.com.study_coroutines.ui.adapter.PersonagesAdapter
import br.com.study_coroutines.ui.model.AdapterClickListener
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.ext.android.inject

class ListFragment : Fragment(), AdapterClickListener<Character> {

    private val viewModel: ListCharactersViewModel by inject()
    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ListFragmentBinding.inflate(inflater, container, false).apply {
        binding = this
        lifecycleOwner = viewLifecycleOwner
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PersonagesAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.getPosts().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.dispatchAction(ViewAction.Init)

        swipe.setOnRefreshListener {
            viewModel.dispatchAction(ViewAction.Refresh)
            swipe.isRefreshing = false
        }
    }

    override fun onItemClick(model: Character, position: Int) {
        val action = ListFragmentDirections.actionToDetail()
        action.characterId = model.id
        Navigation.findNavController(binding.root).navigate(action)
    }
}
