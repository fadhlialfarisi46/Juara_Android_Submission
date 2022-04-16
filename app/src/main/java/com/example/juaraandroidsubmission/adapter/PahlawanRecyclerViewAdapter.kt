package com.example.juaraandroidsubmission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.juaraandroidsubmission.R
import com.example.juaraandroidsubmission.data.local.Pahlawan
import com.example.juaraandroidsubmission.databinding.ListPahlawanBinding

class PahlawanRecyclerViewAdapter : RecyclerView.Adapter<PahlawanRecyclerViewAdapter.ViewHolder>() {

    private var listPahlawan = ArrayList<Pahlawan>()
    var onItemClick: ((Pahlawan) -> Unit)? = null

    fun setData(newListData: List<Pahlawan>?) {
        if (newListData == null) return
        listPahlawan.clear()
        listPahlawan.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListPahlawanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pahlawan) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(item.imageAsset)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPahlawan)
                tvPahlawan.text = item.name.toString()
            }
          itemView.setOnClickListener {
              onItemClick?.invoke(listPahlawan[adapterPosition])
          }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListPahlawanBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPahlawan[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listPahlawan.size
}