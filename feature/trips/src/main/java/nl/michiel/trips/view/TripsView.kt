package nl.michiel.trips.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import timber.log.Timber

val amsterdam = LatLng(52.369792, 4.89075924)

@Composable
fun TripsView(onShowDetails: (content: PlaceDetails) -> Unit = { }) {
    val context = LocalContext.current
    val mapAvailable = remember {
        GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS
    }
    if (mapAvailable) {
        TripsViewOnMap(onShowDetails)
    } else {
        Box(Modifier.fillMaxSize(), Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("no map available because of play services")
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { onShowDetails(PlaceDetails.CITY) }
                ) {
                    Text(text = "Open details")
                }
            }
        }
    }
}

@Composable
fun TripsViewOnMap(onNavigate: (content: PlaceDetails) -> Unit = { }) {
    Timber.d("maps key: ")
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(amsterdam, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,

            ) {
            MarkerInfoWindow(
                state = MarkerState(position = amsterdam),
                title = "Amsterdam",
                snippet = "bla bla bla bla",
                onInfoWindowClick = { onNavigate(PlaceDetails.CITY) }
            ) { marker ->
                Column(
                    Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.DarkGray)
                        .padding(8.dp)) {
                    marker.title?.let { Text(it) }
                    with(marker.position) { Text("$latitude $longitude") }
                }
            }
        }
    }
}
