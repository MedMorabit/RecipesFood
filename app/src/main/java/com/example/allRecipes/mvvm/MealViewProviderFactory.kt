package com.example.allRecipes.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.allRecipes.db.MealDatabase

class MealViewProviderFactory(private val mealDb: MealDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealViewModel(mealDb) as T
    }

}
