package com.example.demoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var ratingBar: RatingBar
    lateinit var buttonSave: Button
    lateinit var listView: ListView

    lateinit var ref: DatabaseReference
    lateinit var heroList: MutableList<Hero>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heroList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("heroes")

        editTextName = findViewById(R.id.editTextName)
        ratingBar = findViewById(R.id.ratingBar)
        buttonSave = findViewById(R.id.buttonSave)
        listView = findViewById(R.id.listView)

        buttonSave.setOnClickListener {
            saveHero()
        }

            //valueEventListener is interface
        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                // read all heroes list
                if(p0!!.exists()){
                    // to prevent fetching data again
                    heroList.clear()
                    for(h in p0.children){
                        val hero = h.getValue(Hero::class.java)
                        heroList.add(hero!!)
                    }

                    //display list view
                    // get heroes xml and display list
                    val adapter = HeroAdapter(applicationContext, R.layout.heroes, heroList)
                    listView.adapter = adapter
                }
            }
        })
    }

    private fun saveHero() {
        val name = editTextName.text.toString().trim()
        if(name.isEmpty()){
            editTextName.error = "Please Enter a Name"
            return
        }

        val heroId = ref.push().key
        val hero = Hero(heroId, name, ratingBar.numStars)
        ref.child(heroId!!).setValue(hero).addOnCompleteListener {
            Toast.makeText(applicationContext, "Hero saved success", Toast.LENGTH_LONG).show()
        }

    }
}
