package io.redgreen.benchpress.userrepo.effecthandlers

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.userrepo.*
import io.redgreen.benchpress.userrepo.http.GitHubApi

object UserRepoEffectHandler {
    fun create(
        gitHubApi: GitHubApi
    ): ObservableTransformer<UserRepoEffect, UserRepoEvent> {
        return RxMobius
            .subtypeEffectHandler<UserRepoEffect, UserRepoEvent>()
            .addTransformer(SearchFollowersEffect::class.java, makeFetchFollowersApiCall(gitHubApi))
            .build()
    }

    private fun makeFetchFollowersApiCall(
        gitHubApi: GitHubApi
    ): ObservableTransformer<SearchFollowersEffect, UserRepoEvent> {
        return object : ObservableTransformer<SearchFollowersEffect, UserRepoEvent> {
            override fun apply(
                searchFollowersEffects: Observable<SearchFollowersEffect>
            ): ObservableSource<UserRepoEvent> {
                return searchFollowersEffects
                    .flatMapSingle { searchFollowersEffect -> gitHubApi.fetchFollowers(searchFollowersEffect.userName) }
                    .map { followers -> if(followers.isEmpty()) NoFollowersFoundEvent else FollowersFetchedEvent(followers) }
            }
        }
    }
}
