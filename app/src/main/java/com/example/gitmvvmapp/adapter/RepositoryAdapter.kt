package com.example.gitmvvmapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitmvvmapp.R
import com.example.gitmvvmapp.model.Repository
import com.example.gitmvvmapp.view.RepoInfo

class  RepositoryAdapter(val repoList: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.repository_item_layout,
                parent,
                false)

        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {

        return repoList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {

        holder.repoName.text = repoList[position].name

        holder.itemView.setOnClickListener{
//            holder.itemView.context.applicationContext.sendBroadcast(Intent("from.rv.view").also {
//                it.putExtra("repositoryName", repoList[position].name)
            val intent = Intent(it.context, RepoInfo::class.java)
            intent.putExtra("name", repoList[position].name)
            intent.putExtra("url", repoList[position].htmlUrl)
            intent.putExtra("avatar", repoList[position].owner.avatarUrl)
            it.context.startActivity(intent)

            }
        }

    inner class RepositoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


        val repoName: TextView = itemView.findViewById(R.id.repository_textview)

    }
}