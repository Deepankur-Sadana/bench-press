package io.redgreen.benchpress.github

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.redgreen.benchpress.github.http.BadRequestError
import io.redgreen.benchpress.github.http.FollowersApi
import io.redgreen.benchpress.test.EffectHandlerTestCase
import io.redgreen.benchpress.test.ImmediateSchedulersProvider
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class GitHubEffectHandlerTest {

    private val followersApi = mock<FollowersApi>()
    private val testCase =
        EffectHandlerTestCase(GitHubEffectHandler.create(followersApi, ImmediateSchedulersProvider()))
    private val userName = "nitesh-munda"

    @Test
    fun `when api return with success and has follower list, then dispatch list of followers event`() {
        val followers = listOf(Follower("ragunath", "some-avatar-url"))

        whenever(followersApi.fetchFollowers(userName))
            .thenReturn(Single.just(followers))


        // when
        testCase.dispatchEffect(FindFollowersEffect(userName))

        // then
        testCase.assertOutgoingEvents(HasFollowersEvent(followers))

    }

    @Test
    fun `when api return with success and has no follower,then dispatch no followers event`() {
        val noFollowerList = emptyList<Follower>()

        whenever(followersApi.fetchFollowers(userName))
            .thenReturn(Single.just(noFollowerList))

        // when
        testCase.dispatchEffect(FindFollowersEffect(userName))

        // then
        testCase.assertOutgoingEvents(HasNoFollowerFoundEvent)
    }

    @Test
    fun `when user in un authenticated, then dispatch  Bad request event`() {
        val userNotAuthenticatedContent = getErrorContent()

        val userNotAuthenticatedError = Response.error<Any>(
            401,
            ResponseBody.create(
                MediaType.parse("application/json"),
                userNotAuthenticatedContent
            )
        )

        whenever(followersApi.fetchFollowers(userName))
            .thenReturn(Single.error(HttpException(userNotAuthenticatedError)))

        // when
        testCase.dispatchEffect(FindFollowersEffect(userName))

        // then
        testCase.assertOutgoingEvents(BadRequestEvent(BadRequestError.UNAUTHENTICATED))

    }

    @Test
    fun `when user is unauthorized, then dispatch Bad Request event`() {
        val userNotAuthorizedContent = getErrorContent()

        val userNotAuthorizedError = Response.error<Any>(
            403,
            ResponseBody.create(
                MediaType.parse("application/json"),
                userNotAuthorizedContent
            )
        )

        whenever(followersApi.fetchFollowers(userName))
            .thenReturn(Single.error(HttpException(userNotAuthorizedError)))

        // when
        testCase.dispatchEffect(FindFollowersEffect(userName))

        // then
        testCase.assertOutgoingEvents(BadRequestEvent(BadRequestError.UNAUTHORIZED))
    }



    @Test
    fun `when user is not found, then dispatch`() {

        val userNotFoundContent = getErrorContent()

        val userNotFoundError = Response.error<Any>(
            404,
            ResponseBody.create(
                MediaType.parse("application/json"),
                userNotFoundContent
            )
        )


        whenever(followersApi.fetchFollowers(userName))
            .thenReturn(Single.error(HttpException(userNotFoundError)))

        //when
        testCase.dispatchEffect(FindFollowersEffect(userName))

        //then
        testCase.assertOutgoingEvents(BadRequestEvent(BadRequestError.NOT_FOUND))

    }
    private fun getErrorContent(): String {
        val userNotAuthenticatedContent = """
                {
                    "message": "Not Found",
                    "documentation_url": "https://developer.github.com/v3/users/followers/#list-followers-of-a-user"
                }
            """.trimIndent()
        return userNotAuthenticatedContent
    }
}