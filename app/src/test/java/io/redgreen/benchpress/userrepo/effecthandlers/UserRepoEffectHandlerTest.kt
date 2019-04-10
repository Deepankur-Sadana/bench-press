package io.redgreen.benchpress.userrepo.effecthandlers

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.redgreen.benchpress.test.EffectHandlerTestCase
import io.redgreen.benchpress.userrepo.*
import io.redgreen.benchpress.userrepo.http.GitHubApi
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

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
    
    @Test
    fun `it can dispatch unable to fetch followers event`() {
        //given
        val userName = "Deepankur-Sadana"

        whenever(gitHubApi.fetchFollowers(userName))
            .thenReturn(Single.error(Exception("Act fibrenet on vacation")))

        //when
        testCase.dispatchEffect(SearchFollowersEffect(userName))

        //then
        testCase.assertOutgoingEvents(UnableToFetchFollowersEvent)
    }

    @Test
    fun `it can dispatch user not found event`() {
        // given
        val userName = "nonexistent-user"
        val userNotFoundJson = """
            {
                "message": "Not Found",
                "documentation_url": "https://developer.github.com/v3/repos/#list-user-repositories"
            }
        """.trimIndent()
        val httpException = HttpException(
            Response.error<Any>(
                404, ResponseBody.create(
                    MediaType.parse("application/json"),
                    userNotFoundJson
                )
            )
        )

        whenever(gitHubApi.fetchFollowers(userName))
            .thenReturn(Single.error(httpException))

        // when
        testCase.dispatchEffect(SearchFollowersEffect(userName))

        // then
        testCase.assertOutgoingEvents(UserNotFoundEvent)
    }
}
