package com.example.lyricsfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView mLyrics;
    EditText mArtist,mSong;
    Button mGetLyrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLyrics=findViewById(R.id.Lyrics);
        mArtist=findViewById(R.id.Artis);
        mSong=findViewById(R.id.song);
        mGetLyrics=findViewById(R.id.GetLyrics);
        mGetLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://api.lyrics.ovh/v1/"+mArtist.getText().toString()+"/"+mSong.getText().toString();
                RequestQueue requests=Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mLyrics.setText(response.toString(Integer.parseInt("Lyrics")));
                        //  Toast.makeText(MainActivity.this,"Pass",Toast.LENGTH_SHORT).show();;
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,"Lyrics not found ",Toast.LENGTH_LONG).show();;
                            }
                        });


                requests.add(request);
            }
        });

    }
}
