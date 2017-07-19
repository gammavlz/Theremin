package com.example.kdash.theremin;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private SensorManager manejadorSensores;
    public static DecimalFormat FORMATO_DECIMAL;
    //ProgressBar barra;

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        manejadorSensores.registerListener(this,
                manejadorSensores.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        FORMATO_DECIMAL = new DecimalFormat("#.000",symbols);

        manejadorSensores = (SensorManager) getSystemService(SENSOR_SERVICE);

        /*barra= (ProgressBar) findViewById(R.id.progressBar2);
        barra.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                barra.setProgress(25);
            }
        });*/
    }


    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType()== Sensor.TYPE_MAGNETIC_FIELD)
        {
            float magX = event.values[0];
            float magY = event.values[1];
            float magZ = event.values[2];

            double medicion = Math.sqrt((magX*magX)+(magY*magY)+(magZ*magZ));

            if(medicion>50.000)
            {

                Toast.makeText(getApplicationContext(), "aqui debe de sonar esta onda",Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
