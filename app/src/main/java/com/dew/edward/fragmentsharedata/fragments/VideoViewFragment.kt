package com.dew.edward.fragmentsharedata.fragments


import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dew.edward.fragmentsharedata.R
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
    // TODO: Rename and change types of parameters
    private var videoUri: Uri? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (arguments != null){
//            videoUri = arguments?.getParcelable(VID_URI)
//        }
        //same as above
        arguments?.let {
            videoUri = it.getParcelable(VID_URI)
        }
    }

    override fun onStart() {
        super.onStart()

        videoView.setVideoURI(videoUri)
        videoView.start()
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
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Uri) =
                VideoViewFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(VID_URI, param1)
                    }
                }

        // it's same as below
        fun newInstance2(uri: Uri): VideoViewFragment {
            val fragment = VideoViewFragment()
            val args = Bundle()
            args.putParcelable(VID_URI, uri)
            fragment.arguments = args
            return fragment
        }




    }
}
