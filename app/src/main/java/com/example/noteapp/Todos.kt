package com.example.noteapp

object Todos {
    // inheritance and its type ✅
    // constructor ✅
    // polymorphism and types ✅
    // abstraction, how can we do ? how can we achieve 100% abstraction. ✅
    // access modifier ✅
    // Updated data state class with sealed class ✅         -> It makes sure you handle all possible states, so your code is safer and less likely to break.
    // Updated FlowState with SharedSate✅                   -> Ideal for one-time events or actions (like navigation, toasts, or messages), whereas StateFlow is best for holding and observing state.

    //nested class
    //inner class
}

/*
    Nested class is a class declared inside another class without inner keyword. By default, nested
    class does not access the members of outer class.

    Inner class is a class declared inside another(outer) class with inner keyword. Inner class
    access the members (properties or methods) of outer class.
*/

// Outer class
class Car {
    val model: String = "Honda"

    // Nested class
    class Info() {
        val type = "Car"

        fun showInfo() {
            println(type)
        }
    }

    inner class Insurance() {
        val amount: Int = 10000

        fun showInsurance() {
            println("$amount is the insurance amount of $model car") }
    }
}