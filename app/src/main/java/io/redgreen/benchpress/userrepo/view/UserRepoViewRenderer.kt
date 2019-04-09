package io.redgreen.benchpress.userrepo.view

import io.redgreen.benchpress.userrepo.UserRepoModel

class UserRepoViewRenderer(
    private val view: UserRepoView
) {
    fun render(model: UserRepoModel) {
        if (model == UserRepoModel.BLANK) {
            view.disableSearchButton()
            view.showBlankMessage()
        } else if (model.isReadyToSearch) {
            view.enableSearchButton()
        }
    }
}
