package by.godevelopment.fourthtask.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.godevelopment.fourthtask.R
import by.godevelopment.fourthtask.commons.START_POINT_LAT
import by.godevelopment.fourthtask.commons.START_POINT_LNG
import by.godevelopment.fourthtask.commons.ZOOM_LEVEL
import by.godevelopment.fourthtask.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val mapsViewModel: MapsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setupEvent()
    }

    private fun setupEvent() {
        lifecycleScope.launchWhenStarted {
            mapsViewModel.uiEvent.collect {
                Snackbar
                    .make(binding.root, it, Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.snackbar_btn_reload))
                    { mapsViewModel.fetchGeographicData() }
                    .show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        lifecycleScope.launchWhenStarted {
            mapsViewModel.uiState
                .collect { uiState ->
                    uiState?.let {
                        if (uiState.isFetchingData)
                            Toast.makeText(
                                applicationContext,
                                getString(R.string.fragment_alert_load_started),
                                Toast.LENGTH_LONG
                            ).show()

                        map = googleMap
                        val zoomLevel = ZOOM_LEVEL
                        val homeLatLng = LatLng(START_POINT_LAT, START_POINT_LNG)

                        uiState.data.forEach { model ->
                            map.addMarker(
                                MarkerOptions()
                                    .position(
                                        LatLng(model.latitude, model.longitude)
                                    )
                                    .title(model.tittle_text)
                                    .snippet(model.snippet_text)
                            )
                        }
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
                    }
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.map_options, menu)
        // return true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        // Change the map type based on the user's selection.
        R.id.normal_map -> {
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            true
        }
        R.id.hybrid_map -> {
            map.mapType = GoogleMap.MAP_TYPE_HYBRID
            true
        }
        R.id.satellite_map -> {
            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            true
        }
        R.id.terrain_map -> {
            map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}