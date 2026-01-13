package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.savedrecipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.SavedRecipeCardBinding
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard

class RecipeRecyclerAdapter(
    private val listener: SavedRecipesActionListener,
) : ListAdapter<Recipe, RecipeRecyclerAdapter.ViewHolder>(diffUtil) {

    /*
    saved_recipe_card 레이아웃을 ViewHolder 에 주입 후 생성
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.saved_recipe_card, parent, false)
        return ViewHolder(
            SavedRecipeCardBinding.bind(view),
//            onClick = {
//                listener.onCardClick(currentList[it].id)
//            },
//            onBookmarkClick = {
//                listener.onBookmarkClick(currentList[it].id)
//            },
        )
    }

    /*
    RecipeCard 데이터 설정
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.savedRecipeCard.setContent {
            RecipeCard(
                recipe = currentList[position],
                onClick = listener::onCardClick,
                onBookmarkClick = listener::onBookmarkClick,
            )
        }
    }

    class ViewHolder(
        val binding: SavedRecipeCardBinding,
        val onClick: (Int) -> Unit = {},
        val onBookmarkClick: (Int) -> Unit = {},
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.savedRecipeCard.setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed,
            )

//            itemView.setOnClickListener {
//                onClick(bindingAdapterPosition)
//            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }
        }
    }
}