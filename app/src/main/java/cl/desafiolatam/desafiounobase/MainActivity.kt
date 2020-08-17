package cl.desafiolatam.desafiounobase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var nameInput: TextInputEditText
    lateinit var advance: Button
    lateinit var container: ConstraintLayout
    lateinit var mSharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameInput = findViewById(R.id.name_input)
        advance = findViewById(R.id.login_button)
        container = findViewById(R.id.container)

        //se inicia la cajita donde guardar sharedPref
        val mFileName="cl.desafiolatam.desafiounobase"
        mSharedPref=getSharedPreferences(mFileName, Context.MODE_PRIVATE)

        setUpListeners()
        Log.d("caca",mSharedPref.getString("user1",""))
    }

    private fun setUpListeners() {
        advance.setOnClickListener {
            if (nameInput.text!!.isNotEmpty()) {
                val intent: Intent
                if (hasSeenWelcome()) {
                    intent = Intent(this, HomeActivity::class.java)
                } else {
                    mSharedPref.edit().putString("user1",nameInput.text.toString()).apply() //aca deberia guardar lo que ingreso el usuario en el nameInput en mSharedPref
                    intent = Intent(this, WelcomeActivity::class.java)
                }
                startActivity(intent)
            } else {
                Snackbar.make(container, "El nombre no puede estar vacío", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun hasSeenWelcome(): Boolean {
        /*if nombreIngresado esta contenido en sharedPref, ir a homeActivity, osea true*/
        if(mSharedPref.getString("user1","").contains(nameInput.toString())){
            return true
        }else{
            //mSharedPref.edit().putString("user1",nameInput.toString()).apply() //aca esta la primera extracccion
            return false
        }
    }
}
