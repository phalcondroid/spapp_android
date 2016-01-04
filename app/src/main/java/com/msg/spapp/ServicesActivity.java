package com.msg.spapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.ServiceAdapter;
import Models.Service;

public class ServicesActivity extends AppCompatActivity {

    private ListView listServices;
    private ArrayList<Service> listItems =
            new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Service service = new Service();
        service.setName("Entrenamiento GYM");
        service.setDescription("Biceps, triceps, cardio, pesas, trx");

        Service service2 = new Service();
        service2.setName("Zumba");
        service2.setDescription("Conviertete en el mejor bailarin de zumba");

        Service service3 = new Service();
        service3.setName("Fighting combat");
        service3.setDescription("Calses de cardio en fighting");

        listItems.add(service);
        listItems.add(service2);
        listItems.add(service3);

        listServices = (ListView) findViewById(R.id.listServices);
        listServices.setAdapter(new ServiceAdapter(this, listItems));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
