package com.example.ipc

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * description：
 *
 * @author: Lwang
 *
 * @createTime: 2022/7/6 10:01 上午
 */
class CommunicationService : Service() {


  private val mCommunication = object : ICommunicationManager.Stub() {

    override fun receive(json: String) {
      Ipc.onReceiveMessage(json)
      Ipc.sendMessage("server send  $json success")
    }

    override fun registClientConn(clientConn: IClientConn?) {
      println("registerClientConn clientConn : $clientConn")
      clientConn?.run {
        Ipc.mClientService = this
        Ipc.sendMessage("registClientConn success")
      }
    }

    override fun unRegist(clientConn: IClientConn?) {
      Ipc.mClientService = null
    }

  }

  override fun onBind(intent: Intent?): IBinder {
    println("服务端onBind")
    return mCommunication
  }

  override fun onCreate() {
    super.onCreate()
    println("服务端oncreate")
  }
}