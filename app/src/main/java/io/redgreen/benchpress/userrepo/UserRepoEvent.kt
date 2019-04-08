package io.redgreen.benchpress.userrepo

sealed class UserRepoEvent

data class UserNameChangeEvent(val userName: String) : UserRepoEvent()