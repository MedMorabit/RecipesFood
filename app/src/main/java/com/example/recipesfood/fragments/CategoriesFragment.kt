package com.example.recipesfood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.recipesfood.R
import com.example.recipesfood.adapter.CategoriesFragmentAdapter
import com.example.recipesfood.models.Category
import com.example.recipesfood.mvvm.HomeViewModel
import com.example.recipesfood.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_categories.*
import java.util.ArrayList

class CategoriesFragment : Fragment() {
lateinit var categoryFragmentAdapter:CategoriesFragmentAdapter
lateinit var viewModel:HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        viewModel.getCategory()
        observeLiveDataCategory()

    }

    private fun observeLiveDataCategory() {
        viewModel.getLiveDataCategory().observe(viewLifecycleOwner){
            categoryFragmentAdapter.setList(it)
        }
    }

    private fun setRecyclerView() {
        categoryFragmentAdapter= CategoriesFragmentAdapter()
        recycler_category_fragment.apply {
            adapter=categoryFragmentAdapter
            layoutManager=GridLayoutManager(context,3)
        }
    }
}