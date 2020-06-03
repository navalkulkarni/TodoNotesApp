package com.naval.todonotesapp.model

class Notes {
    var title: String? = null
    var description: String? = null

    constructor() {}
    constructor(t: String?, desc: String?) {
        title = t
        description = desc
    }

}