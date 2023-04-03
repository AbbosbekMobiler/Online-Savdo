package abbosbek.mobiler.onlinesavdo.fragments.loginRegister

import abbosbek.mobiler.onlinesavdo.R
import abbosbek.mobiler.onlinesavdo.activities.ShoppingActivity
import abbosbek.mobiler.onlinesavdo.databinding.FragmentLoginBinding
import abbosbek.mobiler.onlinesavdo.dialog.setupBottomSheetDialog
import abbosbek.mobiler.onlinesavdo.utils.Resource
import abbosbek.mobiler.onlinesavdo.viewModel.LoginViewModel
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            tvDontHaveAccount.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            btnLoginLogin.setOnClickListener {

                val email = etEmailLogin.text.toString().trim()
                val password = etPasswordLogin.text.toString().trim()
                viewModel.login(email, password)
            }

            tvForgotPasswordLogin.setOnClickListener {
                setupBottomSheetDialog {email->
                    viewModel.resetPassword(email)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.resetPassword.collect{
                when(it){
                    is Resource.Loading ->{

                    }
                    is Resource.Success ->{
                        Snackbar.make(requireView(),"Reset Link was sent to your email",Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Error ->{
                        Snackbar.make(requireView(),"Error ${it.message}",Snackbar.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.login.collect{
                when(it){
                    is Resource.Loading ->{
                        binding.btnLoginLogin.startAnimation()
                    }
                    is Resource.Success ->{
                        binding.btnLoginLogin.revertAnimation()
                        Intent(requireActivity(),ShoppingActivity::class.java).also {intent->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    is Resource.Error ->{
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        binding.btnLoginLogin.revertAnimation()
                    }
                    else -> Unit
                }
            }
        }
    }

}