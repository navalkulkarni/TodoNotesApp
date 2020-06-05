package com.naval.todonotesapp.view

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.DATA
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.naval.todonotesapp.R
import com.naval.todonotesapp.utils.AppConstant

class AddNotesActivity : AppCompatActivity() {

    lateinit var editTextAddNotesTitle:EditText
    lateinit var editTextAddNotesDescription:EditText
    lateinit var buttonSubmitAddNotes:Button
    lateinit var imageViewAddNotes:ImageView
    val REQUEST_CODE_GALLERY = 2
    val REQUEST_CODE_CAMERA = 1
    var picturePath =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        bindViews()
        setupClickListeners()
    }

    private fun bindViews() {
        editTextAddNotesTitle = findViewById(R.id.editTextAddNotesTitle)
        editTextAddNotesDescription = findViewById(R.id.editTextAddNotesDescription)
        buttonSubmitAddNotes = findViewById(R.id.buttonSubmitAddNotes)
        imageViewAddNotes = findViewById(R.id.imageViewAddNotes)
    }

    private fun setupClickListeners() {

        imageViewAddNotes.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                setupDialog()
            }
        })
    }

    private fun setupDialog() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_selector,null)
        var textViewDialogCamera:TextView = view.findViewById(R.id.textViewDialogCamera)
        var textViewDialogGallery:TextView = view.findViewById(R.id.textViewDialogGallery)

        var dialog = AlertDialog.Builder(this)
                .setView(view).setCancelable(true).create()

        textViewDialogCamera.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }

        })

        textViewDialogGallery.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,REQUEST_CODE_GALLERY)
        })

        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            Activity.RESULT_OK ->{
                when(resultCode){
                    REQUEST_CODE_GALLERY ->{
                        var selectedImage = data?.data
                        val filePath = arrayOf(MediaStore.Images.Media.DATA)
                        val contentResolver = contentResolver.query(selectedImage!!,filePath,null,null,null)
                        contentResolver!!.moveToFirst()
                        val columnIndex = contentResolver.getColumnIndex(filePath[0])
                        picturePath = contentResolver.getString(columnIndex)
                        contentResolver.close()
                        Glide.with(this).load(picturePath).into(imageViewAddNotes)
                    }
                    REQUEST_CODE_CAMERA ->{

                    }
                }
            }
        }
    }
}