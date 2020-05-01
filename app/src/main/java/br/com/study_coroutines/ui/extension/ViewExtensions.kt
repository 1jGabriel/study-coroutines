package br.com.study_coroutines.ui.extension

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.study_coroutines.ui.adapter.AdapterObject
import br.com.study_coroutines.ui.adapter.GenericAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:visible")
fun View.setVisibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("app:refresh")
fun SwipeRefreshLayout.refresh(lmbd: (() -> Unit)) {
    this.setOnRefreshListener { lmbd.invoke() }
}

@BindingAdapter("app:list")
fun RecyclerView.setupAdapter(list: List<AdapterObject>) {
    this.adapter = GenericAdapter(mList = list)
}

@BindingAdapter("android:imageUrl")
fun ImageView.loadImage(url: String?) {

    val circularProgressDrawable = CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }

    Glide.with(context)
        .load(url)
        .placeholder(circularProgressDrawable)
        .into(this)
}
