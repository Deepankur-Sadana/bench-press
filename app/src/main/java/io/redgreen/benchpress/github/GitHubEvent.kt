package io.redgreen.benchpress.github

import io.redgreen.benchpress.github.http.BadRequestError

sealed class GitHubEvent


data class HasFollowersEvent(val followers: List<Follower>) : GitHubEvent()

object HasNoFollowerFoundEvent : GitHubEvent()


data class BadRequestEvent(val badRequestError: BadRequestError):GitHubEvent()


