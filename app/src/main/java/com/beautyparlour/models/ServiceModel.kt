package com.beautyparlour.models

class ServiceModel {
    var image : String? = null
    var name : String? = null
    var url : String? = null

    constructor(image: String?, name: String?, url: String?) {
        this.image = image
        this.name = name
        this.url = url
    }

    constructor()


}