package net.mt32.expoll.commons.interfaces

import net.mt32.expoll.commons.PollType
import net.mt32.expoll.commons.helper.UnixTimestamp

interface IPoll {
    val admin: ISimpleUser
    val id: String
    var name: String
    val createdTimestamp: UnixTimestamp
    var updatedTimestamp: UnixTimestamp
    var description: String
    val type: PollType
    var maxPerUserVoteCount: Int
    var allowsMaybe: Boolean
    var allowsEditing: Boolean
    var privateVoting: Boolean
}