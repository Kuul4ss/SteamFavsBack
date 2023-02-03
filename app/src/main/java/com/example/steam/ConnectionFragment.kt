package com.example.steam

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConnectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var auth: FirebaseAuth

    lateinit var loginButton: Button
    lateinit var signupButton: Button
    lateinit var emailInput: EditText
    lateinit var passwordInput: EditText
    lateinit var forgotInput: Button

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
            //view?.findNavController()?.navigate(R.id.searchFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connection, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConnectionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConnectionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.loginButton = (view.findViewById<FrameLayout>(R.id.button_login))[0] as Button;
        loginButton.setText(R.string.login);
        this.signupButton = view.findViewById(R.id.button_signup);
        signupButton.setText(R.string.create_new_account);
        this.emailInput = (view.findViewById<FrameLayout>(R.id.email))[0] as EditText;
        emailInput.setHint(R.string.email);

        this.passwordInput = (view.findViewById<FrameLayout>(R.id.password))[0] as EditText;
        passwordInput.setHint(R.string.password)

        this.forgotInput = view.findViewById(R.id.button_forgot);

        signupButton.setOnClickListener {
            goToSignUp(view)
        }

        forgotInput.setOnClickListener {
            goToForgottenPassword(view)
        }

        loginButton.setOnClickListener {
            tryToConnect(view);
        }

    }

    fun goToSignUp(v: View) {
        v.findNavController().navigate(R.id.signUpFragment)
    }

    fun goToForgottenPassword(v: View) {
        v.findNavController().navigate(R.id.forgottenPasswordFragment)
    }

    fun tryToConnect(v: View) {
        val email: String = this.emailInput.text.toString()
        val password: String = this.passwordInput.text.toString()

        auth.signInWithEmailAndPassword(email, password)

            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    v.findNavController().navigate(R.id.searchFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(this.context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}

