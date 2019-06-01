package io.redgreen.benchpress.github

sealed class GitHubEvent


data class HasFollowersEvent(val followers: List<Follower>) : GitHubEvent()