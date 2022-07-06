package com.example.ipc

/**
 * description：
 *
 * @author: Lwang
 *
 * @createTime: 2022/7/6 2:37 下午
 */
class Ipc {
  companion object {
    var mClientService: IClientConn? = null
    var mOnReceiveMessageListener: OnReceiveMessageListener? = null

    fun sendMessage(sendMessage:String) {
      println("向客户端发送消息 : $sendMessage")
      mClientService?.receiveFromServer(sendMessage)
    }

    fun onReceiveMessage(receiveMessage:String) {
      println("接收客户端消息 json : $receiveMessage")
      mOnReceiveMessageListener?.run {
        onMessageReceive(receiveMessage)
      }
    }
  }


  public interface OnReceiveMessageListener {
    fun onMessageReceive(message:String);
  }
}