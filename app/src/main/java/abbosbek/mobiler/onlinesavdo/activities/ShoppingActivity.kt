package abbosbek.mobiler.onlinesavdo.activities

import abbosbek.mobiler.onlinesavdo.R
import abbosbek.mobiler.onlinesavdo.databinding.ActivityShoppingBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

class ShoppingActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityShoppingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.shoppingNavHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}