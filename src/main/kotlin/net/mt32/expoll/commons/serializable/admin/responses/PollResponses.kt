package net.mt32.expoll.commons.serializable.admin.responses

import kotlinx.serialization.Serializable
import net.mt32.expoll.commons.serializable.responses.PollSummary

@Serializable
data class AdminPollResponse(
    val polls: List<PollSummary>,
    val totalCount: Int
)