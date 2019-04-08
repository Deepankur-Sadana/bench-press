package io.redgreen.benchpress.userrepo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserName (val name: String) : Parcelable {
    fun isValid(): Boolean {
        return !name.isEmpty()
    }
}