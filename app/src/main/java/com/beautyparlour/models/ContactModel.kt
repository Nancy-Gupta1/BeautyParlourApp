package com.beautyparlour.models

class ContactModel {
    var email : String? = null
    var mobile : String? = null
    var time : String? = null


    constructor()


    constructor(email: String?, mobile: String?, time: String?) {
        this.email = email
        this.mobile = mobile
        this.time = time
    }


}