package com.android.testwebview.ui



import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.android.testwebview.R
import com.android.testwebview.databinding.FragmentLoadScreenBinding
import dagger.hilt.android.AndroidEntryPoint

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
        val isAgain = MainActivity.isAgain
        if(!isAgain){
            loadViewModel.getLinks()
            loadViewModel.links.observe(viewLifecycleOwner){
                toWeb(it.link!!)
            }
        } else {
            loadViewModel.receiveDate()
            loadViewModel.saveLink.observe(viewLifecycleOwner){
                if(it!="none"){
                    toWeb(it)
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