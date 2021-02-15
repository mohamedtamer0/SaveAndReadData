package com.example.saveandreaddata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileReader
import java.io.FileWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun btnSaveClick(view:View) {
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

    fun btnReadClick(view: View) {
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


}