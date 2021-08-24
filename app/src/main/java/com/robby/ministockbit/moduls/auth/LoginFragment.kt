package com.robby.ministockbit.moduls.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robby.ministockbit.R
import com.robby.ministockbit.databinding.FragmentLoginBinding
import com.robby.ministockbit.utils.*

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        val etTextwatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().isNullOrEmpty()) {
                    buttonHandle()
                }
            }
        }

        with(binding) {
            tvDaftar.setHtml(getString(R.string.label_belum_punya_akun_daftar_sekarang))
            tieUsername.addTextChangedListener(etTextwatcher)
            tiePassword.addTextChangedListener(etTextwatcher)
            btnLogin.setOnClickListener {
                if (isValid(true)) {
                    goToHome(true)
                }
            }
        }
    }

    private fun buttonHandle() {
        with(binding) {
            if(isValid(false)) {
                btnLogin.isEnabled = true
            } else {
                btnLogin.isEnabled = false
            }
        }
    }

    private fun isValid(isFocus: Boolean): Boolean {
        with(binding) {
            val isEmailValid = when {
                    tieUsername.isFieldCondition(getString(R.string.hint_silahkan_isi_email_anda), tieUsername.string().isEmpty(), isFocus) -> false
                    tieUsername.isFieldCondition(getString(R.string.hint_format_email_salah), !tieUsername.string().isEmailValid(), isFocus) -> false
                    else -> true
                }

            val isPasswordValid = when {
                    tiePassword.isFieldCondition(getString(R.string.hint_silahkan_isi_password_anda), tiePassword.string().isEmpty(), isFocus) -> false
                    else -> true
                }

            val isValidSuccess = isEmailValid && isPasswordValid
            return (isValidSuccess)
        }
    }
}