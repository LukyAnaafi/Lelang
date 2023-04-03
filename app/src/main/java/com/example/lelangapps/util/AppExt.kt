package com.example.lelangapps.util

fun String?.defaultError() : String {
    return this ?: Constants.DEFAULT_ERROR
}