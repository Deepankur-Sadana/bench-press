package io.redgreen.benchpress.github.http

import io.reactivex.Single
import io.redgreen.benchpress.github.Follower
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowersApi {
    @GET("users/{user}/followers")
    fun fetchFollowers(
        @Path("user") userName: String
    ): Single<List<Follower>>
}
