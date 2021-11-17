package fr.enssat.kikeou.alnezami_dansay.viewmodel

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log

import androidx.lifecycle.ViewModel
import net.glxn.qrgen.android.QRCode
import java.lang.Exception

class QRViewModel(private val contentResolver: ContentResolver) : ViewModel()  {
    var json = " { id: string\n" +
            "  name: string\n" +
            " photo: Url?\n" +
            " contact: [ \n" +
            "     { key:  \"email\" , value : string },\n" +
            "     { key: \"tel\", value: string },\n" +
            "     { key: \"face de  book\", value: string},\n" +
            "  ]\n" +
            " week : 43" +
            "  loc: [ \n" +
            "     { day: 1, value: \"teletravail\" },\n" +
            "     { day : 3, value: \"Off\" },\n" +
            "     { day : 5: value: \"WF 036\" } \n" +
            "  ]\n" +
            "}"
    init {
        Log.i("QRViewModel", "QRViewModel created!")

    }
    var uri: Uri? = null
    private val QR_CODE_FILE_NAME = "myqrcode"
    private val NO_COMPRESSION = 100
    val bitmap: Bitmap = QRCode.from(this.json).bitmap()
    fun saveImage(){
        try {
            //in your android manifest
            //<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
            //to access media storage for android <10

            val values = ContentValues()
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, QR_CODE_FILE_NAME)
            values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            values.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_DOCUMENTS.toString() + "/Kikeou/"
            )

            uri = contentResolver.insert(MediaStore.Files.getContentUri("external"), values)
            val out = uri?.let {
                contentResolver.openOutputStream(it)
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, NO_COMPRESSION, out)
            out?.flush()
            out?.close()
            //share.visibility = View.VISIBLE

            //to get png in Android Studio : View -> Tool Windows -> Device File Explorer
            //in storage/self/primary/Documents/Kikeou
            //also in sdcard/Documents/Kikeou
            //synchronize Documents folder if needed
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}