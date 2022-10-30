package com.example.allRecipes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allRecipes.R
import com.example.allRecipes.models.Category

import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter(var listener:OnclickItemCategory) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private lateinit var categoryList:ArrayList<Category>

    fun setList(categoryList:ArrayList<Category>){
        this.categoryList=categoryList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.
        from(parent.context).inflate(R.layout.category_item,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var catList=categoryList[position]
        holder.itemView.category_title.text=catList.strCategory
        Glide.with(holder.itemView).load(catList.strCategoryThumb).into(holder.itemView.image_category)
        holder.itemView.setOnClickListener {
            listener.onClickItemCat(catList)
        }
    }

    override fun getItemCount(): Int {
     return categoryList.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
    interface OnclickItemCategory{
        fun onClickItemCat(catList:Category)
    }
}