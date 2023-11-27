package com.example.roombook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels

class BookDetailActivity : AppCompatActivity() {
    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory((application as BooksApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val selectedBook = intent.getSerializableExtra("selectedBook") as Book
        val edtTitle = findViewById<EditText>(R.id.editTitle)
        val edtAuthor = findViewById<EditText>(R.id.editAuthor)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        edtTitle.text = selectedBook.title.toEditable()
        edtAuthor.text = selectedBook.author.toEditable()
        btnDelete.setOnClickListener(){
            val book = Book(edtTitle.text.toString(), edtAuthor.text.toString())
            bookViewModel.delete(book)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("delete", book)
            startActivity(intent)
        }

        btnUpdate.setOnClickListener(){
            val book = Book(edtTitle.text.toString(), edtAuthor.text.toString())
            bookViewModel.update(book)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("delete", book)
            startActivity(intent)
        }
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

}