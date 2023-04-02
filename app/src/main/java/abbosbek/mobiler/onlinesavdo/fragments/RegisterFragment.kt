package abbosbek.mobiler.onlinesavdo.fragments

import abbosbek.mobiler.onlinesavdo.R
import abbosbek.mobiler.onlinesavdo.data.User
import abbosbek.mobiler.onlinesavdo.databinding.FragmentRegisterBinding
import abbosbek.mobiler.onlinesavdo.utils.RegisterValidation
import abbosbek.mobiler.onlinesavdo.utils.Resource
import abbosbek.mobiler.onlinesavdo.viewModel.RegisterViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding : FragmentRegisterBinding

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btnLogin.setOnClickListener {
                val user = User(
                    edFirstName.text.toString().trim(),
                    edLastName.text.toString().trim(),
                    edEmail.text.toString().trim()
                )
                val password = edPassword.text.toString()
                viewModel.createAccountWithEmailAndPassword(user,password)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect{
                when(it){
                    is Resource.Loading ->{
                        binding.btnLogin.startAnimation()
                    }
                    is Resource.Success ->{
                        Log.d("test",it.data.toString())
                        binding.btnLogin.revertAnimation()
                    }
                    is Resource.Error ->{
                        Log.e("RegisterFragmentTest",it.message.toString())
                        binding.btnLogin.revertAnimation()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect{validation->
                if (validation.email is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.edEmail.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }

                if (validation.password is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.edPassword.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }
            }

        }

    }


}