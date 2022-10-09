package com.jetapps.search.data

import com.jetapps.search.data.SearchType.BOARD_TYPE
import com.jetapps.search.data.SearchType.CARD_TYPE
import com.jetapps.search.data.SearchType.LABEL_TYPE
import com.jetapps.search.data.SearchType.MEMBER_TYPE
import com.jetapps.search.data.SearchType.TICKET_TYPE

enum class SearchType(val type: String) {
    BOARD_TYPE("board"),
    CARD_TYPE("card"),
    MEMBER_TYPE("member"),
    LABEL_TYPE("label"),
    TICKET_TYPE("ticket")
}

fun getAllSearchType(): List<SearchType> {
    return listOf(
        BOARD_TYPE,
        CARD_TYPE,
        MEMBER_TYPE,
        LABEL_TYPE,
        TICKET_TYPE,
    )
}

fun getSelectedType(type: String): SearchType? {
    val convertToMap = SearchType.values().associateBy(SearchType::type)
    return convertToMap[type]
}