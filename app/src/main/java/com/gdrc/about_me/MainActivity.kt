package com.gdrc.about_me

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.gdrc.about_me.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //binding is a component wich bind this file with the layout
    //  the type is the name of the layout file ending with "Binding"
    private lateinit var binding: ActivityMainBinding

    //create an instance of MyName
    private val myName: MyName = MyName("Gabriel Rodriguez")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//      instead of setContentView, we connect the layout with the activity using DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//      We connect the "myName" instance to the "myName" variable in activity_main.xml file
        binding.myName = myName

//        the name of the button is the id we gived it to it
        binding.doneButton.setOnClickListener {
            addNickName(it)
        }
    }

    private fun addNickName(view: View) {

//      now we can access the components through binding variable, but instead writting
//        binding over and over, we refactor it to:
        binding.apply {
//          if  myName is not null, it can save the nicknameEdit.text into
            myName?.nickname = nicknameEdit.text.toString()
//          in order to refresh th UI with the new data, we need to invalidate
//          all binding expressions so that they get recreated with the new correct data
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }
//      Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}