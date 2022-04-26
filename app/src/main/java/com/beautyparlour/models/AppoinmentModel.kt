package com.beautyparlour.models

class AppoinmentModel {
    var username : String? = null
    var userphone : String? = null
    var useraddress : String? = null
    var userdate : String? = null
    var usertime : String? = null




    constructor(
        username: String?,
        userphone: String?,
        useraddress: String?,
        userdate: String?,
        usertime: String?
    ) {
        this.username = username
        this.userphone = userphone
        this.useraddress = useraddress
        this.userdate = userdate
        this.usertime = usertime
    }


}