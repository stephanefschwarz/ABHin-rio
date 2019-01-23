package com.example.stephane.hinario_ab;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MusicDao {

    @Query("SELECT * FROM Musica ORDER BY id_number")
    LiveData<List<Music>> getAll();

    @Query("SELECT * FROM Musica WHERE music_name LIKE :music_name LIMIT 1")
    Music findByName(String music_name);

    @Insert
    void insertAll(Music musics);

    @Update
    void update(Music music);

    @Delete
    void delete(Music music);




}
