package com.example.lelangapps.util

import android.app.Activity
import android.os.Looper
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.logging.Handler

class UploadRequestBody(
    private val mediaType: String,
    private val inputStream: InputStream,
    private val onUploadProgress : (Int) -> Unit,
) : RequestBody() {

    override fun contentType(): MediaType? = mediaType.toMediaTypeOrNull()

    override fun contentLength(): Long = inputStream.available().toLong()

    override fun writeTo(sink: BufferedSink) {
        val contentLength = inputStream.available().toFloat()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        inputStream.use {
            var uploaded = 0
            var read : Int
            while (inputStream.read(buffer).also { read = it } != -1){
                sink.write(buffer, 0,read)
                uploaded += read
                onUploadProgress((100*uploaded/contentLength).toInt())
            }
        }
    }
//    override fun contentType()= "$contentType/*".toMediaTypeOrNull()
//
//    override fun writeTo(sink: BufferedSink) {
//        val length = file.length()
//        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
//        val fileInputStream = FileInputStream(file)
//        var uploaded = 0L
//        fileInputStream.use { inputStream ->
//            var read : Int
//            val handler = android.os.Handler(Looper.getMainLooper())
//            while (inputStream.read(buffer).also { read = it } != -1){
//                handler.post(ProgressUpdater(uploaded, length))
//                uploaded += read
//                sink.write(buffer,0,read)
//            }
//        }
//    }

//    interface UploadCallback {
//        fun onProgressUpdate(percentage: Int)
//    }
//
//    inner class ProgressUpdater(
//        private val uploaded : Long,
//        private val total : Long
//    ): Runnable {
//        override fun run() {
//            callback.onProgressUpdate((100 * uploaded / total).toInt())
//        }
//    }

}