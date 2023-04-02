package abbosbek.mobiler.onlinesavdo.fragments

import abbosbek.mobiler.onlinesavdo.R
import abbosbek.mobiler.onlinesavdo.databinding.FragmentIntroductionBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class IntroductionFragment : Fragment(R.layout.fragment_introduction) {

    private lateinit var binding : FragmentIntroductionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroductionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.accountOptionsFragment)
        }

    }

}