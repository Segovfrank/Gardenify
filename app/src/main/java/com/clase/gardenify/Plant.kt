package com.clase.gardenify

import java.io.Serializable

class Plant(): Serializable{

    var slug: String = ""
    var link: String? = null
    var scientific_name: String = ""
    var id: Int = 0
    var complete_data: Boolean = false
    var common_name: String = ""
    var imageUrl: String? = null

    constructor(slug: String, link: String?, scientific_name: String, id: Int, complete_data: Boolean, common_name: String, imageUrl: String?) : this() {
        this.slug = slug
        this.link = link
        this.scientific_name = scientific_name
        this.id = id
        this.complete_data = complete_data
        this.common_name = common_name
        this.imageUrl = imageUrl
    }


}