package com.fyp.sctsma.model.agreements

data class AgreementData(
    val content: List<Agreement>?,
    val pageable: Pageable?,
    val totalElements: Int?,
    val totalPages: Int?,
    val last: Boolean?,
    val size: Int?,
    val number: Int?,
    val sort: Sort?,
    val numberOfElements: Int?,
    val first: Boolean?,
    val empty: Boolean?
)
