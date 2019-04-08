package io.redgreen.benchpress.userrepo


import com.spotify.mobius.Next
import com.spotify.mobius.Update

import com.spotify.mobius.Next.next

object UserRepoLogic : Update<UserRepoModel, UserRepoEvent, UserRepoEffect> {
    override fun update(
        model: UserRepoModel,
        event: UserRepoEvent
    ): Next<UserRepoModel, UserRepoEffect> {

        return when(event){
            is UserNameChangeEvent -> next(model.userNameChanged(event.userName))
//            is EmailChangedEvent -> next(model.emailChanged(event.value))

        }
    }

}