package com.fofito.codeexcerciseapp.presentation.lists

fun Char.isVowel(): Boolean {
    return this.equals('a', true) ||
            this.equals('e', true) ||
            this.equals('i', true) ||
            this.equals('o', true) ||
            this.equals('u', true)
}