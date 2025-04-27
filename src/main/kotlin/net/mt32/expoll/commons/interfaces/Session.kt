package net.mt32.expoll.commons.interfaces

import net.mt32.expoll.commons.helper.UnixTimestamp
import net.mt32.expoll.commons.serializable.request.Platform
import net.mt32.expoll.commons.tUserID

interface ISession {
    val userID: tUserID
    val nonce: Long
    val userAgent: String
    var clientVersion: String?
    var platform: Platform
    val createdTimestamp: UnixTimestamp
    val expirationTimestamp: UnixTimestamp
    var lastUsedTimestamp: UnixTimestamp
}