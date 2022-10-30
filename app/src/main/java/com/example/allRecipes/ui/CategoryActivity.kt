package com.example.allRecipes.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.allRecipes.R
import com.example.allRecipes.adapter.CategoryByMealAdapter
import com.example.allRecipes.fragments.HomeFragment
import com.example.allRecipes.mvvm.CategoryViewModel
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

    @SuppressLint("SuspiciousIndentation")
    private fun setRecyclerAdapter() {
     categoryByMealAdapter= CategoryByMealAdapter()

        rv_category.apply {
            adapter=categoryByMealAdapter
            layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        }


    }


}