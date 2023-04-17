package nl.michiel.trips.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import timber.log.Timber

val amsterdam = LatLng(52.369792, 4.89075924)

@Composable
fun TripsViewNoMap(onTapInfo: () -> Unit = { }) {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        Button(
            onClick = onTapInfo
        ) {
            Text(text = "Open details")
        }
    }
}

@Composable
fun TripsView(onTapInfo: () -> Unit = { }) {
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
                onInfoWindowClick = { onTapInfo() }
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
