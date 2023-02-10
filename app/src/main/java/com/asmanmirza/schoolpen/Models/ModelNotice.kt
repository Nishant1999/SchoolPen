package com.asmanmirza.schoolpen.Models


data class ModelNotice(
    val data: List<Data>,
    val message: String,
    val stamp: String,
    val status: Int
)

data class Data(
    val id: Int? =null,
    val heading: String? =null,
    val description: String? =null,
    val date: String? =null,
    var file: String? =null,
    var type: String? =null,
    val school: School? =null,
    val classes: Classes? =null
    )


data class Classes(
    val className: String,
    val createDateTime: String,
    val id: Int,
    val schoolId: Int,
    val updateDateTime: String,
    val userId: Int
)

data class School(
    val adderssLine2: Any,
    val addressLine1: String,
    val city: String,
    val contactNumber1: String,
    val contactNumber2: Any,
    val country: Any,
    val id: Int,
    val imgUrl: Any,
    val logo: Any,
    val msg: Any,
    val pin: String,
    val principleName: Any,
    val schoolAboutUs: String,
    val schoolDescription: String,
    val schoolName: String,
    val schoolVision: String,
    val state: String,
    val status: Any,
    val userId: Any
)

/*data class ModelNotice(
    var title:String,
    var date:String,
    var description:String,
    var file:String,
    var type:String)*/






