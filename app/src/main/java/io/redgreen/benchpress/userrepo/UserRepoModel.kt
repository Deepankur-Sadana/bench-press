package io.redgreen.benchpress.userrepo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRepoModel(
    val userName: UserName
) : Parcelable {
    val isReadyToSearch: Boolean
        get() = userName.isValid()

    fun userNameChanged(userName: String): UserRepoModel = copy(userName = UserName(userName))

    companion object {
        val BLANK = UserRepoModel(
            userName = UserName("")
        )
    }
}