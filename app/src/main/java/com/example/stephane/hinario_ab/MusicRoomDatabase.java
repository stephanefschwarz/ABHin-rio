package com.example.stephane.hinario_ab;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Database(entities = {Music.class}, version = 2)
public abstract class MusicRoomDatabase extends RoomDatabase {

    public abstract MusicDao musicDao();

    private static MusicRoomDatabase INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    static MusicRoomDatabase getDatabase(final Context context){

        if (INSTANCE == null) {

            synchronized (MusicRoomDatabase.class) {
                if (INSTANCE == null){

                    copyDatabase(context, "musicas.db");

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                                    MusicRoomDatabase.class, "musicas.db")
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MusicDao mDao;

        PopulateDbAsync(MusicRoomDatabase db){
            mDao = db.musicDao();
        }

        protected Void doInBackground(final Void... params){
            return null;
        }
    }

    private static void copyDatabase(Context context, String dbName){
        final File dbPath = context.getDatabasePath(dbName);

        if (dbPath.exists()) {
            //Log.d("Existe", " :( ");
            return;
        }

        //Log.d("Não existe", "É isso aí");
        dbPath.getParentFile().mkdirs();
        try {

            final InputStream inputStream = context.getAssets().open("databases/" + dbName);

            final OutputStream outputStream = new FileOutputStream(dbPath);

            byte[] buffer = new byte[8192];
            int length;

            while ((length = inputStream.read(buffer, 0, 8192)) > 0){
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

