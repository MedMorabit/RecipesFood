package com.example.recipesfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipesfood.R
import com.example.recipesfood.models.Category
import com.example.recipesfood.models.MealsByCategory
import kotlinx.android.synthetic.main.all_category_items.view.*

class CategoryByMealAdapter : RecyclerView.Adapter<CategoryByMealAdapter.CategoryViewHolder>() {
      private var catList=ArrayList<MealsByCategory>()
    fun setList(catList:ArrayList<MealsByCategory>){
        this.catList=catList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.all_category_items,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView).
        load(catList[position].strMealThumb)
            .into(holder.itemView.image_all_category)
        holder.itemView.tv_all_category.text=catList[position].strMeal
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    inner class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }
}