package com.example.jetpackapi.presention.onboarding

import androidx.annotation.DrawableRes
import com.example.jetpackapi.R

data class Page(
    val title:String,
    val description:String,
    @DrawableRes val img:Int

)
val pages = listOf(
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        img = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        img = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        img = R.drawable.onboarding3
    )
)