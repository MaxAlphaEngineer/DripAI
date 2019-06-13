package uz.inlab.dripai;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    private String upload_URL = "http://ubankmobile.uz/dripAI/user/login.php";
    JSONObject jsonObject;
    RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginBtn = findViewById(R.id.login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doLogin("test", "12345");

            }
        });
    }

    private void doLogin(String login, String password) {


        try {
            jsonObject = new JSONObject();
            jsonObject.put("login", login);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            Log.e("JSONObject Here", e.toString());
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, upload_URL, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            boolean status = jsonObject.getBoolean("status");
                            if (status) {
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(LoginActivity.this, "Wrong Login or password", Toast.LENGTH_SHORT).show();
                            }

                            Log.e("Status: ", String.valueOf(status));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Login response >>> ", jsonObject.toString());

                        rQueue.getCache().clear();
                        Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Login error >>> ", volleyError.toString());

            }
        });

        rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(jsonObjectRequest);

    }
}
