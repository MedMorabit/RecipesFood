package com.example.allRecipes.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.allRecipes.R
import com.example.allRecipes.mvvm.HomeViewModel
import com.example.allRecipes.ui.MainActivity
import com.example.allRecipes.ui.MealActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*


private const val MEAL_ID = "mealId"

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var idMeal: String? = null
    lateinit var viewModel: HomeViewModel
    private var mealName:String?=null
    private var mealThumb:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idMeal = it.getString(MEAL_ID)
        }
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    companion object {

        fun newInstance(myIdMeal: String) =
            BottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(MEAL_ID, myIdMeal)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idMeal?.let { viewModel.getMealById(it) }
        observerBottomMeal()
        onClickBottomSheet()
    }

    private fun onClickBottomSheet() {
    bottomSheetId.setOnClickListener {
        if(mealName!=null && mealThumb!=null){
        val intent=Intent(activity,MealActivity::class.java)
        intent.apply {
            putExtra(HomeFragment.MEAL_ID,idMeal)
            putExtra(HomeFragment.MEAL_NAME,mealName)
            putExtra(HomeFragment.MEAL_THUMB,mealThumb)
        }
            startActivity(intent)
        }
    }
    }

    private fun observerBottomMeal() {
        viewModel.getBottomMealLiveData().observe(viewLifecycleOwner) {
            tv_area_bottomSheet.text = it.strArea
            tv_category_bottomSheet.text = it.strCategory
            Glide.with(this).load(it.strMealThumb).into(image_bottomSheet)
            tv_mealName_bottomSheet.text = it.strMeal
            mealName=it.strMeal
            mealThumb=it.strMealThumb
        }
    }

}