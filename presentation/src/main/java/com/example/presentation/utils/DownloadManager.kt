package com.example.presentation.utils

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


object DownloadManager {

    inline fun Fragment.requestStoragePermissionLauncher(crossinline isUserGranted: (Boolean) -> Unit) {
         if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(requireContext() ,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                isUserGranted(true)
            } else {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                false
            }
        } else {
             isUserGranted(true)
         }
    }

    inline fun startDownload(context: Context, url: String, fileName: String , finishedCallBack: (Boolean) -> Unit) {
        Toast.makeText(context, "Downloading..." , Toast.LENGTH_SHORT)
            .show()
        val request = DownloadManager.Request(Uri.parse(url))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setTitle(fileName) // Title of the Download Notification
            .setDescription("Downloading") // Description of the Download Notification

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadID = downloadManager.enqueue(request) // enqueue puts the download request in the queue.

        var finishDownload = false
        while (!finishDownload) {
            val cursor: Cursor =
                downloadManager.query(DownloadManager.Query().setFilterById(downloadID))
            if (cursor.moveToFirst()) {
                when (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                    DownloadManager.STATUS_FAILED -> {
                        finishDownload = true
                        finishedCallBack(true)

                    }
                    DownloadManager.STATUS_PAUSED -> {

                    }
                    DownloadManager.STATUS_PENDING -> {

                    }
                    DownloadManager.STATUS_RUNNING -> {

                    }
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        finishedCallBack(true)
                        finishDownload = true
                        Toast.makeText(context, "downloaded successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}