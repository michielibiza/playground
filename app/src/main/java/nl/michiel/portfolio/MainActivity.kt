package nl.michiel.portfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nl.michiel.portfolio.view.TopLevelNavigation
import nl.michiel.portfolio.view.theme.PortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioTheme {
                TopLevelNavigation()
            }
        }
    }
}
