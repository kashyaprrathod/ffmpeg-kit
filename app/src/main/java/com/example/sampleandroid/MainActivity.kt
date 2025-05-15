package com.example.sampleandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.FFmpegSession
import com.arthenica.ffmpegkit.FFmpegSessionCompleteCallback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val org = "storage/emulated/0/Download/abc.mp4"
        val opt = "storage/emulated/0/Download/resources/${System.currentTimeMillis()}.mp4"

//        val arg = arrayOf("-i", "", "storage/emulated/0/Download/mno.mp4")

        val arg = arrayOf(
            "-i",
            org,
            "-c:a",
            "aac",
            "-c:v",
            "libx264",
            "-vf",
            "scale='if(gt(iw,ih),if(gt(iw,1280),1280,iw),-2):if(gt(iw,ih),-2,if(gt(ih,720),720,ih))',setpts=PTS-STARTPTS",
            "-crf",
            "33",
            "-preset",
            "ultrafast",
            opt
        )

        FFmpegKit.executeWithArgumentsAsync(
            arg
        ) { session -> Log.e("TAG", "apply: ${session?.logs}") }
    }
}