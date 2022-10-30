package com.example.allRecipes.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.allRecipes.R
import com.example.allRecipes.adapter.CategoryAdapter
import com.example.allRecipes.adapter.MostPopularAdapter
import com.example.allRecipes.adapter.myOnItemLongClick
import com.example.allRecipes.models.Category
import com.example.allRecipes.models.Meal
import com.example.allRecipes.models.MealsByCategory
import com.example.allRecipes.mvvm.HomeViewModel
import com.example.allRecipes.ui.CategoryActivity
import com.example.allRecipes.ui.MainActivity
import com.example.allRecipes.ui.MealActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),MostPopularAdapter.OnItemClickListener,
    CategoryAdapter.OnclickItemCategory,myOnItemLongClick{

    private lateinit var dialog: ProgressDialog
    private lateinit var mostPopularAdapter:MostPopularAdapter
    private lateinit var categoryAdapter:CategoryAdapter
    private lateinit var viewModel:HomeViewModel

    companion object {
        const val MEAL_ID = "meal_id"
        const val MEAL_THUMB = "meal_thumb"
        const val MEAL_NAME = "meal_name"
        const val MEAL_CATEGORY="category"
    }

    private lateinit var mMealData: Meal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =(activity as MainActivity).viewModel
        mostPopularAdapter=MostPopularAdapter(this,this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToMealActivity()
        viewModel.getDataFromApi()
        observerMeal()

        viewModel.getPopularItem()
        observerPopularItem()

        viewModel.getCategory()
        observerCategory()


    }



    private fun observerCategory() {
        viewModel.getLiveDataCategory().observe(viewLifecycleOwner){
            categoryAdapter=CategoryAdapter(this)
            categoryAdapter.setList(it)
            recycler_categories.adapter=categoryAdapter
            recycler_categories.layoutManager=GridLayoutManager(context,3)

        }
    }


    private fun goToMealActivity() {
        card_image.setOnClickListener {
            var intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_NAME, mMealData.strMeal)
            intent.putExtra(MEAL_THUMB, mMealData.strMealThumb)
            intent.putExtra(MEAL_ID, mMealData.idMeal)
            startActivity(intent)
        }
    }

    private fun showDialog() {
        dialog = ProgressDialog(context)
        dialog.setTitle("Loading")
        dialog.show()
    }

    private fun observerMeal() {
        showDialog()
        viewModel.getLiveData().observe(viewLifecycleOwner) { mMeal ->

            Glide.with(this).load(mMeal.strMealThumb).into(image_main)
            this.mMealData = mMeal
            dialog.dismiss()
        }
        dialog.dismiss()
    }
    private fun observerPopularItem(){
        viewModel.getLiveDataMealsByCategory().observe(viewLifecycleOwner){itemLis ->
            mostPopularAdapter= MostPopularAdapter(this,this)
            mostPopularAdapter.setMeal(itemLis)
            recycler_items.adapter=mostPopularAdapter
            recycler_items.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,
                false)
        }
    }


    override fun onItemClick(mealsByCategory: MealsByCategory) {
        val intent=Intent(activity,MealActivity::class.java)
        intent.putExtra(MEAL_THUMB,mealsByCategory.strMealThumb)
        intent.putExtra(MEAL_ID,mealsByCategory.idMeal)
        intent.putExtra(MEAL_NAME,mealsByCategory.strMeal)
        startActivity(intent)
    }

    override fun onClickItemCat(catList: Category) {
        var intent=Intent(activity,CategoryActivity::class.java)
        intent.putExtra(MEAL_CATEGORY,catList.strCategory)
        startActivity(intent)
    }

    override fun myItemLongClick(meal:MealsByCategory) {

        BottomSheetFragment.newInstance(meal.idMeal).show(childFragmentManager,"MealDialog")
    }


}

