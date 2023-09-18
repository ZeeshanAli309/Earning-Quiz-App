package com.example.earningquizclone.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earningquizclone.Adapter.HistoryAdapter
import com.example.earningquizclone.Model.ModelHistoryClass
import com.example.earningquizclone.Model.User
import com.example.earningquizclone.R
import com.example.earningquizclone.WithdrawFragment
import com.example.earningquizclone.databinding.FragmentHitoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HitoryFragment : Fragment() {
val binding:FragmentHitoryBinding by lazy {
    FragmentHitoryBinding.inflate(layoutInflater)
}
var listItem=ArrayList<ModelHistoryClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listItem.add(ModelHistoryClass("12:46","200"))
        listItem.add(ModelHistoryClass("05:46","200"))
        listItem.add(ModelHistoryClass("08:46","500"))
        listItem.add(ModelHistoryClass("11:46","100"))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

binding.rvHistory.layoutManager=LinearLayoutManager(requireContext())
        var adapter=HistoryAdapter(listItem)
        binding.rvHistory.adapter=adapter
        binding.rvHistory.setHasFixedSize(true)
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
        Firebase.database.reference.child("Users")
            .child(Firebase.auth.currentUser!!.uid).addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var user=snapshot.getValue(User::class.java)

                        if (user != null) {
                            binding.name.text=user.age.toString()

                        }
                        else{
                            Toast.makeText(inflater.context, "User not found", Toast.LENGTH_SHORT)
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
            )
        return binding.root
    }


}