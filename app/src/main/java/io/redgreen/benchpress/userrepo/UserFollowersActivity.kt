package io.redgreen.benchpress.userrepo

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.view.View
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.android.BaseActivity
import io.redgreen.benchpress.architecture.android.listener.TextWatcherAdapter
import io.redgreen.benchpress.architecture.threading.DefaultSchedulersProvider
import io.redgreen.benchpress.userrepo.effecthandlers.UserRepoEffectHandler
import io.redgreen.benchpress.userrepo.http.StubGitHubApi
import io.redgreen.benchpress.userrepo.view.FollowersAdapter
import io.redgreen.benchpress.userrepo.view.UserRepoView
import io.redgreen.benchpress.userrepo.view.UserRepoViewRenderer
import kotlinx.android.synthetic.main.activity_user_followers.*
import kotlin.LazyThreadSafetyMode.NONE

class UserFollowersActivity : BaseActivity<UserRepoModel, UserRepoEvent, UserRepoEffect>(), UserRepoView {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, UserFollowersActivity::class.java))
        }
    }

    private val viewRenderer by lazy(NONE) {
        UserRepoViewRenderer(this)
    }

    private val gitHubApi by lazy(NONE) {
        StubGitHubApi()
    }

    override fun layoutResId(): Int {
        return R.layout.activity_user_followers
    }

    override fun setup() {
        usernameEditText.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(s: Editable) {
                val username = s.toString()
                eventSource.notifyEvent(if (username.isBlank()) UserNameClearedEvent else UserNameChangeEvent(username) )
            }
        })

        searchButton.setOnClickListener { eventSource.notifyEvent(SearchFollowersEvent) }

        followersRecyclerView.layoutManager = LinearLayoutManager(this)

        retryButton.setOnClickListener { eventSource.notifyEvent(RetryFetchFollowersEvent) }
    }

    override fun initialModel(): UserRepoModel =
        UserRepoModel.BLANK

    override fun updateFunction(
        model: UserRepoModel,
        event: UserRepoEvent
    ): Next<UserRepoModel, UserRepoEffect> =
        UserRepoLogic.update(model, event)

    override fun render(model: UserRepoModel) {
        viewRenderer.render(model)
    }

    override fun effectHandler(): ObservableTransformer<UserRepoEffect, UserRepoEvent> =
        UserRepoEffectHandler.create(gitHubApi, DefaultSchedulersProvider())

    override fun disableSearchButton() {
        searchButton.isEnabled = false
    }

    override fun enableSearchButton() {
        searchButton.isEnabled = true
    }

    override fun showBlankMessage() {
        messageTextView.text = "Search for someone you like :)"
    }

    override fun showLoading() {
        fetchingFollowersProgressBar.visibility = View.VISIBLE
    }

    override fun disableUserNameField() {
        usernameEditText.isEnabled = false
    }

    override fun hideFollowers() {
        followersRecyclerView.visibility = View.INVISIBLE
    }

    override fun hideNoFollowersMessage() {
        messageTextView.visibility = View.INVISIBLE
    }

    override fun hideUserNotFoundMessage() {
        messageTextView.visibility = View.INVISIBLE
    }

    override fun hideRetryMessage() {
        messageTextView.visibility = View.INVISIBLE
        retryButton.visibility = View.GONE
    }

    override fun enableUserNameField() {
        usernameEditText.isEnabled = true
    }

    override fun hideLoading() {
        fetchingFollowersProgressBar.visibility = View.INVISIBLE
    }

    override fun showFollowers(followers: List<User>) {
        with(followersRecyclerView) {
            visibility = View.VISIBLE
            adapter = FollowersAdapter(followers)
        }
    }

    override fun showNoFollowersFoundMessage() {
        with(messageTextView) {
            visibility = View.VISIBLE
            text = "No followers :("
        }
    }

    override fun showUserNotFoundMessage() {
        with(messageTextView) {
            visibility = View.VISIBLE
            text = "This used does not exist!"
        }
    }

    override fun showRetryMessage() {
        with(messageTextView) {
            visibility = View.VISIBLE
            text = "Something went wrong, please retry!"
        }
        retryButton.visibility = View.VISIBLE
    }
}
