package com.example.scrollable19;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class readtextfile extends AsyncTask<String, Integer, String> {

    private TextView textViewToSet;
    String texturl;
    public readtextfile(TextView descriptionTextView, String texturl){
        this.textViewToSet = descriptionTextView;
        this.texturl=texturl;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        try {
            URL url = new URL(texturl);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = null;

            while ((line = in.readLine()) != null) {
                //get lines
                result+=line;
            }
            in.close();


        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    protected void onProgressUpdate() {
        //called when the background task makes any progress
    }

    protected void onPreExecute() {
        //called before doInBackground() is started
    }

    @Override
    protected void onPostExecute(String result) {
        this.textViewToSet.setText(result);
    }
}
