package io.redgreen.benchpress.github

import io.redgreen.benchpress.github.http.FailureType

sealed class GitHubEvent

data class HasFollowersEvent(val followers: List<Follower>) : GitHubEvent()

object NoFollowersFoundEvent : GitHubEvent()

data class FindFollowersFailedEvent(val failureType: FailureType) : GitHubEvent()

object FindFollowersUnknownErrorEvent : GitHubEvent()
