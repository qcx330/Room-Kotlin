
package com.example.android.roomwordssample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomwordssample.BookListAdapter.BookViewHolder

class BookListAdapter : ListAdapter<Book, BookViewHolder>(WORDS_COMPARATOR) {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title)

        holder.itemView.setOnClickListener{
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            val dialog = DialogFragment()
            dialog.show(fragmentManager, "my_dialog")
        }
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Book) {
            // Bind item data to views in the ViewHolder
        }

        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): BookViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return BookViewHolder(view)
            }
        }

    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}
