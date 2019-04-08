package io.redgreen.benchpress.userrepo

sealed class UserRepoEffect

data class SearchFollowersEffect(val userName: String):UserRepoEffect()