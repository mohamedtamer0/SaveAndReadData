package com.example.saveandreaddata

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileReader
import java.io.FileWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var WRITE_EXTERNAL_STORAGE = 255
    var READ_EXTERNAL_STORAGE = 256

    fun saveFile(){
        try {
            var fr = FileWriter(Environment.getExternalStorageDirectory().path + "/my_file.txt")
            fr.write(txt.text.toString())
            fr.close()
            Toast.makeText(this@MainActivity,"Saved!",Toast.LENGTH_SHORT).show()
            txt.setText("")
        }catch (ex:Exception) {
            Toast.makeText(this@MainActivity,ex.message,Toast.LENGTH_SHORT).show()
        }
    }

    fun readFile(){
        try {
            var fr = FileReader(Environment.getExternalStorageDirectory().path + "/my_file.txt")
            var x:Int = 0
            var str:String = ""
            while (true) {
                x = fr.read()
                str += x.toChar()
                if (x == -1) break
            }
            fr.close()
            txt.setText(str)
        }catch (ex:Exception) {
            Toast.makeText(this@MainActivity,ex.message,Toast.LENGTH_SHORT).show()
        }
    }


    fun btnSaveClick(view:View) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),WRITE_EXTERNAL_STORAGE)
        } else {
            saveFile()
        }
    }

    fun btnReadClick(view: View) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),READ_EXTERNAL_STORAGE)

        } else {
            readFile()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            WRITE_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    saveFile()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }


            READ_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                                grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    readFile()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }


}