package com.lucascampos.testing.repository

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.lucascampos.testing.R
import com.lucascampos.testing.data.model.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter(private val repositories: List<Repository>) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false))


    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repository: Repository) {
            itemView.name.text = repository.name

            Glide.with(itemView.context)
                    .load(repository.owner.avatar)
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .into(itemView.icon)
        }

    }

}