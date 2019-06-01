package io.redgreen.benchpress.github

import io.redgreen.benchpress.userrepo.UserName


sealed class GithubEffect

data class FindFollowersEffect(val userName: String):GithubEffect()

