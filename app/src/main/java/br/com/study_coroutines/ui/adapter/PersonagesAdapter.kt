package br.com.study_coroutines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.study_coroutines.R
import br.com.study_coroutines.ui.extension.loadImage
import br.com.study_coroutines.ui.model.AdapterClickListener
import br.com.study_coroutines.ui.model.CharacterUi
import kotlinx.android.synthetic.main.character_item_list.view.*

class PersonagesAdapter(
    private val clickAction: AdapterClickListener<CharacterUi>
) : PagingDataAdapter<CharacterUi, PersonagesAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item_list, parent, false)
        return MyViewHolder(view, clickAction)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class MyViewHolder(
        itemView: View,
        val clickAction: AdapterClickListener<CharacterUi>
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: CharacterUi) {

            itemView.name.text = character.name
            itemView.imageView.loadImage(character.image)
            itemView.setOnClickListener {
                clickAction.onItemClick(character)
            }
        }
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterUi>() {
    override fun areItemsTheSame(oldItem: CharacterUi, newItem: CharacterUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterUi, newItem: CharacterUi): Boolean {
        return oldItem.equals(newItem)
    }
}