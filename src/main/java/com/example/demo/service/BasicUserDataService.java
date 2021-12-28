package com.example.demo.service;

import com.example.demo.model.BasicUserDataInfo.BasicUserData;
import com.example.demo.model.BasicUserDataInfo.PhysicalActivity;
import com.example.demo.model.BasicUserDataInfo.Sex;

public interface BasicUserDataService {

    BasicUserData create(BasicUserData basicUserData);

    BasicUserData addBasicUserData(String login, int age, Sex sex, PhysicalActivity physicalActivity, double height);

    void updateBasicUserDataPhysicalActivity(String login, PhysicalActivity physicalActivity);

    void removeBasicUserData(Long id);

    Iterable<BasicUserData> getIAllUserData();

    Iterable<BasicUserData> getUserDataByLogin(String login);


}

//package com.example.healthapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.gson.JsonArray;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    Button btn_cityID, btn_getWeatherByID, btn_getWeatherByName;
//    EditText et_dataInput;
//    ListView lv_weatherReport;
//    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btn_cityID = findViewById(R.id.btn_getCityID);
//        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityID);
//        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);
//
//        et_dataInput = findViewById(R.id.et_dataInput);
//        lv_weatherReport = findViewById(R.id.lv_weatherReports);
//        String url = "http://192.168.8.102:8080/api/bodyDimensions";
//        btn_cityID.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
////        String url = "https://www.metaweather.com/api/location/search/?query=london";
////              String url = "http://192.168.8.102:8080/api/bodyDimensions1/miccid";
//
//
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//
//                            @Override
//                            public void onResponse(String response) {
//                                System.out.println("MZ " + response);
//                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        System.out.println("MZ+ " + error);
//                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                queue.add(stringRequest);
//
//            }
//        });
//
//        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//
//
////                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest (Request.Method.GET, url, null,
////                        new Response.Listener<JsonArrayRequest >() {
//                JsonArrayRequest searchMsg= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
////                        Log.d(url, response.toString());
////                        try {
////                            String id = response.get("bodyDimensionsId").toString();
////                            String shoulders = response.get("shoulders").toString();
////                            System.out.println("MZ " + id +"   "+shoulders);
////                        }catch (JSONException e) {
////                            e.printStackTrace();
////                        }}
//                        System.out.println("MZ ");
//                        List<BodyDimensions> bodyDimensionsList= new ArrayList<>();
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//
//                                JSONObject obj = response.getJSONObject(i);
//                                Message msg = new Message();
//bodyDimensionsList.add(obj);
//                                System.out.println("MZ "+obj.getString("shoulders"));
////                                msg.(obj.getString("msgThread"));
////                                msg.setUserName(obj.getString("Username"));
////                                msg.setDate(obj.getString("msgDate"));
//
//                                // adding movie to movies array
////                                MessageList.add(msg);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }}
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                System.out.println("MZ+ " + error);
//                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            queue.add(searchMsg);
//                Toast.makeText(MainActivity.this, "You clicker me 22", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "You clicker me " + et_dataInput.getText().toString(), Toast.LENGTH_SHORT).show();
//                sendMessage(view);
//            }
//        });
//
//    }
//
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, BasicUserDataActivity.class);
//        startActivity(intent);
//    }
//}
