package com.android.modular_views_mobile.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.modular_m_vm.models.data.Person
import com.android.modular_views_mobile.R
import timber.log.Timber


class PeopleAdapter() :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    private val items: MutableList<Person> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    fun setItems(newItems: List<Person>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tv = itemView.findViewById<TextView>(R.id.tv_item_person)

        init {
            itemView.setOnClickListener { }
        }

        fun onBind() {
            Timber.d("onBind layoutPosition $layoutPosition size ${items.size}")
            if (layoutPosition > -1 && layoutPosition < items.size) {
                val item = items[layoutPosition % items.size]
                tv.text = item.name
            }
        }
    }
}