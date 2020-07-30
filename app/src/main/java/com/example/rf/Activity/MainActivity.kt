package com.example.rf.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.rf.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            var Ingredients:String = ingredients.text.toString()
            var SearchItem:String = search_item.text.toString()

            if(!TextUtils.isEmpty(Ingredients) &&
                    !TextUtils.isEmpty(SearchItem)){

                var i = Intent(this,Recipe::class.java)
                i.putExtra("ingredients",Ingredients)
                i.putExtra("search",SearchItem)
                startActivity(i)
            }
            else{
                Toast.makeText(this,"Fill all the Fields",Toast.LENGTH_SHORT).show()
            }



        }

    }
}
