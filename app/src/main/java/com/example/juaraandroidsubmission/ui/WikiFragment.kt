package com.example.juaraandroidsubmission.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.juaraandroidsubmission.R
import com.example.juaraandroidsubmission.databinding.FragmentWikiBinding
import com.example.juaraandroidsubmission.viewmodel.PahlawanViewModel
import com.example.juaraandroidsubmission.viewmodel.ViewModelFactory

class WikiFragment : Fragment() {

    private var _binding: FragmentWikiBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PahlawanViewModel by activityViewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWikiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = binding.webview
        true.also { webView.settings.javaScriptEnabled = it }

        webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                view?.loadUrl("javascript:alert('Selamat membaca')")
            }
        }
        webView.webChromeClient = object : WebChromeClient(){
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                result?.confirm()
                return true
            }
        }

        viewModel.pahlawan.observe(viewLifecycleOwner){

            webView.loadUrl(it.urlWiki.toString())
        }
    }
}