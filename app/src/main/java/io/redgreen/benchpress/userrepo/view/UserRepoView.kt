package io.redgreen.benchpress.userrepo.view

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
}
