package io.redgreen.benchpress.userrepo.http

import io.reactivex.Single
import io.redgreen.benchpress.userrepo.User
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.util.concurrent.TimeUnit

class StubGitHubApi : GitHubApi {
    override fun fetchFollowers(userName: String): Single<List<User>> {
        val response = when (userName) {
            "deepankur" -> getHasFollowersResponse()
            "nobody" -> getUserNotFoundResponse()
            "anzar" -> Single.error(RuntimeException("I wrote the code for Boeing 747 Max"))
            "ragunath" -> Single.just(emptyList())
            else -> getHasFollowersResponse()
        }

        return Single.timer(1500, TimeUnit.MILLISECONDS)
            .flatMap { response }
    }

    private fun getHasFollowersResponse(): Single<List<User>> {
        return Single.just(
            listOf(
                User("ragunath", "some-image-url"),
                User("lalit", "some-other-image-url")
            )
        )
    }

    private fun getUserNotFoundResponse(): Single<List<User>> {
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
        return Single.error<List<User>>(httpException)
    }
}
