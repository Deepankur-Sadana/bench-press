package io.redgreen.benchpress.userrepo

import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.android.BaseActivity

class UserFollowersActivity : BaseActivity<UserRepoModel, UserRepoEvent, UserRepoEffect>() {


    override fun layoutResId(): Int {
        R.layout.activity_user_followers
    }

    override fun setup() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initialModel(): UserRepoModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateFunction(model: UserRepoModel, event: UserRepoEvent): Next<UserRepoModel, UserRepoEffect> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(model: UserRepoModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun effectHandler(): ObservableTransformer<UserRepoEffect, UserRepoEvent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}