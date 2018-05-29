package com.dew.edward.fragmentsharedata

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.dew.edward.fragmentsharedata.fragments.VideoIntentFragment
import com.dew.edward.fragmentsharedata.fragments.VideoViewFragment

class MainActivity : AppCompatActivity(), VideoIntentFragment.OnFragmentVideoUriListener {

    private val TAG = MainActivity::class.qualifiedName

    override fun onFragmentVideoUri(uri: Uri?) {

        Log.v(TAG, "video uri: $uri")
        uri?.let {
            val videoViewFragment = VideoViewFragment.newInstance(it)
            replaceFragment(videoViewFragment)
        }
//        if (uri != null) {
//            val videoViewFragment = VideoViewFragment.newInstance(uri)
//            replaceFragment(videoViewFragment)
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoIntentFragment = VideoIntentFragment.newInstance()
        replaceFragment(videoIntentFragment)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
