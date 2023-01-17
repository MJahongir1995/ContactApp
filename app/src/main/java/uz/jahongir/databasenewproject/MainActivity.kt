package uz.jahongir.databasenewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.UiThread
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import uz.jahongir.databasenewproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    @UiThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setupActionBarWithNavController(findNavController(R.id.viewPagerFragment))
    }
}