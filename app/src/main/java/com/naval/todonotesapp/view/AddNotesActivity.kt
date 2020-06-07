package com.naval.todonotesapp.view

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.naval.todonotesapp.BuildConfig
import com.naval.todonotesapp.R
import com.naval.todonotesapp.utils.AppConstant.DESCRIPTION
import com.naval.todonotesapp.utils.AppConstant.IMAGEPATH
import com.naval.todonotesapp.utils.AppConstant.TAG
import com.naval.todonotesapp.utils.AppConstant.TITLE
import java.io.File
import java.security.Permission
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddNotesActivity : AppCompatActivity() {

    lateinit var editTextAddNotesTitle:EditText
    lateinit var editTextAddNotesDescription:EditText
    lateinit var buttonSubmitAddNotes:Button
    lateinit var imageViewAddNotes:ImageView
    val REQUEST_CODE_GALLERY = 2
    val REQUEST_CODE_CAMERA = 1
    val MY_PERMISSION_CODE = 124
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
                if(checkAndRequestPermission())
                    setupDialog()
            }
        })

        buttonSubmitAddNotes.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.putExtra(TITLE,editTextAddNotesTitle.text.toString())
            intent.putExtra(DESCRIPTION,editTextAddNotesDescription.text.toString())
            intent.putExtra(IMAGEPATH,picturePath)
            setResult(Activity.RESULT_OK,intent)
            finish()
        })
    }

    private fun checkAndRequestPermission(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        var externalStorage = ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
        val listPermissionsNeeded =ArrayList<String>()
        if(cameraPermission != PackageManager.PERMISSION_GRANTED)
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA)

        if(externalStorage != PackageManager.PERMISSION_GRANTED)
            listPermissionsNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if(!listPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(this,listPermissionsNeeded.toTypedArray<String>(),MY_PERMISSION_CODE)
            return false
        }

        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            MY_PERMISSION_CODE ->{
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    setupDialog()
            }
        }
    }

    private fun setupDialog() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_selector,null)
        var textViewDialogCamera:TextView = view.findViewById(R.id.textViewDialogCamera)
        var textViewDialogGallery:TextView = view.findViewById(R.id.textViewDialogGallery)

        var dialog = AlertDialog.Builder(this)
                .setView(view).setCancelable(true).create()

        textViewDialogCamera.setOnClickListener(View.OnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            var photoFile : File? = null
            photoFile = createImage()
            if(photoFile!=null)
            {
                val photoURI = FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID+".provider",photoFile)
                picturePath = photoFile.absolutePath
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
                startActivityForResult(takePictureIntent,REQUEST_CODE_CAMERA)
                dialog.hide()
            }
        })

        textViewDialogGallery.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,REQUEST_CODE_GALLERY)
            dialog.hide()
        })

        dialog.show()
    }

    private fun createImage(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val fileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName,".jpg",storageDir)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
                when(requestCode){
                    REQUEST_CODE_GALLERY ->{
                        var selectedImage = data?.data
                        val filePath = arrayOf(MediaStore.Images.Media.DATA)
                        val contentResolver = contentResolver.
                        query(selectedImage!!,filePath,null,null,null)
                        contentResolver!!.moveToFirst()
                        val columnIndex = contentResolver.getColumnIndex(filePath[0])
                        picturePath = contentResolver.getString(columnIndex)
                        //Log.d("MyNotesLog",picturePath)
                        contentResolver.close()
                        Glide.with(this).load(picturePath).into(imageViewAddNotes)
                    }
                    REQUEST_CODE_CAMERA ->{
                        Glide.with(this).load(picturePath).into(imageViewAddNotes)
                        //Log.d("MyNotesLog",picturePath)
                    }
                }
        }

    }
}