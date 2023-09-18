package com.example.earningquizclone.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.earningquizclone.Model.User
import com.example.earningquizclone.R
import com.example.earningquizclone.databinding.FragmentProfileBinding
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


class ProfileFragment : Fragment() {
val binding:FragmentProfileBinding by lazy {
    FragmentProfileBinding.inflate(layoutInflater)
}
var isExpandable=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding.imageButton.setOnClickListener {
          if (isExpandable){
              binding.expandableConstraintlayout.visibility=View.VISIBLE
              binding.imageButton.setImageResource(R.drawable.arrowup)
              Log.d("before", "onCreateView: "+isExpandable)

          }
          else{
              binding.expandableConstraintlayout.visibility=View.GONE
              binding.imageButton.setImageResource(R.drawable.downarrow)
              Log.d("After", "onCreateView: "+isExpandable)

          }

          isExpandable= !isExpandable
      }
    Firebase.database.reference.child("Users")
        .child(Firebase.auth.currentUser!!.uid).addValueEventListener(
            object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var user=snapshot.getValue(User::class.java)

                    if (user != null) {
                        binding.age.text=user.age.toString()
                        binding.NAme.text=user?.name
                        binding.myNAme.text=user?.name
                        binding.Email.text=user?.email
                        binding.PAssword.text=user?.password
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