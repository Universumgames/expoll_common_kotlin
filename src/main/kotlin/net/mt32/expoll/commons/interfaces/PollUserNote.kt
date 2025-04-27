package net.mt32.expoll.commons.interfaces

import kotlinx.serialization.Serializable
import net.mt32.expoll.commons.tPollID
import net.mt32.expoll.commons.tUserID

interface IPollUserNote {
    val userID: tUserID
    val pollID: tPollID
    val note: String?
}

@Serializable
data class SerializablePollUserNote (
    override val userID: tUserID,
    override val pollID: tPollID,
    override var note: String
): IPollUserNote