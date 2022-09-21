package com.example.recipesfood

import com.example.recipesfood.retrofit.MealApiCall
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api:MealApiCall by lazy {
        Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(MealApiCall::class.java)

    }
}