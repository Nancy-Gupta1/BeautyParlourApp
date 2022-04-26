package com.beautyparlour.models

class TipsModel {

    var name : String? = null
    var title : String? = null
    var url : String? = null

    constructor()

    constructor(name: String?, title: String?, url: String?) {
        this.name = name
        this.title = title
        this.url = url
    }


}