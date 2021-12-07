package com.example.apicalls

class SimpleKTThread:Thread() {
    override fun run() {
        println("thread ${Thread.currentThread()} running")
    }
}

class SimpleKTRunnable:Runnable{
    override fun run() {
        println("Runnable running on thread ${Thread.currentThread()}")
    }

}