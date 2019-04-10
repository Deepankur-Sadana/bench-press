package io.redgreen.benchpress.userrepo.effecthandlers

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.redgreen.benchpress.test.EffectHandlerTestCase
import io.redgreen.benchpress.userrepo.FollowersFetchedEvent
import io.redgreen.benchpress.userrepo.NoFollowersFoundEvent
import io.redgreen.benchpress.userrepo.SearchFollowersEffect
import io.redgreen.benchpress.userrepo.User
import io.redgreen.benchpress.userrepo.http.GitHubApi
import org.junit.Test

class UserRepoEffectHandlerTest {
    private val gitHubApi = mock<GitHubApi>()
    private val testCase = EffectHandlerTestCase(UserRepoEffectHandler.create(gitHubApi))

    @Test
    fun `it can dispatch followers fetched event`() {
        // given
        val userName = "deepankur"
        val followers = listOf(User("ragunath", "some-avatar-url"))

        whenever(gitHubApi.fetchFollowers(userName))
            .thenReturn(Single.just(followers))


        // when
        testCase.dispatchEffect(SearchFollowersEffect(userName))

        // then
        testCase.assertOutgoingEvents(FollowersFetchedEvent(followers))
    }

    @Test
    fun `it can dispatch no followers found event`() {
        //given
        val userName = "alien"

        whenever(gitHubApi.fetchFollowers(userName))
            .thenReturn(Single.just(emptyList()))

        //when
        testCase.dispatchEffect(SearchFollowersEffect(userName))

        //then
        testCase.assertOutgoingEvents(NoFollowersFoundEvent)
    }
}
