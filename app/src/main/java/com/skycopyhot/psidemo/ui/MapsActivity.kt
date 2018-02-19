package com.skycopyhot.psidemo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.skycopyhot.psidemo.R
import com.skycopyhot.psidemo.module.ELocation
import com.skycopyhot.psidemo.module.ResultData
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toSingle
import io.reactivex.subjects.AsyncSubject

class MapsActivity : BaseActivity(), OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener {

    private lateinit var mMap: GoogleMap
    private var selectedMarker: Marker? = null
    private val subject: AsyncSubject<Boolean> = AsyncSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        OnMapAndViewReadyListener(mapFragment, this)

        request(subject.toSingle()
                .flatMap {
                    logicRepository.getPsiData()
                }
                .subscribeBy(onSuccess = {
                    refreshUI(it)
                }, onError = {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_maps, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == R.id.action_refresh) {
            request(logicRepository.getPsiData()
                    .compose(progress())
                    .subscribeBy(onSuccess = {
                        refreshUI(it)
                    }, onError = {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }))
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap?: return
        with(mMap) {
            uiSettings.isZoomControlsEnabled = false

            setInfoWindowAdapter(PSIDataInfoWindowAdapter())

            setOnMarkerClickListener(markerClickListener)
            setOnMapClickListener { selectedMarker = null }
        }
        subject.onNext(true)
        subject.onComplete()
    }

    private val markerClickListener = GoogleMap.OnMarkerClickListener {
        if (it == selectedMarker) {
            selectedMarker = null
            true
        } else {
            selectedMarker = it
            false
        }
    }

    private fun refreshUI(resultData: ResultData?) {
        with(mMap) {
            val bounds = LatLngBounds.Builder()
            resultData?.getRegions()?.map { it ->
                val eLocation = when (it.name) {
                    ELocation.NATIONAL.name.toLowerCase() -> ELocation.NATIONAL
                    ELocation.WEST.name.toLowerCase() -> ELocation.WEST
                    ELocation.EAST.name.toLowerCase() -> ELocation.EAST
                    ELocation.SOUTH.name.toLowerCase() -> ELocation.SOUTH
                    ELocation.NORTH.name.toLowerCase() -> ELocation.NORTH
                    ELocation.CENTRAL.name.toLowerCase() -> ELocation.CENTRAL
                    else -> ELocation.NATIONAL
                }

                if (eLocation != ELocation.NATIONAL) {
                    val latLng = LatLng(it.location.latitude, it.location.longitude)
                    addMarker(MarkerOptions().apply {
                        position(latLng)
                        title(getString(R.string.marker_title))
                        snippet(resultData.getData(this@MapsActivity, eLocation))
                    })
                    bounds.include(latLng)
                }
            }

            moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50))
        }
    }

    internal inner class PSIDataInfoWindowAdapter : GoogleMap.InfoWindowAdapter {

        @SuppressLint("InflateParams")
        private val contentView: View = layoutInflater.inflate(R.layout.content_psi, null)

        override fun getInfoContents(marker: Marker?): View {

            contentView.findViewById<TextView>(R.id.tv_title).text = marker?.title
            contentView.findViewById<TextView>(R.id.tv_content).text = marker?.snippet

            return contentView
        }

        override fun getInfoWindow(p0: Marker?): View? {
            return null
        }
    }
}
