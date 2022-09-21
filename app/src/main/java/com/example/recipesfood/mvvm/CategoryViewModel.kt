package com.example.recipesfood.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesfood.RetrofitInstance
import com.example.recipesfood.models.Category
import com.example.recipesfood.models.MealsByCategory
import com.example.recipesfood.models.MealsByCategoryList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel : ViewModel() {
   private var mutableLiveData=MutableLiveData<ArrayList<MealsByCategory>>()
    fun getMealsByCategory(category:String) {
        RetrofitInstance.api.getCategories(category)
            .enqueue(object : Callback<MealsByCategoryList> {
                override fun onResponse(
                    call: Call<MealsByCategoryList>,
                    response: Response<MealsByCategoryList>
                ) {
                   response.body().let {
                       mutableLiveData.postValue(it?.meals)
                   }

                }

                override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                    Log.d("categoryError",t.message.toString())
                }

            })
    }
    fun getLiveDataCategory():LiveData<ArrayList<MealsByCategory>>{
        return mutableLiveData
    }
}