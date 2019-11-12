package com.example.scrollable19;

/* Learning objectives
* use-permissions in manifests
* AsyncTask to download text file from the Internet
* options menu
* context menu
* floating action bar
* log
* Text to Speech
* */


import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ScrollingActivity extends AppCompatActivity {

    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        final TextView t = (TextView) findViewById(R.id.article);
        registerForContextMenu(t);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = t1.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Text to Speech if shorter than 500!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                String toSpeak = t.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                if (toSpeak.length()>500)
                    t1.speak(toSpeak.substring(0,500), TextToSpeech.QUEUE_FLUSH, null);
                else
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.shorterone) {

            TextView t = (TextView) findViewById(R.id.article);
            t.setText("something new");

            return true;



        }

        if (id == R.id.fromweb) {

            TextView t = (TextView) findViewById(R.id.article);
            new readtextfile(t,"http://textfiles.com/food/batrbred.txt" ).execute();

            return true;



        }

        return super.onOptionsItemSelected(item);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
        menu.setHeaderTitle("Select The Action");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.A){
            Toast.makeText(getApplicationContext(),"You are great!",Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.B){
            Toast.makeText(getApplicationContext(),"You are cool!",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

}
