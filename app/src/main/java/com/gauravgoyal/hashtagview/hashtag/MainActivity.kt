package com.gauravgoyal.hashtagview.hashtag

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text.setHashTagText("Welcome the Legend #Tennis #Roger , What a Player")
        text.setOnTagListener({ Toast.makeText(this@MainActivity, "tag is clicked", Toast.LENGTH_SHORT).show() })
        text.setOnClickListener({ Toast.makeText(this@MainActivity, "text is clicked", Toast.LENGTH_SHORT).show() })
    }
}
