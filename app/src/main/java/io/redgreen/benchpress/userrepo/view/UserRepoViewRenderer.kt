package io.redgreen.benchpress.userrepo.view

import io.redgreen.benchpress.architecture.AsyncOp
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
        } else if (model == UserRepoModel.BLANK) {
            view.disableSearchButton()
            view.showBlankMessage()
        } else if (model.isReadyToSearch) {
            view.enableSearchButton()
        }
    }
}
