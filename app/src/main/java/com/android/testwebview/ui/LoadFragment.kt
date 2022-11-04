package com.android.testwebview.ui



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.android.testwebview.R
import com.android.testwebview.databinding.FragmentLoadScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "LoadFragment"
@AndroidEntryPoint
class LoadFragment : Fragment() {
private val loadViewModel: LoadViewModel by viewModels()
private lateinit var binding:FragmentLoadScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoadScreenBinding.inflate(inflater, container, false)
        loadViewModel.getLink.observe(viewLifecycleOwner) {
            if (it != "" && !MainActivity.isAgain) {
                toWeb(it)
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    loadViewModel.links.observe(viewLifecycleOwner) { test ->
                        val link = if (MainActivity.isAgain) test.home else test.link
                        loadViewModel.saveLink(link!!)
                        toWeb(link)
                    }
                }
            }
        }
        return binding.root
    }

    private fun toWeb(link:String){
        val bundle = bundleOf("link" to link)
        view?.findNavController()
            ?.navigate(R.id.action_splashScreenFragment_to_webFragment, bundle)
    }

}