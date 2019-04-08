package io.redgreen.benchpress.userrepo

import com.google.common.truth.Truth.assertThat
import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test


class UserRepoLogicTest {
    private val updateSpec = UpdateSpec<UserRepoModel, UserRepoEvent, UserRepoEffect>(UserRepoLogic::update)
    private val blankModel = UserRepoModel.BLANK

    @Test
    fun `when username is invalid, then user cannot search`() {
        val invalidUserName = ""
        val invalidModel = blankModel
            .userNameChanged(invalidUserName)

        updateSpec.given(blankModel)
            .`when`(UserNameChangeEvent(invalidUserName))
            .then(
                assertThatNext(
                    hasModel(invalidModel),
                    hasNoEffects()
                )
            )

        assertThat(invalidModel.isReadyToSearch)
            .isFalse()
    }

    @Test
    fun `when username is valid, then user can search`() {
        val validUserName = "Deepankur-Sadana"

        val validModel = blankModel
            .userNameChanged(validUserName)

        updateSpec.given(blankModel)
            .`when`(UserNameChangeEvent(validUserName))
            .then(
                assertThatNext(
                    hasModel(validModel),
                    hasNoEffects()
                )
            )
        assertThat(validModel.isReadyToSearch)
            .isTrue()
    }

    @Test
    fun `when user clears username, then user cannot search`() {
        val hasUsernameState = blankModel
            .userNameChanged("Deepankur-Sadana")
        val noUsernameState = hasUsernameState.userNameCleared()

        updateSpec
            .given(hasUsernameState)
            .`when`(UserNameClearedEvent)
            .then(
                assertThatNext(
                    hasModel(noUsernameState),
                    hasNoEffects()
                )
            )

        assertThat(noUsernameState.isReadyToSearch)
            .isFalse()
    }
}
