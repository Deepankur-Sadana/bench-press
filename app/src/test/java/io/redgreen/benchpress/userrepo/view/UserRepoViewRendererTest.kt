package io.redgreen.benchpress.userrepo.view

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.redgreen.benchpress.userrepo.UserRepoModel
import org.junit.Test

class UserRepoViewRendererTest {
    @Test
    fun `it can render blank view state`() {
        // given
        val view = mock<UserRepoView>()
        val viewRenderer = UserRepoViewRenderer(view)
        val blankModel = UserRepoModel.BLANK

        // when
        viewRenderer.render(blankModel)

        // then
        verify(view).disableSearchButton()
        verify(view).showBlankMessage()

        verifyNoMoreInteractions(view)
    }
}