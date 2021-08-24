package com.robby.ministockbit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robby.ministockbit.R
import com.robby.ministockbit.databinding.ItemHomeBinding
import com.robby.ministockbit.data.local.CryptoEntity
import com.robby.ministockbit.databinding.ItemLoadMoreBinding
import com.robby.ministockbit.utils.Const
import com.robby.ministockbit.utils.changeColorText
import com.robby.ministockbit.utils.formatDoubleWithPrefix

class HomeAdapter(private var listData: List<CryptoEntity>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private lateinit var binding : ItemHomeBinding
    private lateinit var bindingLoadMore : ItemLoadMoreBinding
    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return if (viewType == Const.RecyclerViewItem.VIEW_TYPE_ITEM) {
            binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding.root)
        } else {
            bindingLoadMore = ItemLoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(bindingLoadMore.root)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType == Const.RecyclerViewItem.VIEW_TYPE_ITEM) {
            holder.bind(listData[position])
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : CryptoEntity) {
            with(binding) {

                tvName.text = data.name
                tvFullname.text = data.fullname
                tvPercent.text = context.getString(R.string.label_percent,
                    data.changeHour.formatDoubleWithPrefix(),
                    data.changePCTHour.formatDoubleWithPrefix())
                tvPrice.text = data.price

                tvPercent.changeColorText(data.changeHour.toDouble(), context)
            }
        }
    }
}
