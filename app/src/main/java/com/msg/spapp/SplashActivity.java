package com.msg.spapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Entities.Google;
import Entities.PersonalData;
import Libraries.CustomRequest;
import Entities.Service;
import Libraries.GoogleCloudMsj;
import Libraries.Internet;
import interfaces.ISession;

public class SplashActivity extends FragmentActivity {

    private GoogleCloudMsj gcmCustom;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        if (Internet.validar(this)) {
            this.gcmCustom = new GoogleCloudMsj(this);
            this.gcmCustom.checkPlayServices();
            this.gcmCustom.registerInBackground(new ISession() {
                @Override
                public void onPreExecute() {

                }

                @Override
                public String doInBackground() {
                    return null;
                }

                @Override
                public void onPostExecute(String result) {

                   getDataConfig(result);

                }
            });
        }
    }

    /**
     *
     * @param uuid
     */
    public void getDataConfig(final String uuid) {

        Log.e("uuid", uuid);

        Service.deleteAll(Service.class);

        Map<String, String> params =
                new HashMap<String, String>();

        params.put("key", getString(R.string.spapp_key));
        params.put("platform", "android");

        CustomRequest request =
                new CustomRequest(Request.Method.POST, CustomRequest.getURL(this, "api", "general"), params,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                try {

                                    JSONArray data = response.getJSONArray("data");
                                    for (int i = 0; i < data.length(); i++) {

                                        JSONObject item = data.optJSONObject(i);

                                        Service service = new Service();
                                        service.setId(Long.parseLong(item.getString("id")));
                                        service.setName(item.getString("name"));
                                        service.setDescription(item.getString("description"));
                                        service.setImage(item.getString("image"));
                                        service.save();

                                    }

                                    Google config = new Google();
                                    config.setUuid(uuid);
                                    config.save();

                                    try {

                                        PersonalData.findById(PersonalData.class, 1).getFirstData();
                                        intent = new Intent(SplashActivity.this, ServicesActivity.class);

                                    } catch (NullPointerException e) {
                                        intent = new Intent(SplashActivity.this, PersonalInfoActivity.class);
                                    }

                                    startActivity(intent);

                                } catch (JSONException e) {

                                    Toast.makeText(SplashActivity.this, getString(R.string.splash_error), Toast.LENGTH_SHORT)
                                            .show();

                                }
                            }
                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {

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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
