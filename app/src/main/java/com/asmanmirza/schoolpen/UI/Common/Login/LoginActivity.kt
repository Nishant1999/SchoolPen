package com.asmanmirza.schoolpen.UI.Common.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asmanmirza.schoolpen.Helpers.ApiClient
import com.asmanmirza.schoolpen.Helpers.ApiInterface
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.UI.Parent.ParentHomeActivity
import com.asmanmirza.schoolpen.UI.Student.Home.Models.DataUserId
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelClassUserId
import com.asmanmirza.schoolpen.UI.Student.StudentHome
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import com.asmanmirza.schoolpen.UI.Teacher.TeachersHome
import com.asmanmirza.schoolpen.databinding.ActivityLoginBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var apiInterface: ApiInterface;





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData() {

        binding.apply {

            apiInterface = ApiClient.getClient()?.create(ApiInterface::class.java)!!
            btnBack.setOnClickListener { finish() }

            btnBack.setOnClickListener {
                finish()
            }

            btnLogin.setOnClickListener {
                if (inUsername.text?.isNotEmpty() == true && inPassword.text?.isNotEmpty() == true)
                    JsonObject().apply {
                        addProperty("userName", inUsername.text.toString())
                        addProperty("password", inPassword.text.toString())
                    }.also {
                        //layoutLoading.root.visibility = View.VISIBLE
                        binding.layoutLoading.visibility = View.VISIBLE
                        binding.inUsername.isEnabled = false
                        binding.inPassword.isEnabled = false
                        binding.btnLogin.isEnabled = false
                        login(it)

                    }
                else
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter both userId and Password",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }

    }





    fun ProgressBar.show() {
        binding.layoutLoading.visibility = View.VISIBLE
        binding.inUsername.isEnabled = false
        binding.inPassword.isEnabled = false
        binding.btnLogin.isEnabled = false
    }

    fun ProgressBar.dismiss() {
        binding.layoutLoading.visibility = View.GONE
        binding.inUsername.isEnabled = true
        binding.inPassword.isEnabled = true
        binding.btnLogin.isEnabled = true
    }

    fun login(obj: JsonObject) {
        val call = apiInterface.login(obj)
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                val js = Gson().toJson(response.body())
                try {
                    val res = JSONObject(response.body().toString())
                    if (res.getInt("status") == 201) {
                        val bundle = Bundle()
                        val db = TinyDB(this@LoginActivity);
                        db.putString("token", res.getString("token"))
                        db.putString("username", binding.inUsername.text.toString())
                        db.putString("userId", res.getString("userid"))
                        bundle.putString("user", binding.inUsername.text.toString())
                        binding.layoutLoading.dismiss()
                        Toast.makeText(
                            this@LoginActivity,
                            "Successfully logged in!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val authority =
                            res.getJSONArray("roles").getJSONObject(0).getString("authority")
                        if (authority == "1") {
                            db.putString("authority", "1")
                            val intent = Intent(this@LoginActivity, TeachersHome::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else if (authority == "2") {
                            db.putString("authority", "2")
                            val intent = Intent(this@LoginActivity, StudentHome::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else if(authority == "4") {
                            db.putString("authority", "4")
                            val intent = Intent(this@LoginActivity, ParentHomeActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }

                        finish()
                    } else if (res.getInt("status") == 206) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Please verify mobile number to continue.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (res.getInt("status") == 400) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Invalid username or password.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    binding.layoutLoading.dismiss()
                    Toast.makeText(
                        this@LoginActivity,
                        "Internal server error occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("Error//", "$t")
                binding.layoutLoading.dismiss()
                Toast.makeText(
                    this@LoginActivity,
                    "There was an issue while connecting to the server",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }




}