package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchCondition
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

object MockRecipeRepositoryImpl : RecipeRepository {

    val mockRecipes = listOf(
        Recipe(
            id = 1,
            name = "Traditional spare ribs baked",
            imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chef = "Chef John",
            time = 20,
            rating = 4.0,
            chefImageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        ),
        Recipe(
            id = 2,
            name = "Spice roasted chicken with flavored rice",
            imageUrl = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            chef = "Mark Kelvin",
            time = 20,
            rating = 4.0,
            chefImageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        ),
        Recipe(
            id = 3,
            name = "Spicy fried rice mix chicken bali",
            imageUrl = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
            chef = "Spicy Nelly",
            time = 20,
            rating = 4.0,
            chefImageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        ),
        Recipe(
            id = 4,
            name = "Ttekbokki",
            imageUrl = "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
            chef = "Kim Dahee",
            time = 30,
            rating = 5.0,
            chefImageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        ),
        Recipe(
            id = 5,
            name = "Grilled salmon with avocado salsa",
            imageUrl = "https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_1280.jpg",
            chef = "Alice Johnson",
            time = 25,
            rating = 4.5,
            chefImageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        ),
        Recipe(
            id = 6,
            name = "Beef Wellington",
            imageUrl = "https://cdn.pixabay.com/photo/2019/10/22/10/11/beef-wellington-4568239_1280.jpg",
            chef = "Gordon Ramsay",
            time = 45,
            rating = 5.0,
            chefImageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        ),
        Recipe(
            id = 7,
            name = "Classic Margherita Pizza",
            imageUrl = "https://cdn.pixabay.com/photo/2019/05/15/18/56/pizza-4205701_1280.jpg",
            chef = "Mario Batali",
            time = 15,
            rating = 4.3,
            chefImageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        ),
        Recipe(
            id = 8,
            name = "Sushi Platter",
            imageUrl = "https://cdn.pixabay.com/photo/2017/10/15/11/41/sushi-2853382_1280.jpg",
            chef = "Jiro Ono",
            time = 60,
            rating = 4.8,
            chefImageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        ),
        Recipe(
            id = 9,
            name = "French Onion Soup",
            imageUrl = "https://cdn.pixabay.com/photo/2016/03/03/16/19/food-1234483_1280.jpg",
            chef = "Julia Child",
            time = 40,
            rating = 4.6,
            chefImageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        ),
        Recipe(
            id = 10,
            name = "Chocolate Lava Cake",
            imageUrl = "https://cdn.pixabay.com/photo/2016/11/22/18/52/cake-1850011_1280.jpg",
            chef = "Paul Hollywood",
            time = 30,
            rating = 4.9,
            chefImageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        ),
    )

    override suspend fun getSavedRecipes(): AppResult<List<Recipe>, NetworkError> =
        AppResult.Success(mockRecipes)

    override suspend fun getRecipes(searchCondition: RecipeSearchCondition): AppResult<List<Recipe>, NetworkError> =
        AppResult.Success(mockRecipes)

    override suspend fun getRecipe(recipeId: Long): Recipe {
        return mockRecipes.first()
    }
}