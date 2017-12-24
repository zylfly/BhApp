package com.zyl.beheapp.mvp.model.bean


data class GankBean(
        val error: Boolean, //false
        val results: List<Result>
)

data class Result(
        val _id: String, //5a388e4c421aa90fe2f02ced
        val createdAt: String, //2017-12-19T11:58:04.567Z
        val desc: String, //12-19
        val publishedAt: String, //2017-12-19T12:00:28.893Z
        val source: String, //chrome
        val type: String, //福利
        val url: String, //http://7xi8d6.com1.z0.glb.clouddn.com/20171219115747_tH0TN5_Screenshot.jpeg
        val used: Boolean, //true
        val who: String //daimajia
)
