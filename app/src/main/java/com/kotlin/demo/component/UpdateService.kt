package com.kotlin.demo.component

import android.app.DownloadManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.IBinder
import android.text.TextUtils
import android.util.Log
import android.webkit.MimeTypeMap
import com.blankj.utilcode.util.ToastUtils
import org.greenrobot.eventbus.EventBus
import java.io.File


/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/3/22
 * Desc : 更新服务
 */
class UpdateService : Service() {

    private var receiver: BroadcastReceiver? = null
    private var url: String? = null
    private var enqueueId: Long = 0
    private var downloadManager: DownloadManager? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            url = intent.getStringExtra("url")
        }

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                unregisterReceiver(receiver)

                val completeDownLoadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                val intentInstall = Intent()
                var uri: Uri? = null

                if (completeDownLoadId == enqueueId) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // 兼容6.0以下
                        uri = downloadManager!!.getUriForDownloadedFile(completeDownLoadId)
                        installPackage(context, intentInstall, uri)
                    } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) { // 兼容6.0-7.0
                        val apkFile = queryDownloadedApk(context, completeDownLoadId)
                        uri = Uri.fromFile(apkFile)
                        installPackage(context, intentInstall, uri)
                    } else {//兼容7.0以上
                        Log.i("aaa", ">7.0")

                        EventBus.getDefault().post(InstallPackage28Event())

                    }
                }
                /*intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/cheshangying.apk")),
                        "application/vnd.android.package-archive");
                startActivity(intent);*/
                stopSelf()
            }
        }
        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        startDownload()
        return Service.START_STICKY
    }

    private fun startDownload() {
        downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(
                Uri.parse(url))//填更新链接
        request.setTitle("cheshangying")
        request.setDescription("新版本下载中")
        //request.setMimeType("application/vnd.android.package-archive");
        // 保存的文件名
        val fileName = "cheshangying.apk"
        // 设置下载路径和文件名
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        //设置文件类型
        val mimeTypeMap = MimeTypeMap.getSingleton()
        val mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url))
        request.setMimeType(mimeString)
        // 设置为可被媒体扫描器找到
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        enqueueId = downloadManager!!.enqueue(request)
        ToastUtils.showShort("后台下载中，请稍候...")
    }

    /**
     * 安装APK
     *
     * @param context
     * @param intentInstall
     * @param uri
     */
    private fun installPackage(context: Context, intentInstall: Intent, uri: Uri?) {
        intentInstall.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intentInstall.action = Intent.ACTION_VIEW
        // 安装应用
        Log.i("aaa", "app下载完成了，开始安装。。。" + uri!!)
        intentInstall.setDataAndType(uri, "application/vnd.android.package-archive")
        context.startActivity(intentInstall)
    }

    inner class InstallPackage28Event

    companion object {

        //通过downLoadId查询下载的apk，解决6.0以后安装的问题
        fun queryDownloadedApk(context: Context, downloadId: Long): File? {
            var targetApkFile: File? = null
            val downloader = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            if (downloadId != -1L) {
                val query = DownloadManager.Query()
                query.setFilterById(downloadId)
                query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL)
                val cur = downloader.query(query)
                if (cur != null) {
                    if (cur.moveToFirst()) {
                        val uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
                        if (!TextUtils.isEmpty(uriString)) {
                            targetApkFile = File(Uri.parse(uriString).path!!)
                        }
                    }
                    cur.close()
                }
            }
            return targetApkFile
        }
    }
}
