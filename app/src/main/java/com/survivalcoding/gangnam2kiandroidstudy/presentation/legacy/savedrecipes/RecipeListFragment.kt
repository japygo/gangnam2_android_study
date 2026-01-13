package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.savedrecipes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeListBinding
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes.SavedRecipesAction
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes.SavedRecipesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SavedRecipesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecipeListBinding.bind(view)

        val adapter = RecipeRecyclerAdapter(
            object : SavedRecipesActionListener {
                override fun onCardClick(recipeId: Long) {
                    /*
                    Activity 상태 검증
                     */
                    if (!isAdded || activity?.isFinishing == true || activity?.isDestroyed == true) {
                        return
                    }

                    /*
                    화면 전환 직후 앱이 백그라운드로 가거나 onSaveInstanceState() 호출 이후 등의 상황에서 에러 방지
                     */
                    try {
                        /*
                        RecipeDetailFragment 로 전환
                         */
                        parentFragmentManager.beginTransaction()
                            .replace(
                                R.id.fragment_container,
                                RecipeDetailFragment.newInstance(recipeId),
                            )
                            .addToBackStack(null) // 뒤로가기 지원
                            .commitAllowingStateLoss()
                    } catch (e: IllegalStateException) {
                        Log.e("RecipeListFragment", e.message, e)
                    }
                }

                override fun onBookmarkClick(recipeId: Long) {
                    viewModel.onAction(SavedRecipesAction.OnBookmarkClick(recipeId))
                }
            },
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        /*
        View 의 라이프사이클에 따르는 scope
         */
        viewLifecycleOwner.lifecycleScope.launch {
            /*
            화면이 보일 때만 컬렉트 하도록 사용
             */
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                /*
                UI 상태는 마지막 상태로 보여주면 됨
                 */
                viewModel.uiState.collectLatest {
                    adapter.submitList(it.recipes)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /*
        죽을 때 메모리 해제
         */
        _binding = null
    }
}