package io.redgreen.benchpress.userrepo.view

import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.userrepo.UserRepoError
import io.redgreen.benchpress.userrepo.UserRepoModel

class UserRepoViewRenderer(
    private val view: UserRepoView
) {
    fun render(model: UserRepoModel) {
        if (model.searchFollowersAsyncOp == AsyncOp.IN_FLIGHT) {
            view.showLoading()
            view.disableSearchButton()
            view.disableUserNameField()
            view.hideFollowers()
            view.hideNoFollowersMessage()
            view.hideUserNotFoundMessage()
            view.hideRetryMessage()
        } else if (model.searchFollowersAsyncOp == AsyncOp.SUCCEEDED && model.followers.isNotEmpty()) {
            view.enableUserNameField()
            view.enableSearchButton()
            view.hideLoading()
            view.showFollowers(model.followers)
        } else if (model.error == UserRepoError.UNKNOWN) {
            view.enableUserNameField()
            view.enableSearchButton()
            view.hideLoading()
            view.showRetryMessage()
        } else if (model.error == UserRepoError.USER_NOT_FOUND) {
            view.enableUserNameField()
            view.enableSearchButton()
            view.hideLoading()
            view.showUserNotFoundMessage()
        } else if (model.searchFollowersAsyncOp == AsyncOp.SUCCEEDED && model.followers.isEmpty()) {
            view.enableUserNameField()
            view.enableSearchButton()
            view.hideLoading()
            view.shoNoFollowersFoundMessage()
        } else if (model == UserRepoModel.BLANK) {
            view.disableSearchButton()
            view.showBlankMessage()
        } else if (model.isReadyToSearch) {
            view.enableSearchButton()
        }
    }
}
