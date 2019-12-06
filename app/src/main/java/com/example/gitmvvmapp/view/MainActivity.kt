package com.example.gitmvvmapp.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitmvvmapp.R
import com.example.gitmvvmapp.adapter.RepositoryAdapter
import com.example.gitmvvmapp.model.Repository
import com.example.gitmvvmapp.viewmodel.GitViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GitViewModel

    private val compositeDisposable = CompositeDisposable()

    private val handler = Handler()

    private val myReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            handler.post {

                openFragment(intent?.getStringExtra("repositoryName")?:"")

            }
        }
    }

    private fun openFragment(name: String){
        val fragment = RepoFragment()

        val bundle = Bundle()
        bundle.putString("repositoryName", name)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .add(R.id.main_framelayout, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        registerReceiver(myReceiver, IntentFilter("from.rv.view"))
        viewModel = ViewModelProviders.of(this)
            .get(GitViewModel::class.java)

        compositeDisposable.add(viewModel.getRepositories("ShamarcusW-MAC")
            .subscribe ({ repositories ->

                displayRepositories(repositories)
            }, {throwable ->
                Log.d("TAG_ERROR", throwable.message.toString())
            })
        )
        compositeDisposable




    }

    private fun displayRepositories(repositories: List<Repository>){

        val adapter = RepositoryAdapter(repositories)
        repository_recyclerview.adapter = adapter
        val linear = LinearLayoutManager(this)
//        linear.orientation = RecyclerView.HORIZONTAL
        repository_recyclerview.layoutManager = linear

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }
}
