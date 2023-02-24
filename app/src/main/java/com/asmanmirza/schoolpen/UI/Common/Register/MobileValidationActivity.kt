package com.asmanmirza.schoolpen.UI.Common.Register

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.asmanmirza.schoolpen.databinding.ActivityMobileValidationBinding
import com.asmanmirza.schoolpen.Helpers.ApiClient
import com.asmanmirza.schoolpen.Helpers.ApiInterface
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.UI.Student.StudentHome
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MobileValidationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMobileValidationBinding
    private var sessionID = "";
    var previousMobileNumber = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMobileValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {

            btnBack.setOnClickListener { finish() }

            inPhone.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                @SuppressLint("SetTextI18n")
                override fun afterTextChanged(s: Editable?) {
                    if(inPhone.text.toString().length == 10){
                        btnVerify.text = "Send otp"
                        btnVerify.visibility = View.VISIBLE
                    }
                }
            })

            btnVerify.setOnClickListener {
                if(sessionID.isEmpty()){
                    if(inPhone.text.toString().length != 10){
                        Toast.makeText(this@MobileValidationActivity, "Please enter 10 digit mobile number", Toast.LENGTH_SHORT).show()
                    }else{
                        sendOTP(inPhone.text.toString())
                    }
                }else{
                    if(inOTP.text.toString().length != 4){
                        Toast.makeText(this@MobileValidationActivity, "Please enter a valid otp", Toast.LENGTH_SHORT).show()
                    }else{
                        val userID = intent.extras!!.getInt("userId")
                        val otp = inOTP.text.toString()
                        JsonObject().apply {
                            addProperty("id", userID)
                            addProperty("sessionId", sessionID)
                            addProperty("otp", otp)
                        }.also {
                            verifyOTP(it)
                        }
                    }
                }
            }

            textResend.setOnClickListener {
                sendOTP(inPhone.text.toString())
                Toast.makeText(this@MobileValidationActivity, "Resending otp...", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun sendOTP(obj: JsonObject) {
        val db = TinyDB(this@MobileValidationActivity);
        binding.layoutLoading.show()
        val apiInterface = ApiClient.getClient()?.create<ApiInterface>(ApiInterface::class.java)!!
        val call = apiInterface.sendOTP(obj, "Bearer ${db.getString("token")}")
        call.enqueue(object : Callback<Any> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                val js = Gson().toJson(response.body())
                try {
                    val res = JSONObject(js)

                    if(res.has("data")){
                        val result = res.getJSONObject("data").getString("result").toString();
                        if(JSONObject(result).getString("Status").lowercase() ==  "success") {
                            sessionID = JSONObject(result).getString("Details")
                            binding.inOTP.visibility = View.VISIBLE;
                            binding.btnVerify.text = "Verify and continue"
                            binding.textResend.visibility = View.VISIBLE
                            Toast.makeText(
                                this@MobileValidationActivity,
                                "OTP has been sent successfully.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            Toast.makeText(this@MobileValidationActivity, "Invalid response from the server.", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this@MobileValidationActivity, "Invalid response from the server.", Toast.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    binding.layoutLoading.dismiss()
                    Toast.makeText(
                        this@MobileValidationActivity,
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
                    this@MobileValidationActivity,
                    "There was an issue while connecting to the server",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun verifyOTP(obj: JsonObject) {
        val db = TinyDB(this@MobileValidationActivity);
        binding.layoutLoading.show()
        val apiInterface = ApiClient.getClient()?.create<ApiInterface>(ApiInterface::class.java)!!
        val call = apiInterface.verifyOTP(obj, "Bearer ${db.getString("token")}")
        call.enqueue(object : Callback<Any> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                val js = Gson().toJson(response.body())
                try {
                    val res = JSONObject(js)

                    if(res.getBoolean("authenticated")){
                        Toast.makeText(this@MobileValidationActivity, "Registered successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MobileValidationActivity, StudentHome::class.java))
                    }else{
                        Toast.makeText(this@MobileValidationActivity, "Invalid OTP", Toast.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    binding.layoutLoading.dismiss()
                    Toast.makeText(
                        this@MobileValidationActivity,
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
                    this@MobileValidationActivity,
                    "There was an issue while connecting to the server",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun ProgressBar.show() {
        binding.layoutLoading.visibility = View.VISIBLE
        binding.inPhone.isEnabled = false
        binding.inOTP.isEnabled = false
        binding.btnVerify.isEnabled = false
        binding.btnBack.isEnabled = false
        binding.btnGoToSignUp.isEnabled = false
    }

    fun ProgressBar.dismiss() {
        binding.layoutLoading.visibility = View.GONE
        binding.inPhone.isEnabled = true
        binding.inOTP.isEnabled = true
        binding.btnVerify.isEnabled = true
        binding.btnBack.isEnabled = true
        binding.btnGoToSignUp.isEnabled = true
    }

    fun sendOTP(number:String){
        val userID = intent.extras!!.getInt("userId")
        binding.inPhone.clearFocus()
        JsonObject().apply {
            addProperty("id", userID)
            addProperty("mobileNumber", number.toLong())
        }.also {
            sendOTP(it)
        }
    }

}