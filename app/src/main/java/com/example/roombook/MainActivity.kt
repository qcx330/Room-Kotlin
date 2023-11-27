package com.example.roombook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Math.floor
import java.lang.Math.random

class MainActivity : AppCompatActivity() {
    private val newBookActivityRequestCode = 1
    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory((application as BooksApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BookListAdapter(object:RvInterface{
            override fun OnClickItem(pos: Int) {
                val selectedBook = bookViewModel.getBookByPosition(pos)
                val intent = Intent(this@MainActivity, BookDetailActivity::class.java)
                intent.putExtra("selectedBook", selectedBook)
                startActivity(intent)
            }

        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        bookViewModel.allBooks.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewBookActivity::class.java)
            startActivityForResult(intent, newBookActivityRequestCode)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newBookActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val title = intentData?.getStringExtra("title")
            val author = intentData?.getStringExtra("author")
//            intentData?.getStringExtra(NewBookActivity.EXTRA_REPLY)?.let { reply ->
//                val word = Book(reply,"")
//                bookViewModel.insert(word)
//            }
            if (author != null && title != null)
            {
                val book = Book(title, author)
                bookViewModel.insert(book)
            }

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}