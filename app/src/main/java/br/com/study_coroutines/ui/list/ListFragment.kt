package br.com.study_coroutines.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import br.com.study_coroutines.databinding.ListFragmentBinding
import br.com.study_coroutines.domain.model.Character
import br.com.study_coroutines.ui.ViewAction
import br.com.study_coroutines.ui.adapter.AdapterObject
import br.com.study_coroutines.ui.model.AppAdapterListener
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.ext.android.inject

class ListFragment : Fragment(), AppAdapterListener<AdapterObject> {

    private val viewModel: ListCharactersViewModel by inject()
    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ListFragmentBinding.inflate(inflater, container, false).apply {
        binding = this
        clickListener = this@ListFragment
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

        binding.clickListener = this
    }

    override fun onItemClick(model: AdapterObject, position: Int) {
        val action = ListFragmentDirections.actionToDetail()
        action.characterId = (model as Character).id
        Navigation.findNavController(binding.root).navigate(action)
    }
}
