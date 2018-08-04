package com.example.diegoperez.proyectofinalcursoaltice_comparador_de_fechas;

import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Principal_Activity extends AppCompatActivity implements View.OnClickListener {


   TextView txtFechaInicial, txtFechaFinal, txtResultado;
   EditText fechaInicial, fechaFinal;
   Date fechaI, fechaF = null;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_);

        txtFechaInicial = findViewById(R.id.Text_actual);
        txtFechaFinal = findViewById(R.id.Text_A_Comparar);
        txtResultado = findViewById(R.id.Text_Resultado);

        fechaInicial = findViewById(R.id.EditText_Fecha_actual);
        fechaFinal = findViewById(R.id.EditText_Fecha_a_Comparar);

        Button BotonCalcular = findViewById(R.id.Boton_Calcular);
        BotonCalcular.setOnClickListener(this);

        Button BotonLimpiar = findViewById(R.id.Boton_Limpiar);
        BotonLimpiar.setOnClickListener(this);

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy-hh.mm.ss");
        fechaInicial.setHint(simpleDateFormat.toPattern());
        fechaFinal.setHint(simpleDateFormat.toPattern());

    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.Boton_Calcular:
            {
                try {
                    fechaI = simpleDateFormat.parse(fechaInicial.getText().toString());
                    fechaF = simpleDateFormat.parse(fechaFinal.getText().toString());

                    //fechaF = new Date(System.currentTimeMillis());
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                txtResultado.setText(getDiferencia(fechaI, fechaF));
                break;
            }

            case R.id.Boton_Limpiar:
            {
                fechaInicial.setText("");
                fechaFinal.setText("");
                txtResultado.setText("");
                break;
            }

            default:
            {
                break;
            }
        }
    }




    public String getDiferencia(Date fechaInicial, Date fechaFinal){

        long diferencia = fechaFinal.getTime() - fechaInicial.getTime();

        Log.i("MainActivity", "fechaInicial : " + fechaInicial);
        Log.i("MainActivity", "fechaFinal : " + fechaFinal);

        long segsMilli = 1000;
        long minsMilli = segsMilli * 60;
        long horasMilli = minsMilli * 60;
        long diasMilli = horasMilli * 24;
       // long AnosMilli = diasMilli * 365;

        long diasTranscurridos = diferencia / diasMilli;
        diferencia = diferencia % diasMilli;

        long horasTranscurridos = diferencia / horasMilli;
        diferencia = diferencia % horasMilli;

        long minutosTranscurridos = diferencia / minsMilli;
        diferencia = diferencia % minsMilli;

        long segsTranscurridos = diferencia / segsMilli;

        return "diasTranscurridos: " + diasTranscurridos + " , horasTranscurridos: " + horasTranscurridos +
                " , minutosTranscurridos: " + minutosTranscurridos + " , segsTranscurridos: " + segsTranscurridos;


    }




}
