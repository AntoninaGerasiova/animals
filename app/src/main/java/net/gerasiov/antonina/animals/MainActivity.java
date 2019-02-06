package net.gerasiov.antonina.animals;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Animals-debag";
    private static final String URI_PREF = "android.resource://net.gerasiov.antonina.animals/";
    MediaPlayer mPlayer;
    //array of pictures
    private ArrayList<Integer> mIdsAnimals =  new ArrayList<Integer> (
            Arrays.asList(R.drawable.cow_mid, R.drawable.owl_mid, R.drawable.pig_mid, R.drawable.sheep_mid,
                    R.drawable.bear_mid, R.drawable.cat_mid, R.drawable.dog_mid, R.drawable.monkey_mid));

    //array of sounds
    private ArrayList<Uri> mUriAnimals = new ArrayList <Uri> (
            Arrays.asList(Uri.parse(URI_PREF + R.raw.cow),Uri.parse(URI_PREF + R.raw.owl),
                    Uri.parse(URI_PREF + R.raw.pig),Uri.parse(URI_PREF + R.raw.sheep),
                    Uri.parse(URI_PREF + R.raw.bear),Uri.parse(URI_PREF + R.raw.cat),
                    Uri.parse(URI_PREF + R.raw.dog),Uri.parse(URI_PREF + R.raw.monkey))
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Begin of onCreate");
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        //initialise grid behavior
        initializeGrid();
    }

     @Override
	protected void onResume() {
         Log.d(TAG, "Begin of onResume");
        super.onResume();
        //player init
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

    }

    @Override
    protected void onPause(){
        //player release
        Log.d(TAG, "Begin of onPause");
        //mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
        super.onPause();
    }

     private void initializeGrid() {
         //grid init
         GridView gridview = (GridView) findViewById(R.id.gridview);
         gridview.setAdapter(new ImageAdapter(this, mIdsAnimals));
         //handle pictures' clicks
         gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                try {
                    mPlayer.reset();
                    mPlayer.setDataSource(getApplicationContext(),mUriAnimals.get(position));
                    mPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mPlayer.start();
            }
        });
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
                        .setTitle(R.string.dialog_title)
                        .setMessage(R.string.dialog_text)
                        .setIcon(R.mipmap.ic_launcher)
                        .show();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
