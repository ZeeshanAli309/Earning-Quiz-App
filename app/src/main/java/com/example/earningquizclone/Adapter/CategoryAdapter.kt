package com.example.earningquizclone.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earningquizclone.Model.CategoryModelClass
import com.example.earningquizclone.QuizActivty
import com.example.earningquizclone.databinding.CategoryitemBinding

class CategoryAdapter(var categoryList:ArrayList<CategoryModelClass>,var context:Context): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: CategoryitemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var dataList=categoryList.get(position)
        holder.binding.categoryImg.setImageResource(dataList.catImage)
        holder.binding.categoryText.text=dataList.catTExt
        holder.binding.categoryBtn.setOnClickListener {
            var intent=Intent(context,QuizActivty::class.java)
            intent.putExtra("categoryImg",dataList.catImage)
            context.startActivity(intent)
        }

    }
}