package com.fyp.sctsma.model.userData

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Role(
    val rowId: String?,
    val name: String?,
    val status: String?,
    val permissions: List<Any>? // Adjust the type based on the actual content of permissions
)