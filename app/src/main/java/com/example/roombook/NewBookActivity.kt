package com.example.roombook

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels

class NewBookActivity : AppCompatActivity() {
    private lateinit var editTitle: EditText
    private lateinit var edtAuthor: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)
        editTitle = findViewById(R.id.editTitle)
        edtAuthor = findViewById(R.id.editAuthor)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edtAuthor.text) || TextUtils.isEmpty(editTitle.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = editTitle.text.toString()
                val author = edtAuthor.text.toString()
                replyIntent.putExtra("title", title)
                replyIntent.putExtra("author", author)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}