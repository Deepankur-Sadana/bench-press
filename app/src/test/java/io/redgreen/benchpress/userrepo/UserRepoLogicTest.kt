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
    fun `disable search button when invalid username is entered`() {
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
    fun `enable search button when valid username is entered`() {
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



}