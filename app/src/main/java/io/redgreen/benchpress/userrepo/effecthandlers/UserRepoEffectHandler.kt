package io.redgreen.benchpress.userrepo.effecthandlers

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.architecture.threading.SchedulersProvider
import io.redgreen.benchpress.userrepo.*
import io.redgreen.benchpress.userrepo.http.GitHubApi
import retrofit2.HttpException
import timber.log.Timber

object UserRepoEffectHandler {
    fun create(
        gitHubApi: GitHubApi,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<UserRepoEffect, UserRepoEvent> {
        return RxMobius
            .subtypeEffectHandler<UserRepoEffect, UserRepoEvent>()
            .addTransformer(
                SearchFollowersEffect::class.java,
                makeFetchFollowersApiCall(gitHubApi, schedulersProvider)
            )
            .build()
    }

    private fun makeFetchFollowersApiCall(
        gitHubApi: GitHubApi,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<SearchFollowersEffect, UserRepoEvent> {
        return object : ObservableTransformer<SearchFollowersEffect, UserRepoEvent> {
            override fun apply(
                searchFollowersEffects: Observable<SearchFollowersEffect>
            ): ObservableSource<UserRepoEvent> {
                return searchFollowersEffects
                    .flatMapSingle { searchFollowersEffect -> gitHubApi
                        .fetchFollowers(searchFollowersEffect.userName)
                        .map(::mapToFollowersEvent)
                        .doOnError(Timber::e)
                        .onErrorReturn { mapToErrorEvent(it) }
                    }
                    .subscribeOn(schedulersProvider.io)
            }
        }
    }

    private fun mapToFollowersEvent(followers: List<User>): UserRepoEvent =
        if (followers.isEmpty()) {
            NoFollowersFoundEvent
        } else FollowersFetchedEvent(
            followers
        )

    private fun mapToErrorEvent(throwable: Throwable): UserRepoEvent =
        if (throwable is HttpException && throwable.code() == 404) {
            UserNotFoundEvent
        } else {
            UnableToFetchFollowersEvent
        }
}
