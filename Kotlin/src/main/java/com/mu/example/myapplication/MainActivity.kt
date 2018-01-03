package com.mu.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("Heloo")
        var a=  sum(3,4)
        println(a)
    }
    fun sum(a: Int, b: Int): Int {
        return a + b
    }
}
