package com.noble.astula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.noble.astula.ui.theme.AstulaTheme
import com.noble.features.wardrobe.WardrobeViewModel

class MainActivity : ComponentActivity() {

    private lateinit var wardrobeViewModel: WardrobeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        wardrobeViewModel = (application as AstulaApplication).diContainer.resolve(WardrobeViewModel::class.java)
        setContent {
            AstulaTheme {
                AstulaApp(
                    wardrobeViewModel = wardrobeViewModel
                )
            }
        }
    }
}
