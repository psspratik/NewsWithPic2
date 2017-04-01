package com.example.android.newswithpic2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    ArrayList<NewsElements> arrayList;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        runOnUiThread(new Runnable(){
            @Override
            public void run(){
                new ReadJSON().execute("http://webhose.io/search?token=9c55cbb1-2f1c-4700-9c1e-67e685152506&format=json&q=Indian%20Startups%20(Startups%20OR%20Entrepreneur)%20language%3A(english)%20thread.country%3AIN");
            }
        });
    }

    class ReadJSON extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params){
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content){

            try {

                String image;
                JSONObject jsonObj = new JSONObject(content);
                // Getting JSON Array node
                //JSONObject responseObject = jsonObj.getJSONObject("response");
                JSONArray responseArray = jsonObj.getJSONArray("posts");
                // looping through All Contacts
                for (int i = 0; i < responseArray.length(); i++) {


                    JSONObject firstObject = responseArray.getJSONObject(i);
                    //JSONObject titleObject = firstObject.getJSONObject("title");
                    // Extract out the title, time, and tsunami values
                    JSONObject threadObject = firstObject.getJSONObject("thread");
                    //if(threadObject.has("main_image"))
                    image = threadObject.getString("main_image");

                    //else {
                    //  if()
                    //image = "https://us.123rf.com/450wm/uasumy/uasumy1504/uasumy150400022/38624301-green-letter-g-and-leaf-eco-technology-logo-mockup-ecology-poster.jpg?ver=6";
                    //}
                    String title = firstObject.getString("title");
                    String articleurl = threadObject.getString("url");
                    //if(image==null)
                    //String image = "https://us.123rf.com/450wm/uasumy/uasumy1504/uasumy150400022/38624301-green-letter-g-and-leaf-eco-technology-logo-mockup-ecology-poster.jpg?ver=6";
                    // tmp hash map for single contact
                    //HashMap<String , String> contact = new HashMap<>();

                    // adding each child node to HashMap key => value
                    //contact.put("pic", image);
                    //contact.put("name", title);

                    // adding contact to contact list
                    arrayList.add(new NewsElements(title,image,articleurl));
                }
            }catch (JSONException e){
                e.printStackTrace();

            }
            CustomListAdapter adapter = new CustomListAdapter(getApplicationContext(),R.layout.list, arrayList);
            lv.setAdapter(adapter);
        }
    }



    private static String readURL(String theUrl){
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line=bufferedReader.readLine())!=null){
                content.append(line+"\n");
            }
            bufferedReader.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return content.toString();
    }
}
