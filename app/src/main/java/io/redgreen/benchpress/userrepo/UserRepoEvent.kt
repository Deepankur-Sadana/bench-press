package io.redgreen.benchpress.userrepo

sealed class UserRepoEvent

data class UserNameChangeEvent(val userName: String) : UserRepoEvent()
object UserNameClearedEvent : UserRepoEvent()
object SearchFollowersEvent : UserRepoEvent()
object UnableToFetchFollowersEvent : UserRepoEvent()
object NoFollowersFoundEvent : UserRepoEvent()
object UserNotFoundEvent : UserRepoEvent()