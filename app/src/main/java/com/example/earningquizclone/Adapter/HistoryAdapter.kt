package com.example.earningquizclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earningquizclone.Model.ModelHistoryClass
import com.example.earningquizclone.databinding.FragmentSpinBinding
import com.example.earningquizclone.databinding.HistoryitemsBinding

class HistoryAdapter(var listHistory:ArrayList<ModelHistoryClass>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: HistoryitemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     return ViewHolder(HistoryitemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
  holder.binding.Time.text=listHistory[position].timeAndDate
        holder.binding.coin.text=listHistory[position].coin
    }
}