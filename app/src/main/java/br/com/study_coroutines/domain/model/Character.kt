package br.com.study_coroutines.domain.model

import br.com.study_coroutines.R
import br.com.study_coroutines.ui.adapter.AdapterObject

data class Character(val id: Int, val image: String, val name: String) : AdapterObject {
    override fun layoutId(): Int {
        return R.layout.character_item_list
    }
}
