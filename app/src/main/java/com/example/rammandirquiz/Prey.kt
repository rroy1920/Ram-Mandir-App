package com.example.rammandirquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.airbnb.lottie.LottieAnimationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Prey.newInstance] factory method to
 * create an instance of this fragment.
 */
class Prey : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prey, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val anim1= view.findViewById<LottieAnimationView>(R.id.anim_firework)
        val anim2= view.findViewById<LottieAnimationView>(R.id.anim_flower_two)

        val anim1Btn= view.findViewById<ImageButton>(R.id.redFlBtn)
        val anim2Btn= view.findViewById<ImageButton>(R.id.crBtn)
        val bell= view.findViewById<ImageButton>(R.id.flBtn)

        anim1Btn.setOnClickListener {
            anim1.playAnimation()
        }

        anim2Btn.setOnClickListener {
            anim2.playAnimation()
        }

    }
}