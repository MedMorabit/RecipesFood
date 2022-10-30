package com.example.allRecipes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.allRecipes.db.MealDatabase
import com.example.allRecipes.fragments.CategoriesFragment
import com.example.allRecipes.fragments.FavoriteFragment
import com.example.allRecipes.fragments.HomeFragment
import com.example.allRecipes.mvvm.HomeViewModel
import com.example.allRecipes.mvvm.HomeViewProviderFactory
import com.example.recipesfood.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel:HomeViewModel by lazy {
        var mealDb=MealDatabase.getInstanceDb(this)
        val factory=HomeViewProviderFactory(mealDb)
       ViewModelProvider(this,factory)[HomeViewModel::class.java]

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(HomeFragment())
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.myhome ->  setFragment(HomeFragment())
                R.id.categories -> setFragment(CategoriesFragment())
               R.id.favorites -> setFragment(FavoriteFragment())
                else -> false
            }
        }

    }




    private fun setFragment(frg:Fragment):Boolean{
      var frgManager=supportFragmentManager.beginTransaction()
      frgManager.replace(R.id.frame_main,frg)
      frgManager.commit()
      return true

  }
}