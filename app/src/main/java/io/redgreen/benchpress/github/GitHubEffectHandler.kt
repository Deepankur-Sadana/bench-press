package io.redgreen.benchpress.github

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.architecture.threading.SchedulersProvider
import io.redgreen.benchpress.github.http.BadRequestError
import io.redgreen.benchpress.github.http.FollowersApi
import retrofit2.HttpException
import timber.log.Timber

object GitHubEffectHandler {
    fun create(
        gitHubApi: FollowersApi,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<GithubEffect, GitHubEvent> {
        return RxMobius
            .subtypeEffectHandler<GithubEffect, GitHubEvent>()
            .addTransformer(
                FindFollowersEffect::class.java,
                makeFetchFollowersApiCall(gitHubApi, schedulersProvider)
            )
            .build()
    }

    private fun makeFetchFollowersApiCall(
        gitHubApi: FollowersApi,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<FindFollowersEffect, GitHubEvent> {
        return object : ObservableTransformer<FindFollowersEffect, GitHubEvent> {
            override fun apply(
                searchFollowersEffects: Observable<FindFollowersEffect>
            ): ObservableSource<GitHubEvent> {
                return searchFollowersEffects
                    .flatMapSingle { searchFollowersEffect ->
                        gitHubApi
                            .fetchFollowers(searchFollowersEffect.userName)
                            .map(::mapToFollowersEvent)
                            .doOnError(Timber::e)
                            .onErrorReturn { mapToErrorEvent(it) }
                    }
                    .subscribeOn(schedulersProvider.io)
            }
        }
    }


    private fun mapToFollowersEvent(followers: List<Follower>): GitHubEvent =

        when {
            followers.isEmpty() -> HasNoFollowerFoundEvent
            else -> HasFollowersEvent(followers)
        }


    private fun mapToErrorEvent(throwable: Throwable): GitHubEvent = when {
        throwable is HttpException && throwable.code() == 401 -> BadRequestEvent(BadRequestError.UNAUTHENTICATED)
        throwable is HttpException && throwable.code() == 403 -> BadRequestEvent(BadRequestError.UNAUTHORIZED)
        throwable is HttpException && throwable.code() == 404 -> BadRequestEvent(BadRequestError.NOT_FOUND)
        else -> FindFollowersFailedEvent
    }


}
