package ec.edu.tecnologicoloja.gps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btn_Mi_Ubicacion,btn_Mapa;
    EditText tf_latitud, tf_longitud;
    double longitud,latitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableMyLocation();
        btn_Mi_Ubicacion = findViewById(R.id.btn_Mi_Ubicacion);
        tf_latitud = findViewById(R.id.tf_Latitud);
        tf_longitud = findViewById(R.id.tf_Longitud);


    }

    public void onClick(View v) {

        miposicion();
    }
    public void onClickmapa(View v){
        Intent intent =new Intent(MainActivity.this,MapsActivity.class);
        intent.putExtra("latitud",latitud);
        intent.putExtra("longitud",longitud);
        startActivity(intent);
    }

    public void miposicion(){
        LocationManager objLocation=null;
        LocationListener objLocListener;
        objLocation=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        objLocListener=new miPosicion();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 1000);
        }

        objLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,0,objLocListener);

        if (objLocation.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            if (miPosicion.latitude!=0){
                latitud= miPosicion.latitude;
                longitud= miPosicion.longitude;
                tf_longitud.setText(longitud+"");
                tf_latitud.setText(latitud+"");
            }
        }
    }

    public void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, }, 1000);
        }
    }
}
