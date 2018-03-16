package com.mu.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("Heloo")
        /**
         * Basic Syntax
         */
        var a = sum(3, 4)
        println(a)
    }

    /**
     * Basic Syntax
     */
    fun sum(a: Int, b: Int): Int {
        return a + b
    }
}
