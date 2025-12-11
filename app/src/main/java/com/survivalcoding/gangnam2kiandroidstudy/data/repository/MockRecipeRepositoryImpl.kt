package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeSearchCondition

object MockRecipeRepositoryImpl : RecipeRepository {

    val mockRecipes = listOf(
        Recipe(
            name = "Traditional spare ribs baked",
            imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chef = "Chef John",
            time = 20,
            rating = 4.0,
        ),
        Recipe(
            name = "Spice roasted chicken with flavored rice",
            imageUrl = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            chef = "Mark Kelvin",
            time = 20,
            rating = 4.0,
        ),
        Recipe(
            name = "Spicy fried rice mix chicken bali",
            imageUrl = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
            chef = "Spicy Nelly",
            time = 20,
            rating = 4.0,
        ),
        Recipe(
            name = "Ttekbokki",
            imageUrl = "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
            chef = "Kim Dahee",
            time = 30,
            rating = 5.0,
        ),
        Recipe(
            name = "Grilled salmon with avocado salsa",
            imageUrl = "https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_1280.jpg",
            chef = "Alice Johnson",
            time = 25,
            rating = 4.5,
        ),
        Recipe(
            name = "Beef Wellington",
            imageUrl = "https://cdn.pixabay.com/photo/2019/10/22/10/11/beef-wellington-4568239_1280.jpg",
            chef = "Gordon Ramsay",
            time = 45,
            rating = 5.0,
        ),
        Recipe(
            name = "Classic Margherita Pizza",
            imageUrl = "https://cdn.pixabay.com/photo/2019/05/15/18/56/pizza-4205701_1280.jpg",
            chef = "Mario Batali",
            time = 15,
            rating = 4.3,
        ),
        Recipe(
            name = "Sushi Platter",
            imageUrl = "https://cdn.pixabay.com/photo/2017/10/15/11/41/sushi-2853382_1280.jpg",
            chef = "Jiro Ono",
            time = 60,
            rating = 4.8,
        ),
        Recipe(
            name = "French Onion Soup",
            imageUrl = "https://cdn.pixabay.com/photo/2016/03/03/16/19/food-1234483_1280.jpg",
            chef = "Julia Child",
            time = 40,
            rating = 4.6,
        ),
        Recipe(
            name = "Chocolate Lava Cake",
            imageUrl = "https://cdn.pixabay.com/photo/2016/11/22/18/52/cake-1850011_1280.jpg",
            chef = "Paul Hollywood",
            time = 30,
            rating = 4.9,
        ),
    )

    override suspend fun getSavedRecipes(): AppResult<List<Recipe>, NetworkError> =
        AppResult.Success(mockRecipes)

    override suspend fun getRecipes(searchCondition: RecipeSearchCondition): AppResult<List<Recipe>, NetworkError> =
        AppResult.Success(mockRecipes)
}