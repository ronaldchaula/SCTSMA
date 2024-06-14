package com.fyp.sctsma.model.userData

data class Role(
    val rowId: String,
    val name: String,
    val status: String?,
    val permissions: List<Any>
)