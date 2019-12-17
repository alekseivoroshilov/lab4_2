package com.example.lab4_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import name.ank.lab4.BibDatabase
import name.ank.lab4.Keys

class MainActivity : AppCompatActivity() {

    private lateinit var bibAdepter: Adapter


    private val bibData = mutableListOf<Object>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val articles = resources.openRawResource(R.raw.articles)

        val database = BibDatabase(articles.reader())
        //val size = database.entrie
        val size = database.cfg.maxValid;
        var i = 0
        while (i < size) {
            try {
                val entry = database.getEntry(i)

                bibData.add(
                    Object(
                        entry.getField(Keys.AUTHOR) ?: "Unknown", entry.getField(Keys.TITLE)
                            ?: "Unknown",
                        entry.getField(Keys.JOURNAL) ?: "Unknown", entry.getField(Keys.YEAR)
                            ?: "Unknown",
                        entry.getField(Keys.VOLUME) ?: "Unknown"
                    )
                )
                i++
            } catch (e: IndexOutOfBoundsException) {
                break
            }
        }

        val recyclerLayoutManager = LinearLayoutManager(this)
        bibAdepter = Adapter(bibData)
        val dividerItemDecoration = DividerItemDecoration(
                my_recycler_view.context,
                recyclerLayoutManager.orientation
        )
        my_recycler_view.apply {
            adapter = bibAdepter
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            addItemDecoration(dividerItemDecoration)
        }
    }
}