package net.mt32.expoll.commons.interfaces

import net.mt32.expoll.commons.VoteValue
import net.mt32.expoll.commons.tOptionID
import net.mt32.expoll.commons.tPollID
import net.mt32.expoll.commons.tUserID

interface IVote {
    val id: Int
    val userID: tUserID
    val pollID: tPollID
    val optionID: tOptionID
    var votedFor: VoteValue
}