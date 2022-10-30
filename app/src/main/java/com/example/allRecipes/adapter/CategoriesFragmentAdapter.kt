package com.example.allRecipes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allRecipes.R
import com.example.allRecipes.models.Category
import kotlinx.android.synthetic.main.item_categories_fragment.view.*

class CategoriesFragmentAdapter:RecyclerView.Adapter<CategoriesFragmentAdapter.MyCategoryHolder>(){
    var mList=ArrayList<Category>()
    fun setList(mList: ArrayList<Category>){
        this.mList=mList
    }
    inner class MyCategoryHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoryHolder {
        return MyCategoryHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categories_fragment, parent, false))
    }

    override fun onBindViewHolder(holder: MyCategoryHolder, position: Int) {
        var meal=mList[position]
        holder.itemView.tv_categories_frg.text=meal.strCategory
        Glide.with(holder.itemView).load(meal.strCategoryThumb).into(holder.itemView.image_categories_frg)
    }

    override fun getItemCount(): Int {
     return mList.size
    }
}