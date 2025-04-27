package net.mt32.expoll.commons.serializable.responses

import kotlinx.serialization.Serializable

@Serializable
data class MailRegexRule(
    val id: String,
    val regex: String,
    val blacklist: Boolean
)

@Serializable
data class MailRegexRules(
    val regex: List<MailRegexRule>
)
