package com.msg.spapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Entities.Google;
import Entities.PersonalData;
import Entities.User;
import Libraries.CustomRequest;

public class PersonalInfoActivity extends AppCompatActivity
        implements View.OnClickListener {

    private EditText
                editName,
                editLast,
                editEmail,
                editPhone;

    Intent intent;
    ProgressBar pg;
    Bundle bundle;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        url = CustomRequest.getURL(this, "api", "register_user_information");

        bundle = getIntent().getExtras();

        editName = (EditText) findViewById(R.id.edit_name);
        editLast = (EditText) findViewById(R.id.edit_last);
        editEmail = (EditText) findViewById(R.id.edit_email);
        editPhone = (EditText) findViewById(R.id.edit_phone);

        pg = (ProgressBar) findViewById(R.id.progressBar);
        pg.setVisibility(View.GONE);

        findViewById(R.id.btn_facebook)
                .setOnClickListener(this);

        findViewById(R.id.btn_save)
                .setOnClickListener(this);

        if (bundle.containsKey("edit")) {
            if (bundle.getString("edit").equals("true")) {

                url = CustomRequest.getURL(this, "api", "edit_user_information");
                User user = User.findById(User.class, 1);
                editName.setText(user.getName());
                editLast.setText(user.getLastName());
                editEmail.setText(user.getEmail());
                editPhone.setText(user.getPhone());

            }
        }

    }

    /**
     *
     * @param v
     */
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_save:

                    pg.setVisibility(View.VISIBLE);

                    boolean flag = true;

                    if (editName.getText().toString().equals("")) {
                        editName.setError(getString(R.string.field_error));
                        flag = false;
                    }

                    if (editLast.getText().toString().equals("")) {
                        editLast.setError(getString(R.string.field_error));
                        flag = false;
                    }

                    if (editEmail.getText().toString().equals("")) {
                        editEmail.setError(getString(R.string.field_error));
                        flag = false;
                    }

                    if (editPhone.getText().toString().equals("")) {
                        editPhone.setError(getString(R.string.field_error));
                        flag = false;
                    }

                    if (flag) {

                        Map<String, String> params =
                                new HashMap<String, String>();

                        params.put("key", getString(R.string.spapp_key));
                        params.put("name", editName.getText().toString());
                        params.put("last_name", editLast.getText().toString());
                        params.put("email", editEmail.getText().toString());
                        params.put("phone", editPhone.getText().toString());
                        params.put("image", "");
                        params.put("uuid", Google.findById(Google.class, 1).getUuid());
                        params.put("platform", "android");

                        CustomRequest request =
                                new CustomRequest(Request.Method.POST, url, params,
                                        new Response.Listener<JSONObject>() {

                                            @Override
                                            public void onResponse(JSONObject response) {

                                                pg.setVisibility(View.GONE);

                                                try {

                                                    JSONObject data = response.getJSONObject("data");

                                                    User user = new User();

                                                    if (bundle.containsKey("edit")) {
                                                        if (bundle.getString("edit").equals("true")) {
                                                            user = User.findById(User.class, 1);
                                                        }
                                                    }

                                                    user.setUserId(data.getString("user_id"));
                                                    user.setName(editName.getText().toString());
                                                    user.setLastName(editLast.getText().toString());
                                                    user.setEmail(editEmail.getText().toString());
                                                    user.setPhone(editPhone.getText().toString());
                                                    user.save();

                                                    Toast.makeText(PersonalInfoActivity.this, getString(R.string.title_activity_our_personal_register),Toast.LENGTH_SHORT)
                                                            .show();

                                                    intent = new Intent(PersonalInfoActivity.this, ServicesActivity.class);
                                                    startActivity(intent);

                                                } catch (JSONException e) {

                                                    Toast.makeText(PersonalInfoActivity.this, getString(R.string.splash_error), Toast.LENGTH_SHORT)
                                                            .show();

                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {

                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                                pg.setVisibility(View.GONE);

                                                try {
                                                    Log.e("error", error.getMessage());
                                                } catch (NullPointerException e) {
                                                    Log.e("error", "No se sabe cual es el error");
                                                }
                                            }
                                        }
                                );

                        CustomRequest.MySingleton.getInstance(this)
                                .addToRequestQueue(request);

                        PersonalData config = new PersonalData();
                        config.setFirstData(true);
                        config.save();
                    } else {
                        pg.setVisibility(View.GONE);
                    }

                break;
            case R.id.btn_facebook:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_register, menu);
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
