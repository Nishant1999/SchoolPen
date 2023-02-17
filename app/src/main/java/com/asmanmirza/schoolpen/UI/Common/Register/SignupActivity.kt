package com.asmanmirza.schoolpen.UI.Common.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.asmanmirza.schoolpen.databinding.ActivitySignupBinding
import com.asmanmirza.schoolpen.Helpers.ApiClient
import com.asmanmirza.schoolpen.Helpers.ApiInterface
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.UI.Parent.ParentHomeActivity
import com.asmanmirza.schoolpen.UI.Student.StudentHome
import com.asmanmirza.schoolpen.UI.Teacher.TeachersHome
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private var authorityID: String = "";
    lateinit var db: TinyDB;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater);
        setContentView(binding.root)



        updateData()

    }

    private fun updateData() {

        binding.apply {
            db = TinyDB(this@SignupActivity);

            btnBack.setOnClickListener {
                finish()
            }
            btnSignup.setOnClickListener {

                val b = intent.extras!!;
                authorityID = b.getString("id")!!
                if (inFullName.text.toString().isEmpty()) {
                    Toast.makeText(
                        this@SignupActivity,
                        "Please enter your name",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (inUsername.text.toString().isEmpty()) {
                    Toast.makeText(
                        this@SignupActivity,
                        "Please enter a username",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (inEmail.text.toString().isEmpty()) {
                    Toast.makeText(
                        this@SignupActivity,
                        "Please enter your email",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (inPassword.text.toString().isEmpty()) {
                    Toast.makeText(
                        this@SignupActivity,
                        "Please enter a password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (inPassword.text.toString().length < 6) {
                    Toast.makeText(
                        this@SignupActivity,
                        "Password must contain at least 6 characters",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val ids = JsonArray().apply {
                        add(JsonObject().apply {
                            addProperty("id", authorityID.toInt())
                        })
                    }
                    JsonObject().apply {
                        addProperty("userName", inUsername.text.toString().trim())
                        addProperty("password", inPassword.text.toString().trim())
                        addProperty("email", inEmail.text.toString().trim())
                        addProperty("name", inFullName.text.toString().trim())
                        add("roles", ids)
                    }.also {
                        register(it)
                    }
                }
            }
        }
    }

    fun ProgressBar.show() {
        binding.layoutLoading.visibility = View.VISIBLE
        binding.inUsername.isEnabled = false
        binding.inPassword.isEnabled = false
        binding.inFullName.isEnabled = false
        binding.inEmail.isEnabled = false
        binding.btnSignup.isEnabled = false
    }

    fun ProgressBar.dismiss() {
        binding.layoutLoading.visibility = View.GONE
        binding.inUsername.isEnabled = true
        binding.inPassword.isEnabled = true
        binding.btnSignup.isEnabled = true
        binding.inFullName.isEnabled = true
        binding.inEmail.isEnabled = true
    }

    private fun register(obj: JsonObject) {
        binding.layoutLoading.show()
        val apiInterface = ApiClient.getClient()?.create<ApiInterface>(ApiInterface::class.java)!!
        val call = apiInterface.register(obj)
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                val js = Gson().toJson(response.body())
                try {
                    val res = JSONObject(js)
                    if (res.getInt("status") == 201) {
                        val data = res.getJSONObject("data");
                        val db = TinyDB(this@SignupActivity);
                        db.putString("token", data.getString("token"))
                        db.putString("username", binding.inUsername.text.toString())
                        db.putString("userId", res.getString("userId"))

                        Toast.makeText(
                            this@SignupActivity,
                            "Please verify you mobile number.",
                            Toast.LENGTH_SHORT
                        ).show()
                        if(authorityID == "1"){
                            val intent =
                                Intent(this@SignupActivity, TeachersHome::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("userId", res.getInt("userId"))
                            startActivity(intent)
                            finish()
                        }
                        else if(authorityID == "2") {
                            val intent =
                                Intent(this@SignupActivity, StudentHome::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("userId", res.getInt("userId"))
                            startActivity(intent)
                            finish()
                        }
                        else{
                            val intent =
                                Intent(this@SignupActivity, ParentHomeActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("userId", res.getInt("userId"))
                            startActivity(intent)
                            finish()
                        }

                    }else if(res.getInt("status") == 409){
                        Toast.makeText(this@SignupActivity, "Username or user already exists", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this@SignupActivity,
                            "No response from the server",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    binding.layoutLoading.dismiss()
                    Toast.makeText(
                        this@SignupActivity,
                        "Internal server error occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                binding.layoutLoading.dismiss()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("Error//", "$t")
                binding.layoutLoading.dismiss()
                Toast.makeText(
                    this@SignupActivity,
                    "There was an issue while connecting to the server",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


}