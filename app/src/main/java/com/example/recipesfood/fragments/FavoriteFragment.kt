package com.example.recipesfood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesfood.R
import com.example.recipesfood.adapter.FavoriteMealAdapter
import com.example.recipesfood.models.Meal
import com.example.recipesfood.mvvm.HomeViewModel
import com.example.recipesfood.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
lateinit var viewModel:HomeViewModel
lateinit var favoriteAdapter: FavoriteMealAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFavoriteMeal()
        setRecycler()
        deleteItem()
    }

    private fun deleteItem() {
       var itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteMeal(favoriteAdapter.mList[position])
                Snackbar.make(requireView(),"Meal Deleted",Snackbar.LENGTH_LONG)
                    .setAction("Undo", View.OnClickListener {
                        viewModel.insertMeal(favoriteAdapter.mList[position])
                    }).show()

            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recycler_favorite)
    }

    private fun setRecycler() {
        favoriteAdapter= FavoriteMealAdapter()
        recycler_favorite.apply {
            adapter=favoriteAdapter
            layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        }
    }

    private fun observeFavoriteMeal(){
       viewModel.observeFavoriteMeal().observe(viewLifecycleOwner){
            favoriteAdapter.setList(it as ArrayList<Meal>)
       }
   }
}