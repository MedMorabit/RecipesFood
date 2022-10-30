package com.example.allRecipes.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allRecipes.RetrofitInstance
import com.example.allRecipes.db.MealDatabase
import com.example.allRecipes.models.Meal
import com.example.allRecipes.models.MealsList
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  MealViewModel(private val mealDataBase:MealDatabase) : ViewModel() {
    private val mutableLiveData=MutableLiveData<Meal>()

    fun getMealDetails(id:String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object: Callback<MealsList>{
            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
               if(response.body()!=null){
                   mutableLiveData.value= response.body()!!.meals[0]
               }else
                   return
            }
            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.d("Meal",t.message.toString())
            }

        })
    }
    fun getLiveData():LiveData<Meal>{
       return mutableLiveData
   }
    fun insertMeal(meal:Meal){
    viewModelScope.launch {
        mealDataBase.mealDao().upset(meal)
    }
}
    fun deleteMeal(meal:Meal){
        viewModelScope.launch {
            mealDataBase.mealDao().deleteMeal(meal)
        }
    }

}