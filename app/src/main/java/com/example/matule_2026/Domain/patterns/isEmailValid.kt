package com.example.matule_2026.Domain.patterns

fun String.isEmailValid():Boolean{
    return !this.isEmpty() && Regex("^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$").matches(this)
}