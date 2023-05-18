package me.proton.jobforandroid.gbandroidpro.view.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.proton.jobforandroid.gbandroidpro.databinding.ActivityHistoryRecyclerviewItemBinding
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel

class HistoryAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ActivityHistoryRecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: ActivityHistoryRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) {
            binding.apply {
                if (layoutPosition != RecyclerView.NO_POSITION) {
                    headerHistoryTextviewRecyclerItem.text = data.text
                    itemView.setOnClickListener { openInNewWindow(data) }
                }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}