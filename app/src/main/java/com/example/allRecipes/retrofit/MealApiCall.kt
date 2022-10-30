package com.example.allRecipes.retrofit

import com.example.allRecipes.models.CategoryList
import com.example.allRecipes.models.MealsByCategoryList
import com.example.allRecipes.models.MealsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiCall {
    @GET("random.php")
    fun getMeals(): Call<MealsList>
    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id:String): Call<MealsList>
    @GET("filter.php?")
    fun getPopularItem(@Query("c")categoryName:String):Call<MealsByCategoryList>
    @GET("categories.php")
    fun getCategory():Call<CategoryList>
    @GET("filter.php")
    fun getCategories(@Query("c")CategoryName:String):Call<MealsByCategoryList>
}