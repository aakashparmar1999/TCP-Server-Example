package com.example.tcpserverexample

import android.util.Log
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException

class TcpClientHandler(
    private val dataInputStream: DataInputStream,
    private val dataOutputStream: DataOutputStream
) : Thread() {
    override fun run() {
        while (true) {
            try {
                if (dataInputStream.available() > 0) {
                    Log.i(TAG, "Received:" + dataInputStream.readUTF())
                    dataOutputStream.writeUTF("hello client")
                    sleep(2000L)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                try {
                    dataInputStream.close()
                    dataOutputStream.close()
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
    companion object{
        private val TAG = TcpClientHandler::class.java.simpleName
    }
}