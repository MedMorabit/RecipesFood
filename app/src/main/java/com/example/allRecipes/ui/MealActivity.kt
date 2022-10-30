package com.example.allRecipes.ui

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.allRecipes.R
import com.example.allRecipes.db.MealDatabase
import com.example.allRecipes.fragments.HomeFragment
import com.example.allRecipes.models.Meal
import com.example.allRecipes.mvvm.MealViewModel
import com.example.allRecipes.mvvm.MealViewProviderFactory
import kotlinx.android.synthetic.main.activity_meal.*

class MealActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var link:String
    private lateinit var thumb:String
    private lateinit var title:String
    private lateinit var id:String
    private lateinit var meal: Meal
    private lateinit var mealMvvm:MealViewModel
    lateinit var dialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
        showDialog()
        getDataFromIntent()
        val mealDb=MealDatabase.getInstanceDb(this)
        val mealViewModelFactory= MealViewProviderFactory(mealDb)
        mealMvvm= ViewModelProvider(this,mealViewModelFactory)[MealViewModel::class.java]
        mealMvvm.getMealDetails(id)
        observerData()
        ytbLink.setOnClickListener(this)
        onFavoriteClick()
    }

    private fun showDialog() {
        dialog= ProgressDialog(this)
        dialog.setTitle("Loading")
        dialog.show()
    }

    private fun getDataFromIntent() {
        thumb=intent.extras?.get(HomeFragment.MEAL_THUMB).toString()
        id=intent.extras?.get(HomeFragment.MEAL_ID).toString()
        title=intent.extras?.get(HomeFragment.MEAL_NAME).toString()
        Glide.with(this).load(thumb).into(image_meal_details)
        collapsingToolbar.title=title
        collapsingToolbar.setExpandedTitleColor(Color.WHITE)
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)

    }

    override fun onClick(p0: View?) {
        if(p0==ytbLink){
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        }
    }
    private fun observerData(){

        mealMvvm.getLiveData().observe(this
        ) {
            setDataInUi(it)
        }
    }

    private fun setDataInUi(it: Meal?) {
        tv_category.text="Category:${it?.strCategory}"
        tv_area.text="Area:${it?.strArea}"
        instruction_body.text=it?.strInstructions
        link= it!!.strYoutube.toString()
        dialog.dismiss()
        meal=it

    }
    private fun onFavoriteClick(){
        floating_btn_favorite.setOnClickListener {
            mealMvvm.insertMeal(meal)
            Toast.makeText(baseContext, "Meal Saved", Toast.LENGTH_SHORT).show()
        }
    }
}