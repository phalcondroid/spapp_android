package com.msg.spapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import Adapter.ServiceAdapter;
import Entities.Service;
import Entities.ServiceCategory;

public class ServiceCategoriesActivity extends AppCompatActivity {

    private ListView listServices;
    private List<Service> list;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        list = Service.listAll(Service.class);

        listServices = (ListView) findViewById(R.id.listServices);
        listServices.setAdapter(new ServiceAdapter(this, list));
        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ServiceCategory service = (ServiceCategory) adapterView.getItemAtPosition(i);
                bundle = new Bundle();
                bundle.putString("service_category_id", String.valueOf(service.getIdServiceCategory()));
                intent = new Intent(ServiceCategoriesActivity.this, BookingActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_service_categories, menu);
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
