package my.edu.tarc.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.edu.tarc.bmi.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    //Module leve
    //Step 1: Cerate an instance of the View Binding Class
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Step 2: Link binding object to UI
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Step 3: Set the root of the binding as the UI
        setContentView(binding.root)

        //From this onwards - use the binding class to access UI components
        binding.buttonCalculate.setOnClickListener{
            //Input validate
            if (binding.editTextWeight.text.isEmpty()){
                binding.editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }
            if (binding.editTextHeight.text.isEmpty()){
                binding.editTextHeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            val weight = binding.editTextWeight.text.toString().toFloat()
            val height = binding.editTextHeight.text.toString().toFloat()
            val bmi = weight/(height/100).pow(2)

            //Display
            //binding.textViewBMI.text = String.format("%s %.2f", getString(R.string.bmi), bmi)
            //binding.imageViewBMI.setImageResource(R.drawable.normal)

            if (bmi < 18.5){
                binding.textViewBMI.text = String.format("%s %.2f (%s)", getString(R.string.bmi), bmi, getString(R.string.underweight))
                binding.imageViewBMI.setImageResource(R.drawable.under)
            }else if (bmi > 18.5 && bmi < 24.9){
                binding.textViewBMI.text = String.format("%s %.2f (%s)", getString(R.string.bmi), bmi, getString(R.string.normal))
                binding.imageViewBMI.setImageResource(R.drawable.normal)
            }else if (bmi > 25){
                binding.textViewBMI.text = String.format("%s %.2f (%s)", getString(R.string.bmi), bmi, getString(R.string.overweight))
                binding.imageViewBMI.setImageResource(R.drawable.over)
            }
        }
        binding.buttonReset.setOnClickListener{

        }

        binding.imageViewMoreInfo.setOnClickListener{
            //Create an instance of intent class
            val intent = Intent(this, MoreInfoActivity::class.java)
            startActivity(intent)
        }
    }
}