package io.redgreen.benchpress.userrepo

import android.os.Parcelable
import io.redgreen.benchpress.architecture.AsyncOp
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRepoModel(
    val userName: UserName,
    val searchFollowersAsyncOp: AsyncOp,
    val hasFollowers: Boolean = false
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
            copy(searchFollowersAsyncOp = AsyncOp.IN_FLIGHT)

    fun unableToFetchFollowers(): UserRepoModel = copy(searchFollowersAsyncOp = AsyncOp.FAILED)

    fun noFollowersFound(): UserRepoModel =
        copy(searchFollowersAsyncOp = AsyncOp.SUCCEEDED, hasFollowers = false)

}