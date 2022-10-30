package com.example.allRecipes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allRecipes.R
import com.example.allRecipes.models.Meal
import kotlinx.android.synthetic.main.favorites_item.view.*

class FavoriteMealAdapter: RecyclerView.Adapter<FavoriteMealAdapter.FavoriteViewHolder>() {
    var mList=ArrayList<Meal>()
    lateinit var meal:Meal

   fun setList(mList: ArrayList<Meal>){
       this.mList=mList
       notifyDataSetChanged()
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
       return FavoriteViewHolder(LayoutInflater
           .from(parent.context).inflate(R.layout.favorites_item,parent,false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        meal=mList[position]
        holder.itemView.tv_favorite_meal.text=meal.strMeal
        Glide.with(holder.itemView).load(meal.strMealThumb).into(holder.itemView.favorite_image)
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    inner class FavoriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}