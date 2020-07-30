package com.example.rf.model

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rf.Activity.Recipe
import com.example.rf.Activity.ShowWebsiteActivity
import com.example.rf.R
import com.example.rf.data.RecipeData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*

class RecipeAdapter(private val context: Context,private val list:ArrayList<RecipeData>)

    :RecyclerView.Adapter<RecipeAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecipeAdapter.ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

       fun bindView(recipe:RecipeData) {
           itemView.recipe_title.text = recipe.Title.toString()
           itemView.recipe_ingredients.text = recipe.Ingredients.toString()

           if(!TextUtils.isEmpty(recipe.Thumbnail)){

               Log.d("path",recipe.Thumbnail)

               Picasso.get().load(recipe.Thumbnail)
                   .placeholder(android.R.drawable.ic_menu_report_image)
                   .error(android.R.drawable.ic_menu_report_image)
                   .into(itemView.thumbnail)
           }
           else{
               itemView.thumbnail.setImageResource(R.mipmap.ic_launcher_round)
           }

           itemView.link_btn.setOnClickListener {
               var intent = Intent(context,ShowWebsiteActivity::class.java)
               intent.putExtra("link",recipe.Link)
               context.startActivity(intent)
           }

       }


    }


}
