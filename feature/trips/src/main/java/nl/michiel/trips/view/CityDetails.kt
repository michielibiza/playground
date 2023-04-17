package nl.michiel.trips.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CityDetails(onNavigate: (content: BottomSheet) -> Unit = { }) {
    Column(Modifier.padding(16.dp, 8.dp)) {
        Text("Amsterdam", style = MaterialTheme.typography.h2)
        Spacer(Modifier.height(8.dp))
        Text("bla ".repeat(100), style = MaterialTheme.typography.body1)
        Spacer(Modifier.height(8.dp))
        Text("Highlights", style = MaterialTheme.typography.h3)
        Spacer(Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                Button({ onNavigate(BottomSheet.RESTAURANT) }) {
                    Text("FEBO")
                }
            }
            item {
                Button({ onNavigate(BottomSheet.HOTEL) }) {
                    Text("Amstel Hotel")
                }
            }
            item {
                Button({ onNavigate(BottomSheet.RESTAURANT) }) {
                    Text("Salsa Shop")
                }
            }
            item {
                Button({ onNavigate(BottomSheet.PARK) }) {
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
fun RestaurantDetails(onNavigate: (content: BottomSheet) -> Unit = { }) {
    Column(Modifier.padding(16.dp, 8.dp)) {
        Text("Restaurant", style = MaterialTheme.typography.h2)
        Spacer(Modifier.height(8.dp))
        Text("bla ".repeat(100), style = MaterialTheme.typography.body1)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { onNavigate(BottomSheet.CITY) }) {
            Text("Amsterdam")
        }
    }
}

@Composable
fun HotelDetails(onNavigate: (content: BottomSheet) -> Unit = { }) {
    Column(Modifier.padding(16.dp, 8.dp)) {
        Text("Hotel", style = MaterialTheme.typography.h2)
        Spacer(Modifier.height(8.dp))
        Text("bla ".repeat(100), style = MaterialTheme.typography.body1)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { onNavigate(BottomSheet.CITY) }) {
            Text("Amsterdam")
        }
    }
}

@Composable
fun ParkDetails(onNavigate: (content: BottomSheet) -> Unit = { }) {
    Column(Modifier.padding(16.dp, 8.dp)) {
        Text("Park", style = MaterialTheme.typography.h2)
        Spacer(Modifier.height(8.dp))
        Text("bla ".repeat(100), style = MaterialTheme.typography.body1)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { onNavigate(BottomSheet.CITY) }) {
            Text("Amsterdam")
        }
    }
}
