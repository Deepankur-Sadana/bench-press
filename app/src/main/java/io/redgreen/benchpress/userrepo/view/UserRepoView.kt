package io.redgreen.benchpress.userrepo.view

import io.redgreen.benchpress.userrepo.User

interface UserRepoView {
    fun disableSearchButton()
    fun showBlankMessage()
    fun enableSearchButton()
    fun showLoading()
    fun disableUserNameField()
    fun hideFollowers()
    fun hideNoFollowersMessage()
    fun hideUserNotFoundMessage()
    fun hideRetryMessage()
    fun enableUserNameField()
    fun hideLoading()
    fun showFollowers(followers: List<User>)
    fun shoNoFollowersFoundMessage()
}
