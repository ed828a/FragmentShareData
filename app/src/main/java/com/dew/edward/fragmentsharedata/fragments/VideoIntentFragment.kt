package com.dew.edward.fragmentsharedata.fragments

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dew.edward.fragmentsharedata.R
import com.dew.edward.fragmentsharedata.R.id.buttonPlay
import com.dew.edward.fragmentsharedata.R.id.buttonRecord
import com.dew.edward.fragmentsharedata.viewmodel.FragmentViewModel
import kotlinx.android.synthetic.main.fragment_video_intent.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [VideoIntentFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [VideoIntentFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class VideoIntentFragment : Fragment() {

    private var videoUri: Uri? = null
    private val VIDEO_APP_REQUEST_CODE = 1000
    private val videoUriViewModel by lazy {
        activity?.let { ViewModelProviders.of(it).get(FragmentViewModel::class.java) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_intent, container, false)
    }

    private fun startVideoViewFragment(){
        val videoViewFragment = VideoViewFragment.newInstance()
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.apply {
            replace(R.id.fragmentContainer, videoViewFragment)
            addToBackStack(null)
            commit()
        }
    }
    // send data to Activity
    private fun callVideoApp(){
        val videoCaptureIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if(videoCaptureIntent.resolveActivity(activity?.packageManager) != null){
            startActivityForResult(videoCaptureIntent, VIDEO_APP_REQUEST_CODE)
        }
    }

    // get data from Activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            VIDEO_APP_REQUEST_CODE -> {
                if (resultCode == RESULT_OK){
                    videoUri = data?.data
                    Log.v(TAG, "onActivityResult: ${videoUri.toString()}")
                }
            }
            else -> Log.e(TAG, "Unrecognized request code $requestCode")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonRecord.setOnClickListener {
            callVideoApp()
        }

        buttonPlay.setOnClickListener {
            // ViewModel will pass the videoUri to the VideoViewFragment
            videoUri?.let {
                videoUriViewModel?.videoUri = it
            }
            startVideoViewFragment()
        }
    }

    companion object {
        val TAG = VideoIntentFragment::class.qualifiedName
        @JvmStatic
        fun newInstance() = VideoIntentFragment()
//                VideoIntentFragment().apply {
//                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
//                    }
//                }
    }
}
