package com.android.testwebview.ui



import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.android.testwebview.databinding.FragmentWebBinding


private const val TAG = "WebFragment"
class WebFragment : Fragment() {

private lateinit var binding:FragmentWebBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebBinding.inflate(inflater,container,false)
        val url = arguments?.getString("link")
        Log.d(TAG, url.toString())
        binding.web.webViewClient = WebViewClient()
        binding.web.settings.javaScriptEnabled = true
        binding.web.settings.domStorageEnabled = true
        binding.web.setOnKeyListener(View.OnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK && binding.web.canGoBack()) {
                binding.web.goBack()
                return@OnKeyListener true
            }
            false
        })
        if (savedInstanceState == null) {
            binding.web.loadUrl(url.toString())
        } else {
          binding.web.restoreState(savedInstanceState)
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.web.saveState(outState)
    }
}
