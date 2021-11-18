package fr.enssat.kikeou.alnezami_dansay.view.qr

import android.annotation.SuppressLint
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentQrBinding
import kotlinx.coroutines.InternalCoroutinesApi
import net.glxn.qrgen.android.QRCode
import android.content.ContentResolver
import androidx.lifecycle.ViewModelProvider
import fr.enssat.kikeou.alnezami_dansay.database.entity.MyAccount
import fr.enssat.kikeou.alnezami_dansay.view.list.ListPersonViewModel


class QrFragment : Fragment() {
    val NO_COMPRESSION = 100
    val QR_CODE_FILE_NAME = "myqrcode"
    private lateinit var binding: FragmentQrBinding
    @InternalCoroutinesApi
    private lateinit var myacountViewModel: QrViewModel

    @SuppressLint("WrongThread")
    @InternalCoroutinesApi
    override fun  onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQrBinding.inflate(inflater, container, false)

        myacountViewModel = ViewModelProvider(this).get(QrViewModel::class.java)
        var qrCodeView =binding.qrCodeView
        var uri : Uri? = null
        val bitmap: Bitmap = QRCode.from(myacountViewModel.test).bitmap()
        qrCodeView.setImageBitmap(bitmap)
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
            val resolver = activity!!.contentResolver
            uri = resolver.insert(MediaStore.Files.getContentUri("external"), values)
            val out = uri?.let {
                resolver.openOutputStream(it)
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, NO_COMPRESSION, out)
            out?.flush()
            out?.close()


            //to get png in Android Studio : View -> Tool Windows -> Device File Explorer
            //in storage/self/primary/Documents/Kikeou
            //also in sdcard/Documents/Kikeou
            //synchronize Documents folder if needed
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root

    }



}