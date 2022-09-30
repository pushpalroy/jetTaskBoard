package com.jetapps.jettaskboard.feature_search.data

enum class SearchType(val type: String) {
    BOARD_TYPE("board"),
    CARD_TYPE("card"),
    MEMBER_TYPE("member"),
    LABEL_TYPE("label"),
    TICKET_TYPE("ticket")
}

fun getAllSearchType(): List<SearchType> {
    return listOf(
        SearchType.BOARD_TYPE,
        SearchType.CARD_TYPE,
        SearchType.MEMBER_TYPE,
        SearchType.LABEL_TYPE,
        SearchType.TICKET_TYPE,
    )
}

fun getSelectedType(type: String): SearchType? {
    val convertToMap = SearchType.values().associateBy(SearchType::type)
    return convertToMap[type]
}