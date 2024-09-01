package com.example.contactapp

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        customCoroutine()
    }
}

suspend fun customCoroutine() {

}