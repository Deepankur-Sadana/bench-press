package io.redgreen.benchpress.userrepo.view

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.redgreen.benchpress.userrepo.UserRepoModel
import org.junit.Test

class UserRepoViewRendererTest {
    private val view = mock<UserRepoView>()
    private val viewRenderer = UserRepoViewRenderer(view)

    @Test
    fun `it can render blank view state`() {
        // given
        val blankModel = UserRepoModel.BLANK

        // when
        viewRenderer.render(blankModel)

        // then
        verify(view).disableSearchButton()
        verify(view).showBlankMessage()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render ready to search state`() {
        // given
        val readyToSearchModel = UserRepoModel.BLANK
            .userNameChanged("deepankur")

        // when
        viewRenderer.render(readyToSearchModel)

        // then
        verify(view).enableSearchButton()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render loading state`() {
        val loadingModel = UserRepoModel.BLANK
            .userNameChanged("deepankur")
            .searchFollowers()

        //when
        viewRenderer.render(loadingModel)

        //then
        verify(view).showLoading()
        verify(view).disableSearchButton()
        verify(view).disableUserNameField()
        verify(view).hideFollowers()
        verify(view).hideNoFollowersMessage()
        verify(view).hideUserNotFoundMessage()
        verify(view).hideRetryMessage()

        verifyNoMoreInteractions(view)
    }
}
