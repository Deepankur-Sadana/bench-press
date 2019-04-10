package io.redgreen.benchpress.userrepo.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.redgreen.benchpress.R
import io.redgreen.benchpress.userrepo.User
import kotlinx.android.synthetic.main.user_list_item.view.*

class FollowersAdapter(
    private val followers: List<User>
) : RecyclerView.Adapter<FollowerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): FollowerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FollowerViewHolder(inflater.inflate(R.layout.user_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return followers.size
    }

    override fun onBindViewHolder(
        viewHolder: FollowerViewHolder,
        position: Int
    ) {
        viewHolder.bind(followers[position])
    }
}

class FollowerViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bind(user: User) {
        itemView.usernameTextView.text = user.username
    }
}
