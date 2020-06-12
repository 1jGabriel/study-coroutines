package br.com.study_coroutines.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import br.com.study_coroutines.databinding.ListFragmentBinding
import br.com.study_coroutines.ui.adapter.PersonagesAdapter
import br.com.study_coroutines.ui.model.AdapterClickListener
import br.com.study_coroutines.ui.model.CharacterUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class ListFragment : Fragment(), AdapterClickListener<CharacterUi> {

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

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PersonagesAdapter(this@ListFragment)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.allPersonages.collectLatest {
                adapter.submitData(it)
            }
        }
        // viewModel.getPosts().observe(viewLifecycleOwner, Observer {
        //     adapter.submitList(it)
        // })
        // viewModel.dispatchAction(ViewAction.Init)
        //
        // swipe.setOnRefreshListener {
        //     viewModel.dispatchAction(ViewAction.Refresh)
        //     swipe.isRefreshing = false
        // }
    }

    override fun onItemClick(model: CharacterUi, position: Int) {
        Toast.makeText(requireContext(), model.name, Toast.LENGTH_LONG).show()
        // val action = ListFragmentDirections.actionToDetail()
        // action.characterId = model.id
        // Navigation.findNavController(binding.root).navigate(action)
    }
}
