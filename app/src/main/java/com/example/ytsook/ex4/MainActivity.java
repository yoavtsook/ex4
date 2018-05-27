package com.example.ytsook.ex4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
public class MainActivity extends AppCompatActivity implements Callback<JsonElement> {
    RecyclerView rv;
    MainActivity myActivity;
    ImageAdapter imageAdapter;
    ArrayList<String> myUrlList;
    Button btn;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myUrlList = new ArrayList<>();
        imageAdapter = new ImageAdapter(myUrlList);
        myActivity = this;
        rv = findViewById(R.id.recycel);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(imageAdapter);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestAdapter r = new RestAdapter.Builder().setEndpoint("https://api.imgur.com/3/").build();
                service s = r.create(service.class);
                s.getGallery("auPprBX", myActivity);
            }
        });
    }

    @Override
    public void success(JsonElement jsonElement, Response response) {
        JsonObject jo = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jo.get("data").getAsJsonObject().get("images").getAsJsonArray();

        myUrlList.clear();
        int position = 0;
        for (JsonElement element : jsonArray)
        {
            myUrlList.add(element.getAsJsonObject().get("link").getAsString());
            // Log.e("URL", myUrlList.get(position));
            ++position;
        }
        imageAdapter.setmDataSource(myUrlList);
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }
}
