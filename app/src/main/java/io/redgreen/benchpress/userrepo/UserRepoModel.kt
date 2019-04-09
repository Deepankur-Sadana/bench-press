package io.redgreen.benchpress.userrepo

import android.os.Parcelable
import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.architecture.AsyncOp.*
import io.redgreen.benchpress.userrepo.UserRepoError.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRepoModel(
    val userName: UserName,
    val searchFollowersAsyncOp: AsyncOp,
    val followers: List<User> = emptyList(),
    val error: UserRepoError = NONE
) : Parcelable {
    companion object {
        val BLANK = UserRepoModel(
            userName = UserName(""),
            searchFollowersAsyncOp = AsyncOp.IDLE
        )
    }

    val isReadyToSearch: Boolean
        get() = userName.isValid()

    fun userNameChanged(userName: String): UserRepoModel = copy(userName = UserName(userName))

    fun userNameCleared(): UserRepoModel =
        copy(userName = UserName(""))

    fun searchFollowers(): UserRepoModel =
        copy(searchFollowersAsyncOp = IN_FLIGHT, error = NONE)

    fun unableToFetchFollowers(): UserRepoModel =
        copy(searchFollowersAsyncOp = FAILED, error = UNKNOWN)

    fun noFollowersFound(): UserRepoModel =
        copy(searchFollowersAsyncOp = SUCCEEDED, followers = emptyList())

    fun userNotFound(): UserRepoModel =
        copy(searchFollowersAsyncOp = SUCCEEDED, error = USER_NOT_FOUND)

    fun followersListFetched(followers: List<User>): UserRepoModel =
        copy(searchFollowersAsyncOp = SUCCEEDED,followers = followers)

}
