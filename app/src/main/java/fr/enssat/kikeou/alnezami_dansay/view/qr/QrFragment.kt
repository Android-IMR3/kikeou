package fr.enssat.kikeou.alnezami_dansay.view.qr

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentQrBinding
import kotlinx.coroutines.InternalCoroutinesApi
import net.glxn.qrgen.android.QRCode
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fr.enssat.kikeou.alnezami_dansay.R


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
        myacountViewModel.myAcount.observe(viewLifecycleOwner, Observer{agenda ->

            var json  =myacountViewModel.getJson(agenda)
            val bitmap: Bitmap = QRCode.from(json).bitmap()
            qrCodeView.setImageBitmap(bitmap)

            try {
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

                val btn = binding.navBottom.homeBtn
                btn.setOnClickListener{_ ->
                    findNavController().navigate(R.id.action_listFragment_to_qrFragment)
                }
                val btnShare = binding.shareBtn2
                btnShare.setOnClickListener{_ ->
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "image/png"
                    intent.putExtra(Intent.EXTRA_STREAM, uri)
                    startActivity(Intent.createChooser(intent, "Share Image"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        val btnList = binding.navBottom.btnList
        btnList.setOnClickListener{_ ->
             findNavController().navigate(R.id.action_qrFragment_to_listFragment)
        }
        val btnGenerate = binding.navBottom.btnGenerate
        btnGenerate.setOnClickListener{_ ->
           // findNavController().navigate(R.id.)
        }
        val btnScanner = binding.navBottom.btnScanner
        btnScanner.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_qrFragment_to_scannerQrFragment)
        }
        val btnHome = binding.navBottom.homeBtn
        btnHome.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_qrFragment_to_homeFragment)
        }
        return binding.root

    }



}