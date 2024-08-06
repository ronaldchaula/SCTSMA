package com.fyp.sctsma.model.agreements

data class Pageable(  val pageNumber: Int?,
                      val pageSize: Int?,
                      val sort: Sort?,
                      val offset: Int?,
                      val paged: Boolean?,
                      val unpaged: Boolean?)
