package io.redgreen.benchpress.github

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object GitHubLogic : Update<GitHubModel, GitHubEvent, GitHubEffect> {
    override fun update(
        model: GitHubModel,
        event: GitHubEvent
    ): Next<GitHubModel, GitHubEffect> {
        return when (event) {
            is UsernameChangedEvent -> next(model.usernameChanged(event.username))
            is UsernameClearedEvent -> next(GitHubModel.EMPTY)
            is FetchFollowersEvent -> next(model.fetchingFollowers(), setOf(FetchFollowersEffect(event.username)))
            is FollowersFetchedEvent -> next(model.followersFetched(event.followers))
            is NoFollowersEvent -> next(model.noFollowers())
            else -> TODO()
        }
    }
}
