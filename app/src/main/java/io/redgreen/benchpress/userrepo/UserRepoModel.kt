package io.redgreen.benchpress.userrepo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRepoModel(
    val userName: UserName
) : Parcelable {
    companion object {
        val BLANK = UserRepoModel(
            userName = UserName("")
        )
    }

    val isReadyToSearch: Boolean
        get() = userName.isValid()

    fun userNameChanged(userName: String): UserRepoModel = copy(userName = UserName(userName))

    fun userNameCleared(): UserRepoModel  =
        copy(userName = UserName(""))
}