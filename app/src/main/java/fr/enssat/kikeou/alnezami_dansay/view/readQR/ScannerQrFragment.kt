package fr.enssat.kikeou.alnezami_dansay.view.readQR

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.fragment.app.Fragment
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import fr.enssat.kikeou.alnezami_dansay.R
import com.google.common.util.concurrent.ListenableFuture
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentScannerQrBinding
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 * Use the [ScannerQrFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScannerQrFragment : Fragment() {
    private val REQUEST_CODE = 123456
    private lateinit var binding: FragmentScannerQrBinding
    private lateinit var objectDetector: ObjectDetector
    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>
    private val args: ScannerQrFragmentArgs by navArgs()
    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        when (requestCode) {
            REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    initCameraAndTF()
                } else {
                    Toast.makeText(context,this.getString(R.string.permissionToGrant), Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScannerQrBinding.inflate(inflater, container, false)

        val btnList = binding.navBottom.btnList
        btnList.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_scannerQrFragment_to_listFragment)
        }
        val btnGenerate = binding.navBottom.btnGenerate
        btnGenerate.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_scannerQrFragment_to_qrFragment)
        }
        val btnScanner = binding.navBottom.btnScanner
        btnScanner.setOnClickListener{_ ->
           // findNavController().navigate(R.id.action_formNewContactFragment_to_scannerQrFragment)
        }
        val btnHome = binding.navBottom.homeBtn
        btnHome.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_scannerQrFragment_to_homeFragment)
        }

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        //Do not forget to declare  <uses-permission android:name="android.permission.CAMERA"/> in your manifest
        if (context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) } == PackageManager.PERMISSION_GRANTED) {
            initCameraAndTF()
        } else {
            val permissions = arrayOf(Manifest.permission.CAMERA)
            activity?.let { ActivityCompat.requestPermissions(it, permissions,REQUEST_CODE) }
        }
    }

    //Only the original thread that created a view can touch its views.
    private fun initCameraAndTF() = binding.previewView.post {

        // tensor flow lite model to detect object in camera preview
        // model retrieve from
        // https://tfhub.dev/google/object_detection/mobile_object_labeler_v1/1
        // store in assets folder of the project
        val TFModel = LocalModel.Builder()
            .setAssetFilePath("lite-model_object_detection_mobile_object_labeler_v1_1.tflite")
            .build()

        // Detect oject according to TF model
        // Stream mode to skip image when camera move too fast
        // Threshold confidence up to 50% to classify object according to Tensor Flow model
        val customObjectDetectorOptions =
            CustomObjectDetectorOptions.Builder(TFModel)
                .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
                .enableClassification()
                .setClassificationConfidenceThreshold(0.5f)
                .setMaxPerObjectLabelCount(3)
                .build()
        objectDetector = ObjectDetection.getClient(customObjectDetectorOptions)

        val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient(
            BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                .build()
        )

        // future do not block
        // get() is used to get the instance of the future when available
        cameraProviderFuture = context?.let { ProcessCameraProvider.getInstance(it) } as ListenableFuture<ProcessCameraProvider>
        cameraProviderFuture.addListener( {

            val cameraProvider = cameraProviderFuture.get()

            // Set up the view finder use case to display camera preview
            val preview = Preview.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .build()

            // Set up the image analysis use case which will process frames in real time
            val imageAnalysis = ImageAnalysis.Builder()
                .setTargetResolution(Size(1080, 2340))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            imageAnalysis.setAnalyzer(
                ContextCompat.getMainExecutor(context!!),
                @ExperimentalGetImage { imageProxy ->
                    val rotationDegrees = imageProxy.imageInfo.rotationDegrees
                    val image = imageProxy.image
                    if(image != null) {
                        val processImage = InputImage.fromMediaImage(image, rotationDegrees)


                        //See https://developers.google.com/ml-kit/vision/barcode-scanning/android
                        barcodeScanner.process(processImage).addOnFailureListener {
                            Log.e("ScannerActivity", "Error: $it.message")
                            imageProxy.close()
                        }.addOnSuccessListener { barcodes ->
                            for (barcode in barcodes) {
                                val rawValue = barcode.rawValue
                                val moshi = Moshi.Builder().build()
                                val adapter: JsonAdapter<Agenda> = moshi.adapter(Agenda::class.java)
                                try{
                                    val contact = adapter.fromJson(rawValue)
                                    Log.e("ScannerActivity", rawValue)
                                    //update agenda
                                    if(args.updateAgenda!= null){
                                        contact?.id= args.updateAgenda!!.id
                                    }else{
                                        contact?.id=0
                                    }
                                    //  Log.e("camera scanner", json.toString())
                                    val action = contact?.let {
                                        ScannerQrFragmentDirections.actionScannerQrFragmentToFormNewContactFragment(
                                            it
                                        )
                                    }
                                    action?.let { findNavController().navigate(it) }
                                }catch( e: IOException){
                                    Toast.makeText(context,"QR code not standard",Toast.LENGTH_SHORT).show()
                                }



                            }


                        }.addOnCompleteListener{

                            image.close()
                            imageProxy.close()

                        }

                    }
                })

            // Create a new camera selector each time, enforcing lens facing
            val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

            // Apply declared configs to CameraX using the same lifecycle owner
            cameraProvider.unbindAll()
            val camera = cameraProvider.bindToLifecycle(
                this as LifecycleOwner, cameraSelector, preview, imageAnalysis)

            // Use the camera object to link our preview use case with the view
            preview.setSurfaceProvider(binding.previewView.surfaceProvider)
        },
            ContextCompat.getMainExecutor(context!!)
        )
    }

}