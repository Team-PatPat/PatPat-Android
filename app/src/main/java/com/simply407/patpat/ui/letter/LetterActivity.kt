package com.simply407.patpat.ui.letter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.simply407.patpat.R
import com.simply407.patpat.data.letter
import com.simply407.patpat.databinding.ActivityLetterBinding

class LetterActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLetterBinding
    private val manager =supportFragmentManager
    private lateinit var viewModel : LetterViewModel2

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityLetterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this)[LetterViewModel2::class.java]

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
        }

        val send1=intent.getStringExtra("letter_room")!!  //counselorId 는 이름
        val send2=intent.getStringExtra("letter_user")!! //내 이름
        val send3=intent.getStringExtra("letter_content")!! //content
        val send4=intent.getIntExtra("letter_image",-1) // 이미지
        val send5=intent.getStringExtra("letter_comment")!! //코멘트
        viewModel.set_letter_info(letter(send1,send2,send3,send4,send5))

        binding.appbar2.appbarLetterTitle.text=send1+"의 편지"
        binding.appbar2.appbarLetterBackBtn.setOnClickListener{
            finish()
        }

//        if(savedInstanceState==null){
//            val transaction=manager.beginTransaction()
//            val fragmentChat = LetterFragment()
//            transaction.replace(R.id.letterfragment,fragmentChat)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }


    }

}