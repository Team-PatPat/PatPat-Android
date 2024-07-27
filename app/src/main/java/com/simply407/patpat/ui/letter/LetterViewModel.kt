package com.simply407.patpat.ui.letter

import androidx.lifecycle.ViewModel
import com.simply407.patpat.data.letter

class LetterViewModel : ViewModel() {

    companion object{
        private const val ROOM1="복남이"
        private const val ROOM2="닥터 냉철한"
        private const val ROOM3="곽두팔"
        private const val ROOM4="코코"
    }

    private var letter_counselor=""
    private var letter_reciever=""
    private var letter_content=""
    private var letter_comment=""
    private var letter_image=0


    fun set_letter_info(letter :letter){
        letter_counselor=letter.counselor
        letter_reciever=letter.username
        letter_content=letter.content
        if(letter.counselor==ROOM1){
            letter_comment="unknown"
            letter_image=letter.commentImage
        }else{
            letter_comment=letter.commentString!!
            letter_image=-1
        }

    }

    fun get_letter_info() : letter{
        return letter(letter_counselor,letter_reciever,letter_content,letter_image,letter_comment)
    }

}