package com.survivalcoding.gangnam2kiandroidstudy.legacy.p02state

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.gangnam2kiandroidstudy.databinding.ActivityStateLegacyBinding

class StateLegacyActivity : AppCompatActivity() {
    var count = 0

    val binding by lazy {
        ActivityStateLegacyBinding.inflate(layoutInflater)
    }

    val viewModel by viewModels<StateLegacyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 복원 찬스
        if (savedInstanceState != null) {
//            count = savedInstanceState.getInt("count")
//            binding.plusTextView.text = count.toString()

            binding.plusTextView.text = viewModel.count.toString()
        }

        binding.plusButton.setOnClickListener {
//            count++
//            binding.plusTextView.text = count.toString()

            viewModel.increment()
            binding.plusTextView.text = viewModel.count.toString()
        }
    }

    // 화면 돌 때만 호출
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("count", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // 복원 찬스
    }
}