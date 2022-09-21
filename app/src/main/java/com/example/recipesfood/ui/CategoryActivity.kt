package com.example.recipesfood.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipesfood.R
import com.example.recipesfood.adapter.CategoryByMealAdapter
import com.example.recipesfood.fragments.HomeFragment
import com.example.recipesfood.mvvm.CategoryViewModel
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.all_category_items.*

class CategoryActivity : AppCompatActivity() {
    lateinit var categoryByMealAdapter:CategoryByMealAdapter
    lateinit var categoryViewModel:CategoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setRecyclerAdapter()
        categoryViewModel= ViewModelProvider(this)[CategoryViewModel::class.java]
        val data= intent.extras?.get(HomeFragment.MEAL_CATEGORY).toString()
        categoryViewModel.getMealsByCategory(data)
        categoryViewModel.getLiveDataCategory().observe(this){mealList->
          categoryByMealAdapter.setList(mealList)
            cat_title.text= mealList.size.toString()
        }

    }

    private fun setRecyclerAdapter() {
     categoryByMealAdapter= CategoryByMealAdapter()

        rv_category.apply {
            adapter=categoryByMealAdapter
            layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        }


    }


}