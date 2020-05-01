package br.com.study_coroutines.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import br.com.study_coroutines.ui.model.AdapterClickListener

class GenericAdapter<T : AdapterObject>(
    var mList: List<T> = emptyList(),
    var listener: AdapterClickListener<T>? = null
) : RecyclerView.Adapter<GenericAdapter<T>.ViewHolder<ViewDataBinding>>() {

    private var searchList: List<T> = mList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ViewDataBinding> {
        val bind: ViewDataBinding =
            DataBindingUtil.bind(LayoutInflater.from(parent.context).inflate(viewType, parent, false))!!
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder<ViewDataBinding>, position: Int) {
        val model: T = searchList[position]
        holder.getBinding().setVariable(BR.model, model)
        holder.getBinding().executePendingBindings()

        holder.getBinding().root.setOnClickListener {
            listener?.onItemClick(model, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return searchList[position].layoutId()
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class ViewHolder<V : ViewDataBinding>(private val view: V) : RecyclerView.ViewHolder(view.root) {

        fun getBinding(): V {
            return view
        }
    }
}
