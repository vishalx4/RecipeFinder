package com.example.rf.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.rf.R
import com.example.rf.data.LEFT_LINK
import com.example.rf.data.QUERY
import com.example.rf.data.RecipeData
import com.example.rf.model.RecipeAdapter
import kotlinx.android.synthetic.main.activity_recipe.*
import org.json.JSONException
import org.json.JSONObject

class Recipe : AppCompatActivity() {

    var volley_request:RequestQueue? = null
    var Url:String? = null

    var recipe_adapter:RecipeAdapter? = null
    var layout_maneger:RecyclerView.LayoutManager? = null
    var list:ArrayList<RecipeData>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        list = ArrayList()


        var extra = intent.extras
        var ingredients = extra?.get("ingredients")
        var search_recipe = extra?.get("search")

        var makeUrl :String = LEFT_LINK+ingredients+ QUERY+search_recipe


        Log.d("url",makeUrl)

        println(makeUrl)

        Url = makeUrl

        volley_request = Volley.newRequestQueue(this)
        getRecipe(Url!!)

    }

    fun getRecipe(Url:String){

        val json_request = JsonObjectRequest(Request.Method.GET,Url,null,
            Response.Listener {
                    response: JSONObject ->
                try{
                    var json_array = response.getJSONArray("results")

                    for( i in 0..json_array.length()-1){
                        var json_object = json_array.getJSONObject(i)

                        var title:String = json_object.getString("title").trim()
                        var href:String  = json_object.getString("href").trim()
                        var ingredients:String = json_object.getString("ingredients").trim()
                        var thumbnail:String = json_object.getString("thumbnail").trim()

                        var recipe_data = RecipeData()
                        recipe_data.Title = "RECIPE : "+title
                        recipe_data.Ingredients = "INGREDIENTS: "+ingredients
                        recipe_data.Thumbnail = thumbnail
                        recipe_data.Link = href

                        list?.add(recipe_data)

                        recipe_adapter = RecipeAdapter(this,list!!)
                        layout_maneger = LinearLayoutManager(this)

                        // setup RecyclerView
                        recycler_view.adapter = recipe_adapter
                        recycler_view.layoutManager = LinearLayoutManager(this)
                        //Log.d("result","title==>> $title   ingredients==>> $ingredients    thumbnail==>> $thumbnail    href==>> $href")
                    }
                    recipe_adapter!!.notifyDataSetChanged()
                }catch (e:JSONException){e.printStackTrace()}
            },Response.ErrorListener {
                    error: VolleyError? ->
                try {
                    Log.d("error","$error")
                }catch (e:JSONException){e.printStackTrace()}
            })

        volley_request!!.add(json_request)
    }

}
