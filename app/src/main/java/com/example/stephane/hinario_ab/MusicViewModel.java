package com.example.stephane.hinario_ab;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class MusicViewModel extends AndroidViewModel {

    private MusicRepository mRepository;

    public LiveData<List<Music>> getAllMusics() {
        return mAllMusics;
    }

    private LiveData<List<Music>> mAllMusics;

    public MusicViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MusicRepository(application);
        mAllMusics = mRepository.getAllMusics();
    }

    public void insert(Music music) {
        mRepository.insert(music);
    }
}
