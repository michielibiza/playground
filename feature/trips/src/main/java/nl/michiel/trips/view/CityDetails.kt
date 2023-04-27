package nl.michiel.trips.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailsFor(details: PlaceDetails, onShowDetails: (PlaceDetails) -> Unit) {
    when (details) {
        PlaceDetails.CITY -> CityDetails(onShowDetails)
        PlaceDetails.HOTEL -> HotelDetails(onShowDetails)
        PlaceDetails.RESTAURANT -> RestaurantDetails(onShowDetails)
        PlaceDetails.PARK -> ParkDetails(onShowDetails)
        else -> {}
    }
}

@Composable
fun CityDetails(onNavigate: (content: PlaceDetails) -> Unit = { }) {
    Column(Modifier.padding(16.dp, 8.dp)) {
        Text("Amsterdam", style = MaterialTheme.typography.h2)
        Spacer(Modifier.height(8.dp))
        Text("bla ".repeat(100), style = MaterialTheme.typography.body1)
        Spacer(Modifier.height(8.dp))
        Text("Highlights", style = MaterialTheme.typography.h3)
        Spacer(Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                Button({ onNavigate(PlaceDetails.RESTAURANT) }) {
                    Text("FEBO")
                }
            }
            item {
                Button({ onNavigate(PlaceDetails.HOTEL) }) {
                    Text("Amstel Hotel")
                }
            }
            item {
                Button({ onNavigate(PlaceDetails.RESTAURANT) }) {
                    Text("Salsa Shop")
                }
            }
            item {
                Button({ onNavigate(PlaceDetails.PARK) }) {
                    Text("Vondelpark")
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Text("Friend's recommendations", style = MaterialTheme.typography.h3)
        Spacer(Modifier.height(8.dp))
        Text("bla ".repeat(140), style = MaterialTheme.typography.body1)
    }
}

@Composable
fun RestaurantDetails(onNavigate: (content: PlaceDetails) -> Unit = { }) {
    Column(Modifier.padding(16.dp, 8.dp)) {
        Text("Restaurant", style = MaterialTheme.typography.h2)
        Spacer(Modifier.height(8.dp))
        Text("bla ".repeat(100), style = MaterialTheme.typography.body1)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { onNavigate(PlaceDetails.CITY) }) {
            Text("Amsterdam")
        }
    }
}

@Composable
fun HotelDetails(onNavigate: (content: PlaceDetails) -> Unit = { }) {
    Column(Modifier.padding(16.dp, 8.dp)) {
        Text("Hotel", style = MaterialTheme.typography.h2)
        Spacer(Modifier.height(8.dp))
        Text("bla ".repeat(100), style = MaterialTheme.typography.body1)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { onNavigate(PlaceDetails.CITY) }) {
            Text("Amsterdam")
        }
    }
}

@Composable
fun ParkDetails(onNavigate: (content: PlaceDetails) -> Unit = { }) {
    Column(Modifier.padding(16.dp, 8.dp)) {
        Text("Park", style = MaterialTheme.typography.h2)
        Spacer(Modifier.height(8.dp))
        Text("bla ".repeat(100), style = MaterialTheme.typography.body1)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { onNavigate(PlaceDetails.CITY) }) {
            Text("Amsterdam")
        }
    }
}
