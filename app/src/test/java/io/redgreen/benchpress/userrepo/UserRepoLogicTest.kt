package io.redgreen.benchpress.userrepo

import com.google.common.truth.Truth.assertThat
import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test


class UserRepoLogicTest {
    private val updateSpec = UpdateSpec<UserRepoModel, UserRepoEvent, UserRepoEffect>(UserRepoLogic::update)
    private val blankModel = UserRepoModel.BLANK
    private val validUserName = "Deepankur-Sadana"

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

    @Test
    fun `when user taps on search then show loader`() {
        val validModel = blankModel
            .userNameChanged(validUserName)

        updateSpec.given(validModel)
            .`when`(SearchFollowersEvent)
            .then(
                assertThatNext(
                    hasModel(validModel.searchFollowers()),
                    hasEffects(SearchFollowersEffect(validUserName) as UserRepoEffect)
                )
            )
    }

    @Test
    fun `when search followers api fails, then show error`() {
        val searchingModel = blankModel.
            userNameChanged(validUserName).
            searchFollowers()

        updateSpec.given(searchingModel)
            .`when`(UnableToFetchFollowersEvent)
            .then(
                assertThatNext(
                    hasModel(searchingModel.unableToFetchFollowers()),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when no followers are fetched, display no followers widget`() {
        val searchingModel = blankModel.userNameChanged(validUserName).searchFollowers()

        updateSpec
            .given(searchingModel)
            .`when`(NoFollowersFoundEvent)
            .then(
                assertThatNext(
                    hasModel(searchingModel.noFollowersFound()),
                    hasNoEffects()
                )
            )
    }
}
