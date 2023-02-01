package com.example.steam

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.get
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var signButton: Button
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var emailInput: EditText
    lateinit var passwordVerifInput: EditText

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            System.out.println("Home")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.signButton = (view.findViewById<FrameLayout>(R.id.button_signup))[0] as Button
        signButton.setText(R.string.sign_on)

        this.usernameInput = (view.findViewById<FrameLayout>(R.id.username))[0] as EditText
        usernameInput.setHint(R.string.username)

        this.passwordInput = (view.findViewById<FrameLayout>(R.id.password))[0] as EditText
        passwordInput.setHint(R.string.password)
        passwordInput.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD

        this.emailInput = (view.findViewById<FrameLayout>(R.id.email))[0] as EditText
        emailInput.setHint(R.string.email)
        emailInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        this.passwordVerifInput = (view.findViewById<FrameLayout>(R.id.password_verif))[0] as EditText
        passwordVerifInput.setHint(R.string.password_verification)
        passwordVerifInput.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD

        signButton.setOnClickListener {
            tryToSignup()
        }
    }

    fun tryToSignup() {
        auth.createUserWithEmailAndPassword(this.emailInput.text.toString(), this.passwordInput.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this.context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}