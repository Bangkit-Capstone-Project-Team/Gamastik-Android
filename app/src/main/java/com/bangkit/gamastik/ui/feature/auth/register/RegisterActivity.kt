package com.bangkit.gamastik.ui.feature.auth.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bangkit.gamastik.data.model.auth.register.RegisterRequest
import com.bangkit.gamastik.databinding.ActivityRegisterBinding
import com.bangkit.gamastik.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import gov.ulama.uttp.utils.Resource

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {
    lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmailReg.text.toString()
            val name = binding.edtNameReg.text.toString()
            val password = binding.edtPasswordReg.text.toString()
            val repassword = binding.edtRePasswordReg.text.toString()
            register(RegisterRequest(name, email, password, repassword))
        }

    }

    fun register(request: RegisterRequest) {
        viewModel.register(request)?.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    val data = it.data
                    if (data != null) {
                        setUserName(data.user?.name)
                        finish()
                        Toast.makeText(this, "Register berhasil, silakan login", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "Register Gagal", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
            }
        })
    }
}