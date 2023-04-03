package abbosbek.mobiler.onlinesavdo.fragments.loginRegister

import abbosbek.mobiler.onlinesavdo.R
import abbosbek.mobiler.onlinesavdo.databinding.FragmentAccountOptionsBinding
import abbosbek.mobiler.onlinesavdo.databinding.FragmentIntroductionBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class AccountOptionsFragment : Fragment(R.layout.fragment_account_options) {

    private lateinit var binding : FragmentAccountOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountOptionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLoginAccountOptions.setOnClickListener {
            findNavController().navigate(R.id.action_accountOptionsFragment_to_loginFragment)

        }
        binding.btnAccountRegisterOptions.setOnClickListener {
            findNavController().navigate(R.id.action_accountOptionsFragment_to_registerFragment)

        }
    }


}