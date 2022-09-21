package com.example.recipesfood.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.recipesfood.models.Meal

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upset(meal:Meal)
    @Query("SELECT * FROM mealTable ")
    fun getAllMeal():LiveData<List<Meal>>
    @Delete
    suspend fun deleteMeal(meal:Meal)
    @Query("delete from mealTable")
    suspend fun deleteALLMeal()

}