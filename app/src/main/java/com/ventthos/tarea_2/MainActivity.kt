package com.ventthos.tarea_2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var pizzaSpinner: Spinner
    lateinit var checkExtraCheese:CheckBox
    lateinit var checkMushrooms: CheckBox
    lateinit var checkBacon: CheckBox
    lateinit var toggleExtras: ToggleButton
    lateinit var extrasContainer: LinearLayout
    lateinit var textSelected: TextView
    lateinit var pizzasDisplay: ImageView
    lateinit var shippingRequired: Switch
    lateinit var shippingImageView: ImageView
    lateinit var masaDisplay : ImageView
    lateinit var masatradicionalButton: Button
    lateinit var masaespecialButton: Button
    lateinit var masapremiumButton: Button

    val pizzaDefault = R.drawable.pizzadefault
    val pizzaImages = listOf(
        R.drawable.pizza_de_peperoni,
        R.drawable.pizza_hawaiana,
        R.drawable.pizza_de_queso,
        R.drawable.pizza_vegetariana
    )
    val shipImage = R.drawable.shipping
    val onRestaurantImage = R.drawable.mesa


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkExtraCheese = findViewById(R.id.checkExtraCheese)
        checkMushrooms  = findViewById(R.id.checkMushrooms)
        checkBacon = findViewById(R.id.checkBacon)
        toggleExtras = findViewById(R.id.toggleExtras)
        extrasContainer  = findViewById(R.id.extrasContainer)
        textSelected = findViewById(R.id.textSelected)
        masaDisplay = findViewById(R.id.masasDisplay)
        masatradicionalButton = findViewById(R.id.masaNormalButton)
        masapremiumButton = findViewById(R.id.masaPremiumButton)
        masaespecialButton = findViewById(R.id.masaEspecialButton)
        pizzaSpinner = findViewById(R.id.pizzaSpinner)
        val spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.pizzaOptions, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pizzaSpinner.adapter = spinnerAdapter



        pizzasDisplay = findViewById(R.id.pizzasDisplay)
        shippingRequired = findViewById(R.id.switchShipping)

        shippingImageView = findViewById(R.id.shippingImage)

        extrasContainer.visibility = View.GONE

        toggleExtras.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                extrasContainer.visibility = View.VISIBLE
            } else {
                extrasContainer.visibility = View.GONE
            }
        }



        checkExtraCheese.setOnCheckedChangeListener { _, _ -> actualizarSeleccion() }
        checkMushrooms.setOnCheckedChangeListener { _, _ -> actualizarSeleccion() }
        checkBacon.setOnCheckedChangeListener { _, _ -> actualizarSeleccion() }

        pizzaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                pizzasDisplay.setImageResource(pizzaDefault )
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                pizzasDisplay.setImageResource(pizzaImages[position])
            }
        }

        shippingRequired.setOnCheckedChangeListener{ _, isChecked->
            if(isChecked){
                shippingImageView.setImageResource(shipImage)
            }
            else{
                shippingImageView.setImageResource(onRestaurantImage)
            }
        }
        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { _, checkedId ->
            val radioButton = findViewById<RadioButton>(checkedId)
            val selectedOption = radioButton.text.toString()
            if (selectedOption == "Masa Normal") masaDisplay.setImageResource(R.drawable.masatradicional)
            if (selectedOption == "Masa Premium") masaDisplay.setImageResource(R.drawable.masapremium)
            if (selectedOption == "Masa Especial") masaDisplay.setImageResource(R.drawable.masaespecial)
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

}