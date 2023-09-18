package com.example.earningquizclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.earningquizclone.Model.User
import com.example.earningquizclone.databinding.ActivityQuizActivtyBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class QuizActivty : AppCompatActivity() {
    val binding:ActivityQuizActivtyBinding by lazy{
        ActivityQuizActivtyBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.coinTxt.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialogFragment =WithdrawFragment()
            bottomSheetDialog.show(this.supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        binding.coinImage.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialogFragment =WithdrawFragment()
            bottomSheetDialog.show(this.supportFragmentManager,"Test")
            bottomSheetDialog.enterTransition
        }
        var img=intent.getIntExtra("categoryImg",0)
        binding.categoryImg.setImageResource(img)
        Firebase.database.reference.child("Users")
            .child(Firebase.auth.currentUser!!.uid).addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var user=snapshot.getValue(User::class.java)

                        if (user != null) {
                            binding.name.text=user?.name

                        }


                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
            )
    }
}