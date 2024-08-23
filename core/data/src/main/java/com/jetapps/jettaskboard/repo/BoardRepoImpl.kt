package com.jetapps.jettaskboard.repo

import android.util.Log
import com.jetapps.jettaskboard.local.datastore.PreferenceDataStoreSource
import com.jetapps.jettaskboard.local.source.DatabaseSource
import com.jetapps.jettaskboard.mapper.BoardMapper
import com.jetapps.jettaskboard.mapper.CardMapper
import com.jetapps.jettaskboard.mapper.ListMapper
import com.jetapps.jettaskboard.model.BoardWithListAndCard
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.ListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BoardRepoImpl @Inject constructor(
    private val preferenceDataStoreSource: PreferenceDataStoreSource,
    private val databaseSource: DatabaseSource,
    private val boardMapper: BoardMapper,
    private val listMapper: ListMapper,
    private val cardMapper: CardMapper,
) : BoardRepo {

    override suspend fun getLatestBackgroundImgUri(): Flow<String?> {
        return preferenceDataStoreSource.getLatestBackgroundImgUri()
    }

    override suspend fun updateBackgroundImgUri(string: String) {
        preferenceDataStoreSource.updateBackgroundImgUri(string)
    }

    /**
     * TODO
     * This task can be expensive as it loads lots of data once and Map each property
     * Need to explore more and check for better solution.
     */
    override suspend fun getBoardDetails(boardId: Int): Flow<BoardWithListAndCard> {
        return databaseSource.getBoard(boardId).map { boardWithLists ->
            Log.d("DAO_RELATION", "getBoardDetails: $boardWithLists")
            BoardWithListAndCard(
                boardId = boardWithLists.boardEntity.boardId,
                boardTitle = boardWithLists.boardEntity.title,
                isFav = boardWithLists.boardEntity.isFav == 1,
                listModel = boardWithLists.boardList.map { listWithCards ->
                    ListModel(
                        listId = listWithCards.columnList.listId,
                        title = listWithCards.columnList.title,
                        cards = listWithCards.cardList.map { cardEntity ->
                            cardMapper.mapToDomain(cardEntity)
                        }.toMutableList(),
                        boardId = listWithCards.columnList.boardId
                    )
                }
            )
        }
    }

    override suspend fun createNewList(listModel: ListModel) {
        databaseSource.createNewList(listMapper.mapToDomain(listModel))
    }

    override suspend fun deleteList(listModel: ListModel) {
        databaseSource.deleteList(listMapper.mapToDomain(listModel))
    }

    override suspend fun updateCard(cardModel: CardModel) {
        databaseSource.updateCard(cardMapper.mapToData(cardModel))
    }

    override suspend fun deleteCard(cardModel: CardModel) {
        databaseSource.deleteCard(cardMapper.mapToData(cardModel))
    }

    override suspend fun createCard(cardModel: CardModel) {
        databaseSource.createCard(cardMapper.mapToData(cardModel))
    }
}