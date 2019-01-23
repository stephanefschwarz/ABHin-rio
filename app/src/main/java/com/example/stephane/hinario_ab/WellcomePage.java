package com.example.stephane.hinario_ab;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WellcomePage extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private MusicViewModel mMusicViewModel;
    private MusicListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new MusicListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMusicViewModel = ViewModelProviders.of(this).get(MusicViewModel.class);

        mMusicViewModel.getAllMusics().observe(this, new Observer<List<Music>>(){

            @Override
            public void onChanged(@Nullable final List<Music> musics){
                int t = musics.size();
                //Log.d("DATABASE TEST", "--->> " + musics.get(0).getId_number());

                adapter.setMusics(musics);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wellcome_page, menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String input = newText.toLowerCase();
        List<Music> newList = new ArrayList<>();

        List<Music> tmp = mMusicViewModel.getAllMusics().getValue();

        for (Music music : tmp){

            if ( (Integer.toString(music.getId_number()).contains(input) ) ||
                 (music.getMusic_name().toLowerCase().contains(input)) ){
                newList.add(music);
            }
        }
        adapter.updateList(newList);
        return true;
    }
}
