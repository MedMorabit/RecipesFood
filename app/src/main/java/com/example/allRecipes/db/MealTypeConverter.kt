package com.example.allRecipes.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConverter {
    @TypeConverter
    fun fromAnyToString(att:Any?):String{
        if(att==null)
            return ""
        return att as String
    }
    @TypeConverter
    fun fromStringToAny(att:String?):Any{
        if(att==null)
            return ""
        return att
    }
}