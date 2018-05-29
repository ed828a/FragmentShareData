package com.dew.edward.fragmentsharedata.fragments


import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dew.edward.fragmentsharedata.R
import com.dew.edward.fragmentsharedata.viewmodel.FragmentViewModel
import kotlinx.android.synthetic.main.fragment_video_view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val VID_URI = "video uri"

/**
 * A simple [Fragment] subclass.
 * Use the [VideoViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class VideoViewFragment : Fragment() {
    // videoMode will provide this videoUri
    private val videoUriViewModel by lazy {
        ViewModelProviders.of(activity!!).get(FragmentViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        if (videoUriViewModel.videoUri != null) {
            Log.v(TAG, "videoUriViewModel: ${videoUriViewModel.videoUri.toString()}")

            videoView.setVideoURI(videoUriViewModel.videoUri)
            videoView.start()
        }
    }

    override fun onPause() {
        super.onPause()

        videoView.pause()
    }

    override fun onStop() {
        videoView.stopPlayback()
        super.onStop()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_view, container, false)
    }


    companion object {
        private val TAG = VideoViewFragment::class.qualifiedName
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * Activity can pass data by those params when creating this fragment
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment VideoViewFragment.
         */

        @JvmStatic
        fun newInstance() = VideoViewFragment()






    }
}
