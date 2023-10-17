package com.example.android.roomwordssample

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.flow.Flow

class DialogFragment: DialogFragment(){

    companion object {
        fun newInstance(selectedItem: Book): DialogFragment {
            val fragment = DialogFragment()
            val args = Bundle()
            args.putParcelable("selectedItem", selectedItem)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_book, null)

        val selectedItem = arguments?.getParcelable<Book>("selectedItem")

        val btnDel = dialogView.findViewById<Button>(R.id.btnDelete)
        val btnEdit = dialogView.findViewById<Button>(R.id.btnEdit)
        val edtText = dialogView.findViewById<EditText>(R.id.edtEdit)

        btnDel.setOnClickListener {

        }
        btnEdit.setOnClickListener(){

        }

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(dialogView)

        return builder.create()
    }
}