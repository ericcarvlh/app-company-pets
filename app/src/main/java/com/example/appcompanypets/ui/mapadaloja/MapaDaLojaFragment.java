package com.example.appcompanypets.ui.mapadaloja;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcompanypets.R;
import com.example.appcompanypets.databinding.FragmentMapaDaLojaBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

public class MapaDaLojaFragment extends Fragment
{
    public MapaDaLojaFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa_da_loja, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback()
        {
            @Override
            public void onMapReady(@NonNull @NotNull GoogleMap googleMap)
            {
                LatLng BG = new LatLng(-23.520441410534357, -46.72854943365466);
                final CameraPosition position = new CameraPosition.Builder().target(BG).bearing(45).tilt(70).zoom(18).build();
                final MarkerOptions markerOptions = new MarkerOptions();
                CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
                markerOptions.position(BG).title("Company Pets").snippet("Loja company pets");
                googleMap.addMarker(markerOptions);
                googleMap.animateCamera(update);
            }
        });

        return view;
    }
}