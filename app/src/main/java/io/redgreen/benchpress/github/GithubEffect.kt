package io.redgreen.benchpress.github


sealed class GithubEffect

data class FindFollowersEffect(val userName: String):GithubEffect()


