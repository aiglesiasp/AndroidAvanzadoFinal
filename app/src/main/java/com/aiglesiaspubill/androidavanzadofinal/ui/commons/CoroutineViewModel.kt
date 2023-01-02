package com.aiglesiaspubill.androidavanzadofinal.ui.commons

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.Runnable

class CoroutineViewModel: ViewModel() {


    //ASYNG
    fun tareaCostosa () {
        //Dormimoa el Thread 1 segundo
        Thread.sleep(1000)
    }

    //THREAD
    fun miThread() {
        val resultado = devuelveParametro()
        miFuncionConCallback {
            Log.d("HOLA", it)
        }
    }

    fun devuelveParametro():String {
        return "Hola"
    }

    //FUNCION CON CALLBACK
    fun miFuncionConCallback(callback: (String) -> (Unit)) {
        val thread = Thread(Runnable {
            Thread.sleep(10000)
            callback("HOLA")
        })
        thread.start()
    }

    //CORRUTINAS
    fun miPrimeraCorrutina() {
        GlobalScope.launch {
            Thread.sleep(10000)
            Log.d("HOLA", "Despues de crrutina")
        }
    }

    fun miCorrutinaEnMain() {
        GlobalScope.launch(Dispatchers.Main) {

        }
    }

    //Para entrada y salida
    fun miCorrutinaEnIo() {
        GlobalScope.launch(Dispatchers.IO) {

        }
    }

    fun devuelveHolaDentrode10Segundos() {
        GlobalScope.launch {
            var hola = withContext(Dispatchers.IO) {
                delay(10000)
                return@withContext "HOLA"
            }
            Log.d("Hola", hola)
        }
    }

    fun devuelveVariosReusltados() {
        GlobalScope.launch {
            val result1 = async(Dispatchers.IO) { funcionXSegundos(2) }
            val result2 = async(Dispatchers.IO) { funcionXSegundos(4) }

            Log.d("HOLA", "FINAL ${result1.await()} ${result2.await()}")
        }

        }

    fun corrutinasCOmplejas() {
        GlobalScope.launch {
            //EJERCICIO Resultado String a los 3 segundos
            //OPCION 1 - ASYNC
            val result1 = async(Dispatchers.IO) {
                funcionXSegundos(3)
            }
            Log.d("HOLA", "Result 1: ${result1.await()}")

            //OPCION 2 - WITHCONTEXT
            val result2 = withContext(Dispatchers.IO) {
                funcionXSegundos(3)
            }
            Log.d("HOLA", "Result 2: ${result2}")
        }
    }

    suspend fun funcionXSegundos(segundos: Int): String {
        Log.d("HOLA", "HOLA $segundos inicio")
        delay(segundos * 1000L)
        Log.d("HOLA", "HOLA $segundos Final")
        return "Hola $segundos"
    }

    suspend fun funcionDevuelveXSegundos(segundos: Int): Int {
        Log.d("HOLA", "HOLA $segundos inicio")
        delay(segundos * 1000L)
        Log.d("HOLA", "HOLA $segundos Final")
        return segundos
    }







}