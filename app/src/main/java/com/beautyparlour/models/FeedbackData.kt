package com.beautyparlour.models

class FeedbackData {


    var username : String? = null

    constructor(username: String?, userfeedback: String?, rating: String?) {
        this.username = username
        this.userfeedback = userfeedback
        this.rating = rating
    }

    var userfeedback : String? = null
    var rating : String? = null

//    constructor(userfeedback: String?, rating: String?) {
//        this.userfeedback = userfeedback
//        this.rating = rating
//    }



}