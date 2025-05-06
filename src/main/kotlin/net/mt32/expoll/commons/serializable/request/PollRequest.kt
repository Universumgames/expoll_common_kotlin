package net.mt32.expoll.commons.serializable.request

import kotlinx.serialization.Serializable
import net.mt32.expoll.commons.serializable.request.search.PollSearchParameters
import net.mt32.expoll.commons.serializable.responses.ComplexOption
import net.mt32.expoll.commons.tPollID
import net.mt32.expoll.commons.tUserID

@Serializable
data class CreatePollRequest(
    val name: String,
    val maxPerUserVoteCount: Int,
    val description: String,
    val type: Int,
    val options: List<ComplexOption>,
    val allowsMaybe: Boolean,
    val allowsEditing: Boolean,
    val privateVoting: Boolean = false,
    val defaultVote: Int?,
)

interface IBasicPollOperation {
    val pollID: tPollID
}

@Serializable
data class BasicPollOperation(override val pollID: tPollID) : IBasicPollOperation

@Serializable
data class EditPollRequest(
    override val pollID: tPollID,
    val delete: Boolean? = null,
    val name: String? = null,
    val description: String? = null,
    val maxPerUserVoteCount: Int? = null,
    val allowsMaybe: Boolean? = null,
    val allowsEditing: Boolean? = null,
    val privateVoting: Boolean? = null,
    val userRemove: List<tUserID> = listOf(),
    val userAdd: List<tUserID> = listOf(),
    val votes: List<VoteChange> = listOf(),
    val options: List<ComplexOption> = listOf(),
    val notes: List<UserNote> = listOf()
) : IBasicPollOperation

@Serializable
data class UserNote(
    val userID: tUserID,
    val note: String?
)

@Serializable
data class PollRequest(
    val pollID: tPollID? = null,
    val searchParameters: PollSearchParameters? = null,
)

@Serializable
data class PollHideRequest(val pollID: tPollID, val hide: Boolean? = true)