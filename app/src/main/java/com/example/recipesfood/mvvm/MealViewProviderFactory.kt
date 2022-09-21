package com.example.recipesfood.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipesfood.db.MealDatabase

class MealViewProviderFactory(private val mealDb: MealDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealViewModel(mealDb) as T
    }

}
