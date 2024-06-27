package com.example.earthquake_tracker
import models.Feature
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import models.Terremoto


class Adapter() : ListAdapter<Terremoto, Adapter.ViewHolder>(DiffCallBack) {

    lateinit var onItemClickListener: (Terremoto) -> Unit

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nombreElem: TextView = view.findViewById(R.id.nameView)
        val magElem: TextView = view.findViewById(R.id.magnitudeView)

        fun bind (quake: Terremoto) {
            nombreElem.text=quake.title
            magElem.text=quake.mag

            view.setOnClickListener{
                onItemClickListener(quake)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val quake = getItem(position)
        holder.bind(quake)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Terremoto>() {
        override fun areItemsTheSame(oldItem: Terremoto, newItem: Terremoto): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Terremoto, newItem: Terremoto): Boolean {
            return oldItem == newItem
        }
    }
}