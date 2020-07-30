package com.example.rf.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rf.R
import kotlinx.android.synthetic.main.activity_show_website.*

class ShowWebsiteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_website)

        var extra = intent.extras

        if(extra!=null){
            var link = extra.get("link")
            web_view.loadUrl(link.toString())
        }
    }
}
