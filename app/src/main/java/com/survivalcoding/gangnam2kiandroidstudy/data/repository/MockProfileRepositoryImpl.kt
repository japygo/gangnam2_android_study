package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProfileRepository

object MockProfileRepositoryImpl : ProfileRepository {

    val mockProfiles = listOf(
        Profile(
            id = 1L,
            name = "Chef John",
            imageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            recipeCount = 45,
            followerCount = 15200,
            followingCount = 150,
            job = "Head Chef",
            biography = "West African cuisine specialist. Bringing Lagos flavors to the world.",
            address = "Lagos, Nigeria",
        ),
        Profile(
            id = 2L,
            name = "Mark Kelvin",
            imageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            recipeCount = 12,
            followerCount = 850,
            followingCount = 320,
            job = "Home Cook Enthusiast",
            biography = "Exploring comfort food recipes for busy weeknights.",
            address = "Abuja, Nigeria",
        ),
        Profile(
            id = 3L,
            name = "Spicy Nelly",
            imageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            recipeCount = 68,
            followerCount = 45000,
            followingCount = 80,
            job = "Culinary Blogger",
            biography = "If it's not spicy, I don't eat it! Focusing on chili-infused dishes.",
            address = "Ibadan, Nigeria",
        ),
        Profile(
            id = 4L,
            name = "Kim Dahee",
            imageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            recipeCount = 105,
            followerCount = 120000,
            followingCount = 250,
            job = "Korean Food Master",
            biography = "Traditional and modern Korean cuisine. Based in Seoul.",
            address = "Seoul, South Korea",
        ),
        Profile(
            id = 5L,
            name = "Alice Johnson",
            imageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            recipeCount = 22,
            followerCount = 7200,
            followingCount = 400,
            job = "Pastry Chef",
            biography = "Life is what you bake it! Classic American desserts and baking tips.",
            address = "New York, USA",
        ),
        Profile(
            id = 6L,
            name = "Gordon Ramsay",
            imageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            recipeCount = 95,
            followerCount = 5000000,
            followingCount = 0,
            job = "Michelin Star Chef",
            biography = "Where's the lamb sauce?! Professional cooking, no nonsense.",
            address = "London, UK",
        ),
        Profile(
            id = 7L,
            name = "Mario Batali",
            imageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            recipeCount = 30,
            followerCount = 15000,
            followingCount = 10,
            job = "Italian Cuisine Expert",
            biography = "Authentic flavors of Italy, from pasta to gelato.",
            address = "Rome, Italy",
        ),
        Profile(
            id = 8L,
            name = "Jiro Ono",
            imageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            recipeCount = 5,
            followerCount = 250000,
            followingCount = 2,
            job = "Sushi Master",
            biography = "The pursuit of the perfect sushi. Simplicity is the ultimate sophistication.",
            address = "Tokyo, Japan",
        ),
        Profile(
            id = 9L,
            name = "Julia Child",
            imageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            recipeCount = 75,
            followerCount = 90000,
            followingCount = 100,
            job = "French Cooking Legend",
            biography = "Bon Appétit! Mastering the art of French cooking for the home.",
            address = "Paris, France",
        ),
        Profile(
            id = 10L,
            name = "Paul Hollywood",
            imageUrl = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            recipeCount = 40,
            followerCount = 350000,
            followingCount = 50,
            job = "Master Baker",
            biography = "Judging bakes with a keen eye and a silver fox handshake.",
            address = "London, UK",
        ),
    )

    val mockProfile = Profile(
        id = 1,
        name = "Afuwape Abiodun",
        imageUrl = "https://picsum.photos/id/259/200/300",
        recipeCount = 4,
        followerCount = 2_500_000,
        followingCount = 259,
        job = "Chef",
        biography = """
                    Private Chef
                    Passionate about food and life 🥘🍲🍝🍱
                    Passionate about food and life 🥘🍲🍝🍱
                    Passionate about food and life 🥘🍲🍝🍱
                    """.trimIndent(),
    )

    override suspend fun getProfileByRecipeId(recipeId: Long): Profile {
        return mockProfiles.firstOrNull { it.id == recipeId }
            ?: throw IllegalStateException("Profile not found for recipeId: $recipeId")
    }

    override suspend fun getProfile(profileId: Long): Profile {
        return mockProfile
    }
}