package me.proton.jobforandroid.gbandroidpro.view.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.proton.jobforandroid.gbandroidpro.R
import me.proton.jobforandroid.gbandroidpro.databinding.ActivityMainRecyclerviewItemBinding
import me.proton.jobforandroid.gbandroidpro.model.repository.entity.DataModel

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ActivityMainRecyclerviewItemBinding.inflate(
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

    inner class RecyclerItemViewHolder(private val binding: ActivityMainRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerTextviewRecyclerItem.text = data.text
                binding.descriptionTextviewRecyclerItem.text =
                    data.meanings?.get(0)?.translation?.translation

                itemView.setOnClickListener { openInNewWindow(data) }
                setImg("https:" + (data.meanings?.get(0)?.imageUrl))
            }
        }

        private fun setImg(url: String) = with(binding) {
            ivImg.load(url) {
                error(R.drawable.ic_load_error_vector)
                placeholder(R.drawable.ic_no_photo_vector)
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
