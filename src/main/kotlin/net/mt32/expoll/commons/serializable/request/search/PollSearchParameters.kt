package net.mt32.expoll.commons.serializable.request.search

import kotlinx.serialization.Serializable
import net.mt32.expoll.commons.serializable.request.SortingOrder
import kotlin.reflect.full.memberProperties

@Serializable
data class PollSearchParameters(
    var sortingOrder: SortingOrder = SortingOrder.DESCENDING,
    var sortingStrategy: SortingStrategy = SortingStrategy.UPDATED,
    var specialFilter: SpecialFilter = SpecialFilter.ALL,
    var searchQuery: Query = Query()
) {
    enum class SortingStrategy {
        UPDATED, CREATED, NAME, USER_COUNT
    }

    enum class SpecialFilter {
        ALL, JOINED, NOT_JOINED
    }

    @Serializable
    data class Query(
        val adminID: String? = null,
        val description: String? = null,
        val name: String? = null,
        val memberID: String? = null,
        val any: String? = null
    )

    @Serializable
    data class Descriptor(
        val sortingOrder: List<SortingOrder> = SortingOrder.entries,
        val sortingStrategy: List<SortingStrategy> = SortingStrategy.entries,
        val specialFilter: List<SpecialFilter> = SpecialFilter.entries,
        val searchQuery: List<String> = Query::class.memberProperties.map { it.name }
    )
}