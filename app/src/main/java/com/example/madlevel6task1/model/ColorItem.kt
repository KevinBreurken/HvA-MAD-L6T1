package com.example.madlevel6task1.model

data class ColorItem(
    val hex: String,
    val name: String
) {
    fun getImageUrl() = "http://singlecolorimage.com/get/$hex/1080x1080"
}