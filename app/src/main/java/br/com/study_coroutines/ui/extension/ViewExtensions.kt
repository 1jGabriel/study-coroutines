package br.com.study_coroutines.ui.extension

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.study_coroutines.ui.adapter.AdapterObject
import br.com.study_coroutines.ui.adapter.GenericAdapter
import br.com.study_coroutines.ui.model.AppAdapterListener
import com.bumptech.glide.Glide

@BindingAdapter("app:visible")
fun View.setVisibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("app:refresh")
fun SwipeRefreshLayout.refresh(lmbd: (() -> Unit)) {
    this.setOnRefreshListener { lmbd.invoke() }
}

@BindingAdapter(value = ["app:list", "app:listener"])
fun RecyclerView.setupAdapter(list: List<AdapterObject>, listener: AppAdapterListener<AdapterObject>?) {
    this.adapter = GenericAdapter(list, listener)
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