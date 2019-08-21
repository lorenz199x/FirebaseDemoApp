package com.example.demoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var ratingBar: RatingBar
    lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        ratingBar = findViewById(R.id.ratingBar)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            saveHero()
        }
    }

    private fun saveHero() {
        val name = editTextName.text.toString().trim()
        if(name.isEmpty()){
            editTextName.error = "Please Enter a Name"
            return
        }
        val ref = FirebaseDatabase.getInstance().getReference("heroes")
        val heroId = ref.push().key
        val hero = Hero(heroId, name, ratingBar.numStars)
        ref.child(heroId!!).setValue(hero).addOnCompleteListener {
            Toast.makeText(applicationContext, "Hero saved success", Toast.LENGTH_LONG).show()
        }

    }
}
