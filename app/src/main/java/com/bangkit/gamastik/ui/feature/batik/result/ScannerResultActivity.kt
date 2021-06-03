package com.bangkit.gamastik.ui.feature.batik.result

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import com.bangkit.gamastik.databinding.ActivityScannerResultBinding
import com.bangkit.gamastik.ml.BatikModel
import com.bangkit.gamastik.ui.base.BaseActivity
import com.bangkit.gamastik.ui.feature.recomendation.ProductRecomendationActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.model.Model
import org.tensorflow.lite.support.model.Model.Options
import java.io.File


@AndroidEntryPoint
class ScannerResultActivity : BaseActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    lateinit var binding: ActivityScannerResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val file: File? = intent.getSerializableExtra(EXTRA_DATA) as File?
        Glide.with(applicationContext).asBitmap().load(file).into(binding.ivBatikResult)

        val imgBitmap = BitmapFactory.decodeFile(file?.path)
        batikRecognition(imgBitmap)

        binding.btnSeeRecomendation.setOnClickListener {
            val intent = Intent(this, ProductRecomendationActivity::class.java)
            intent.putExtra(
                ProductRecomendationActivity.EXTRA_DATA,
                binding.tvBatikResult.text.toString()
            )
            startActivity(intent)
        }
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun batikRecognition(img: Bitmap) {
        val model: BatikModel by lazy {

            val compatList = CompatibilityList()

            val options = if (compatList.isDelegateSupportedOnThisDevice) {
                Log.d("TAG", "This device is GPU Compatible ")
                Options.Builder().setDevice(Model.Device.GPU).build()
            } else {
                Log.d("TAG", "This device is GPU Incompatible ")
                Options.Builder().setNumThreads(4).build()
            }

            BatikModel.newInstance(applicationContext, options)
        }

        val image = TensorImage.fromBitmap(img)
        val result = model.process(image).probabilityAsCategoryList.toList()
            .sortedByDescending { it.score }[0]

        binding.tvBatikResult.text = result.label.replace("-", " ")
        model.close()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}