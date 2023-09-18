package com.example.earningquizclone.Fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.earningquizclone.R
import com.example.earningquizclone.WithdrawFragment
import com.example.earningquizclone.databinding.FragmentSpinBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SpinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpinFragment : Fragment() {

private lateinit var binding: FragmentSpinBinding
private lateinit var timer: CountDownTimer
private val itemTitles= arrayOf("100","Try Again","500","Try Again","200","Try Again")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSpinBinding.inflate(inflater,container,false)
        binding.coinTxt.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialogFragment = WithdrawFragment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        binding.coinImage.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialogFragment = WithdrawFragment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        return binding.root
    }
    fun showResult(itemTitle:String){
        Toast.makeText(requireContext(),itemTitle,Toast.LENGTH_SHORT).show()
        binding.Spin.isEnabled=true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Spin.setOnClickListener {
            binding.Spin.isEnabled=false
            val spin= Random.nextInt(5)
            val deg=60f*spin
            timer=object :CountDownTimer(5000,50){
                var rotation=0f
                override fun onTick(millisUntilFinished: Long) {
                    rotation+=5f
                    if (rotation>=deg){
                        rotation=deg
                        timer.cancel()
                        showResult(itemTitles[spin])
                    }
                    binding.wheel.rotation=rotation
                }

                override fun onFinish() {

                }

            }.start()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SpinFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpinFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}