package com.example.gitmvvmapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitmvvmapp.R
import com.example.gitmvvmapp.model.Commit
import com.example.gitmvvmapp.model.Commits

class CommitAdapter(private val commitList: List<Commits>):
    RecyclerView.Adapter<CommitAdapter.CommitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.commit_item_layout,
                parent,
                false)

        return CommitViewHolder(view)
    }

    override fun getItemCount(): Int = commitList.size

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        holder.apply {
            committerDate.text = commitList[position].commit.committer.date
            committerName.text = commitList[position].commit.committer.name
            committerTitle.text = commitList[position].commit.message
        }
    }


    inner class CommitViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val committerName: TextView = itemView.findViewById(R.id.committer_textview)
        val committerTitle: TextView = itemView.findViewById(R.id.commit_title_textview)
        val committerDate: TextView = itemView.findViewById(R.id.committer_date_textview)

    }
}