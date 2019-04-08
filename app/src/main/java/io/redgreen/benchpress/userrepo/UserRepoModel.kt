package io.redgreen.benchpress.userrepo

import android.os.Parcelable
import io.redgreen.benchpress.architecture.AsyncOp
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRepoModel(
    val userName: UserName,
    val searcFollowersAsyncOp: AsyncOp
) : Parcelable {
    companion object {
        val BLANK = UserRepoModel(
            userName = UserName(""),
            searcFollowersAsyncOp = AsyncOp.IDLE
        )
    }

    val isReadyToSearch: Boolean
        get() = userName.isValid()

    fun userNameChanged(userName: String): UserRepoModel = copy(userName = UserName(userName))

    fun userNameCleared(): UserRepoModel =
        copy(userName = UserName(""))

    fun searchFollowersName(): UserRepoModel =
            copy(searcFollowersAsyncOp = AsyncOp.IN_FLIGHT)
}