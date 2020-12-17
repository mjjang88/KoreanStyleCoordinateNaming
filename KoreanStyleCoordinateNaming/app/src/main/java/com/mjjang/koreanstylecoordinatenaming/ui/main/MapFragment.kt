package com.mjjang.koreanstylecoordinatenaming.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mjjang.koreanstylecoordinatenaming.R
import com.mjjang.koreanstylecoordinatenaming.databinding.FragmentMapBinding
import com.mjjang.koreanstylecoordinatenaming.util.CoordConverterUtil
import com.naver.maps.geometry.Utmk
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private lateinit var naverMap: NaverMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)

        binding.searchLocate.setOnClickListener {
            binding.editLocate.editableText.toString().apply {
                if (isNullOrBlank()) {
                    return@setOnClickListener
                }

                val utmk = CoordConverterUtil.getUtmkFromName(this)
                val latLng = utmk.toLatLng()

                val cameraUpdate = CameraUpdate.scrollTo(latLng)
                naverMap.moveCamera(cameraUpdate)
            }
        }



        return binding.root
    }

    override fun onMapReady(p0: NaverMap) {
        naverMap = p0

        p0.setOnMapLongClickListener { pointF, latLng ->
            val utmk = Utmk.valueOf(latLng)
            val name = CoordConverterUtil.getKrCoordNameFromUtmk(utmk.x.toInt(), utmk.y.toInt())
            Toast.makeText(requireContext(), "한글 좌표계 : $name", Toast.LENGTH_SHORT).show()
        }
    }
}