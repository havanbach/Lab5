package com.example.myapplication;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class VolleyFn2 {
    String strJSON = "";
    public void getAlldataFromAPI(Context context, TextView textView){
        //1 tạo request
        RequestQueue queue = Volley.newRequestQueue(context);
        //2 url
        String url = "http://12.34.56.78:80/phpmyadmin/index.php?route=/sql&pos=0&db=lab5&table=tbluser";
        //3 Gọi request
        //Mảng của các đối tượng => JsonArrayRequest(url,thanhcong,thatbai)
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //đọc các dối tượng con của mảng
                for (int i = 0 ; i<response.length();i++){
                    try {
                        JSONObject person = response.getJSONObject(i);
                        String id = person.getString("id");
                        String name = person.getString("name");
                        String phone = String.valueOf(person.getInt("phone"));
                        String gender = person.getString("gender");
                        strJSON+= "id: "+id+"\n";
                        strJSON+= "name: "+name+"\n";
                        strJSON+= "phone: "+phone+"\n";
                        strJSON+= "gender: "+gender+"\n";

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                textView.setText(strJSON);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.getMessage());
            }
        });
        queue.add(request);//thực thi request
    }
}
