package io.redgreen.benchpress.userrepo.view

import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.userrepo.UserRepoError
import io.redgreen.benchpress.userrepo.UserRepoModel

class UserRepoViewRenderer(
    private val view: UserRepoView
) {
    fun render(model: UserRepoModel) {
        if (model.searchFollowersAsyncOp == AsyncOp.IN_FLIGHT) {
            renderLoading()
        } else if (model.searchFollowersAsyncOp == AsyncOp.SUCCEEDED && model.followers.isNotEmpty()) {
            renderFollowers(model)
        } else if (model.error == UserRepoError.UNKNOWN) {
            renderUnknownError()
        } else if (model.error == UserRepoError.USER_NOT_FOUND) {
            renderUserNotFound()
        } else if (model.searchFollowersAsyncOp == AsyncOp.SUCCEEDED && model.followers.isEmpty()) {
            renderNoFollowersFound()
        } else if (model == UserRepoModel.BLANK) {
            renderBlank()
        } else if (model.isReadyToSearch) {
            view.enableSearchButton()
        }
    }

    private fun renderLoading() {
        view.showLoading()
        view.disableSearchButton()
        view.disableUserNameField()
        view.hideFollowers()
        view.hideNoFollowersMessage()
        view.hideUserNotFoundMessage()
        view.hideRetryMessage()
    }

    private fun renderFollowers(model: UserRepoModel) {
        view.enableUserNameField()
        view.enableSearchButton()
        view.hideLoading()
        view.showFollowers(model.followers)
    }

    private fun renderUnknownError() {
        view.enableUserNameField()
        view.enableSearchButton()
        view.hideLoading()
        view.showRetryMessage()
    }

    private fun renderUserNotFound() {
        view.enableUserNameField()
        view.enableSearchButton()
        view.hideLoading()
        view.showUserNotFoundMessage()
    }

    private fun renderNoFollowersFound() {
        view.enableUserNameField()
        view.enableSearchButton()
        view.hideLoading()
        view.shoNoFollowersFoundMessage()
    }

    private fun renderBlank() {
        view.disableSearchButton()
        view.showBlankMessage()
    }
}
