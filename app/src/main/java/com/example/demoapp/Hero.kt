package com.example.demoapp

/* use for storing data */
class Hero(val id: String?, val name: String, val rating: Int){
    //define empty constructor
    constructor() : this("", "", 0){

    }
}