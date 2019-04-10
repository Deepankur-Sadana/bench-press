package io.redgreen.benchpress.userrepo.http

import io.reactivex.Single
import io.redgreen.benchpress.userrepo.User

interface GitHubApi {
    fun fetchFollowers(userName: String): Single<List<User>>
}
