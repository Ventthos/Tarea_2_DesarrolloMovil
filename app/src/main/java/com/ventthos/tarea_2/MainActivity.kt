package com.ventthos.tarea_2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkExtraCheese = findViewById<CheckBox>(R.id.checkExtraCheese)
        val checkMushrooms = findViewById<CheckBox>(R.id.checkMushrooms)
        val checkBacon = findViewById<CheckBox>(R.id.checkBacon)
        val toggleExtras = findViewById<ToggleButton>(R.id.toggleExtras)
        val extrasContainer = findViewById<LinearLayout>(R.id.extrasContainer)
        val textSelected = findViewById<TextView>(R.id.textSelected)

        extrasContainer.visibility = View.GONE

        toggleExtras.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                extrasContainer.visibility = View.VISIBLE
            } else {
                extrasContainer.visibility = View.GONE
            }
        }

        fun actualizarSeleccion() {
            var extras = ""
            if (checkExtraCheese.isChecked) {
                extras += getString(R.string.extra_cheese) + ", "
            }
            if (checkMushrooms.isChecked) {
                extras += getString(R.string.mushrooms) + ", "
            }
            if (checkBacon.isChecked) {
                extras += getString(R.string.bacon) + ", "
            }
            if (extras.isNotEmpty()) {
                extras = extras.dropLast(2)
            }
            textSelected.text = getString(R.string.extras) + "$extras"
        }

        checkExtraCheese.setOnCheckedChangeListener { _, _ -> actualizarSeleccion() }
        checkMushrooms.setOnCheckedChangeListener { _, _ -> actualizarSeleccion() }
        checkBacon.setOnCheckedChangeListener { _, _ -> actualizarSeleccion() }
    }
}