package io.redgreen.benchpress.userrepo.view

import io.redgreen.benchpress.userrepo.UserRepoModel

class UserRepoViewRenderer(
    private val view: UserRepoView
) {
    fun render(model: UserRepoModel) {
        view.disableSearchButton()
        view.showBlankMessage()
    }
}
