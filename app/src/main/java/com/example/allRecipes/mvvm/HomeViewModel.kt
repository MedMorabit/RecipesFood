package com.example.allRecipes.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allRecipes.RetrofitInstance
import com.example.allRecipes.db.MealDatabase
import com.example.allRecipes.models.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel (private val mealDatabase:MealDatabase): ViewModel(){
    private var mutableData = MutableLiveData<Meal>()
    private val popularMutableCategory=MutableLiveData<ArrayList<MealsByCategory>>()
    private val categoryLiveData=MutableLiveData<ArrayList<Category>>()
    private val bottomSheetMutableLiveData=MutableLiveData<Meal>()
    private val favoriteMealLiveData=mealDatabase.mealDao().getAllMeal()
    private var saveRandomMeal:Meal?=null
    fun getDataFromApi(){
       saveRandomMeal?.let {
           mutableData.postValue(it)
           return
       }
        RetrofitInstance.api.getMeals().enqueue(object : Callback<MealsList> {
            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                if(response.body()!=null){
                    val meal= response.body()!!.meals[0]
                    mutableData.value=meal
                    saveRandomMeal=meal
                }else
                    return
            }
            override fun onFailure(call: Call<MealsList>, t: Throwable) {
             Log.d("errorHome",t.message.toString())
            }

        })
    }
    fun getLiveData():LiveData<Meal>{
        return mutableData
    }
    fun getPopularItem(){
        RetrofitInstance.api.getPopularItem("Seafood").enqueue(object : Callback<MealsByCategoryList>{
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                popularMutableCategory.value=response.body()?.meals
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("HomeMain",t.message.toString())
            }

        })
    }
    fun getCategory(){
        RetrofitInstance.api.getCategory().enqueue(object :Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body().let {
                    categoryLiveData.postValue(it?.categories)

                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("CategoryMeal",t.message.toString())
            }

        })
    }
    fun getMealById(id:String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object :Callback<MealsList>{
            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                val meal= response.body()!!.meals[0]
                meal.let {nowMeal->
                    bottomSheetMutableLiveData.postValue(nowMeal)
                }
            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    fun getBottomMealLiveData():LiveData<Meal>{
        return bottomSheetMutableLiveData
    }

    fun getLiveDataMealsByCategory():LiveData<ArrayList<MealsByCategory>>{
        return popularMutableCategory
    }
    fun getLiveDataCategory():LiveData<ArrayList<Category>>{
        return categoryLiveData
    }
    fun observeFavoriteMeal():LiveData<List<Meal>>{
        return favoriteMealLiveData
    }
    fun deleteMeal(meal:Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().deleteMeal(meal)
        }
    }
    fun insertMeal(meal:Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().upset(meal)
        }
    }
}