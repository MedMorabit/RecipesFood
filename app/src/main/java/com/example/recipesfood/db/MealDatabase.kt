package com.example.recipesfood.db

import android.content.Context
import androidx.room.*
import com.example.recipesfood.models.Meal

@Database( entities=[Meal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao():Dao
    companion object {
        @Volatile
        var instance: MealDatabase? = null

        @Synchronized
        fun getInstanceDb(context: Context): MealDatabase {
            if (instance == null) {
                instance =Room.databaseBuilder(context, MealDatabase::class.java, "Meal_db").
                fallbackToDestructiveMigration().build()
            }
            return instance as MealDatabase
        }
    }
}