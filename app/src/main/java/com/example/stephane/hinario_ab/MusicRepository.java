package com.example.stephane.hinario_ab;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MusicRepository {

    private MusicDao mMusicDao;
    private LiveData<List<Music>> allMusics;

    MusicRepository(Application application) {

        MusicRoomDatabase db = MusicRoomDatabase.getDatabase(application);
        mMusicDao = db.musicDao();
        allMusics = mMusicDao.getAll();
    }

    public LiveData<List<Music>> getAllMusics() {
        return allMusics;
    }

    public void insert (Music music) {
        new insertAsyncTask(mMusicDao).execute(music);

    }

    private static class insertAsyncTask extends AsyncTask<Music, Void, Void>{

        private MusicDao mAsyncTaskDao;

        insertAsyncTask(MusicDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Music... params) {

            mAsyncTaskDao.insertAll(params[0]);
            return null;

        }
    }
}
