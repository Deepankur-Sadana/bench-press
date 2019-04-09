package io.redgreen.benchpress.userrepo


import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object UserRepoLogic : Update<UserRepoModel, UserRepoEvent, UserRepoEffect> {
    override fun update(
        model: UserRepoModel,
        event: UserRepoEvent
    ): Next<UserRepoModel, UserRepoEffect> {

        return when(event){
            is UserNameChangeEvent -> next(model.userNameChanged(event.userName))
            is UserNameClearedEvent -> next(model.userNameCleared())
            is SearchFollowersEvent -> next(
                model.searchFollowers(), setOf(SearchFollowersEffect(model.userName.name))
            )
            is UnableToFetchFollowersEvent -> next(model.unableToFetchFollowers())
            is NoFollowersFoundEvent -> next(model.noFollowersFound())
            else -> TODO()
        }
    }

}