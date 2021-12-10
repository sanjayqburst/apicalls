package com.example.apicalls

import android.accounts.NetworkErrorException
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicalls.adapters.RecyclerAdapter
import com.example.apicalls.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.recyclerContainer.adapter
        layoutManager=LinearLayoutManager(this)
        mainBinding.recyclerContainer.layoutManager=layoutManager

        if (networkStatus(this)){
            getMyData()
        }else{
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,NoNetworkFragment()).commit()
        }
        mainBinding.apply {
            swipeRefresh.setOnRefreshListener {
                var fragmentTransaction=supportFragmentManager.beginTransaction()
                if (networkStatus(applicationContext)){
                    Log.d("Msg","True")
                    getMyData()
                    fragmentContainer.visibility= View.INVISIBLE
                    fragmentTransaction.remove(NoNetworkFragment()).commit()
                    swipeRefresh.isRefreshing=false
                }else{
                    Log.d("Msg","False")
                    fragmentContainer.visibility=View.VISIBLE
                    fragmentTransaction.add(R.id.fragmentContainer,NoNetworkFragment()).commit()
                    swipeRefresh.isRefreshing=false
                }
            }
        }

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/").build().create(ApiInterface::class.java)
        val retrofitData=retrofitBuilder.getData()
        retrofitData.enqueue(object :Callback<ProfileData?>{
            override fun onResponse(call: Call<ProfileData?>, response: Response<ProfileData?>) {
                val responseBody=response.body()
                Log.d("Msg","$responseBody")
                recyclerAdapter= RecyclerAdapter(baseContext,responseBody)
                mainBinding.recyclerContainer.adapter=recyclerAdapter
            }

            override fun onFailure(call: Call<ProfileData?>, t: Throwable) {
                Log.d("Msg","Failed")
            }

        })
    }
private fun networkStatus(context: Context): Boolean {
    val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork:NetworkInfo?=connectivityManager.activeNetworkInfo
    return activeNetwork?.isConnected?:false

}


}