package io.redgreen.benchpress.github

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.redgreen.benchpress.github.http.FollowersApi
import io.redgreen.benchpress.test.EffectHandlerTestCase
import io.redgreen.benchpress.test.ImmediateSchedulersProvider
import org.junit.Test

class GitHubEffectHandlerTest {

    private val followersApi = mock<FollowersApi>()
    private val testCase = EffectHandlerTestCase(GitHubEffectHandler.create(followersApi, ImmediateSchedulersProvider()))


    @Test
    fun `it can dispatch list of followers`() {
        val userName = "nitesh-munda"

        val followers = listOf(Follower("ragunath", "some-avatar-url"))


        whenever(followersApi.fetchFollowers(userName))
            .thenReturn(Single.just(followers))


        // when
        testCase.dispatchEffect(FindFollowersEffect(userName))

        // then
        testCase.assertOutgoingEvents(HasFollowersEvent(followers))

    }
}