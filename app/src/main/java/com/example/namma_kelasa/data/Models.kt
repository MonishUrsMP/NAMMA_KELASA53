package com.example.namma_kelasa.data

data class Worker(
    val id: String,
    val name: String,
    val skill: String,
    val location: String,
    val rating: Double,
    val reviewCount: Int,
    val dailyRate: String,
    val available: Boolean = true,
    val aboutMe: String = "I have 8 years of experience in house painting. I provide quality work with focus on detail and customer satisfaction.",
    val experience: String = "8 Years"
)

data class JobEnquiry(
    val id: String,
    val title: String,
    val location: String,
    val time: String,
    val status: String,
    val isHired: Boolean = false
)
