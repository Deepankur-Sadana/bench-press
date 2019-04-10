package io.redgreen.benchpress.userrepo

sealed class UserRepoEvent

data class UserNameChangeEvent(val userName: String) : UserRepoEvent()

object UserNameClearedEvent : UserRepoEvent()

object SearchFollowersEvent : UserRepoEvent()

data class FollowersFetchedEvent(val followers: List<User>) : UserRepoEvent()

object NoFollowersFoundEvent : UserRepoEvent()

object UserNotFoundEvent : UserRepoEvent()

object UnableToFetchFollowersEvent : UserRepoEvent()

object RetryFetchFollowersEvent : UserRepoEvent()
