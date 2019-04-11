package io.redgreen.benchpress.userrepo.http

import io.reactivex.Single
import io.redgreen.benchpress.userrepo.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{user}/followers")
    fun fetchFollowers(
        @Path("user") userName: String
    ): Single<List<User>>
}
