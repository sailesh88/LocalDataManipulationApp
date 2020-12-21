package com.test.localdataapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.localdataapp.models.UserData
import com.test.localdataapp.models.UserDataSource
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var userListAdapter: UserListAdapter

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addUserData()


        val sw1 = findViewById<Switch>(R.id.toggle_switch)
        sw1?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addUserRemoteData()
            } else {
                addUserData()
            }

        }

    }

    private fun addUserRemoteData() {

        val usersList = ApiServices.apiRequests.getUsers()

        usersList.enqueue(object: retrofit2.Callback<List<UserData>>{
            override fun onResponse(
                call: Call<List<UserData>>,
                response: Response<List<UserData>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()!!
                    val users = ArrayList<UserData>()
                    users.addAll(data)
                    initRecyclerView()
                    userListAdapter.swapData(users)
                }else
                    addUserData()
            }

            override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                addUserData()
            }
        })
    }

    private fun addUserData() {
        val users =  UserDataSource.createDataSet()
        userListAdapter.swapData(users)
    }

    private fun initRecyclerView() {
        val rv: RecyclerView = findViewById(R.id.rv_user_list)
        rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            userListAdapter = UserListAdapter()
            rv.adapter = userListAdapter
        }
    }
}