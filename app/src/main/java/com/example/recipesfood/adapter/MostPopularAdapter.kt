package com.example.recipesfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipesfood.R
import com.example.recipesfood.models.MealsByCategory

class MostPopularAdapter(
    var listener: OnItemClickListener,
    private var listenerLongClick: myOnItemLongClick
) :
    RecyclerView.Adapter<MostPopularAdapter.MyViewHolder>() {
    private var mealsByCategoryList = ArrayList<MealsByCategory>()
    fun setMeal(mealsByCategoryList: ArrayList<MealsByCategory>) {
        this.mealsByCategoryList = mealsByCategoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.popular_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView).load(mealsByCategoryList[position].strMealThumb)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            listener.onItemClick(mealsByCategoryList[position])
        }
      holder.itemView.setOnLongClickListener {
          listenerLongClick.myItemLongClick(mealsByCategoryList[position])
          true
      }
    }

    override fun getItemCount(): Int {
        return mealsByCategoryList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById(R.id.popular_Image) as ImageView
    }

    interface OnItemClickListener {
        fun onItemClick(mealsByCategory: MealsByCategory)
    }
}
    interface myOnItemLongClick {
    fun myItemLongClick(meal: MealsByCategory)
}