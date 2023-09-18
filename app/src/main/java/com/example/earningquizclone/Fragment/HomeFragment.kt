package com.example.earningquizclone.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.earningquizclone.Adapter.CategoryAdapter
import com.example.earningquizclone.Model.CategoryModelClass
import com.example.earningquizclone.Model.User
import com.example.earningquizclone.R
import com.example.earningquizclone.WithdrawFragment
import com.example.earningquizclone.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    private val binding:FragmentHomeBinding by lazy{
        FragmentHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private var categorList=ArrayList<CategoryModelClass>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding.coinTxt.setOnClickListener {
           val bottomSheetDialog:BottomSheetDialogFragment=WithdrawFragment()
           bottomSheetDialog.show(requireActivity().supportFragmentManager,"Test")
           bottomSheetDialog.enterTransition
       }
        binding.coinImage.setOnClickListener {
            val bottomSheetDialog:BottomSheetDialogFragment=WithdrawFragment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        Firebase.database.reference.child("Users")
            .child(Firebase.auth.currentUser!!.uid).addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var user=snapshot.getValue(User::class.java)

                        if (user != null) {
                            binding.name.text=user.name.toString()

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categorList.add(CategoryModelClass(R.drawable.scince,"Science"))
        categorList.add(CategoryModelClass(R.drawable.englishs,"English"))
        categorList.add(CategoryModelClass(R.drawable.mathmetic,"Mathmatics"))
        categorList.add(CategoryModelClass(R.drawable.history,"History"))
        binding.categoryRecyclerview.layoutManager= GridLayoutManager(requireContext(),2)
        var adapter= CategoryAdapter(categorList,requireActivity())
        binding.categoryRecyclerview.adapter=adapter
        binding.categoryRecyclerview.setHasFixedSize(true)

    }
    companion object {

    }
}