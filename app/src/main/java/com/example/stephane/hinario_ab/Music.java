package com.example.stephane.hinario_ab;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.w3c.dom.Text;

@Entity(tableName = "Musica")
public class Music {

    public int getId_number() {
        return id_number;
    }

    public String getMusic_name() {
        return music_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getMusic_content() {
        return music_content;
    }

    public void setId_number(@NonNull int id_number) {
        this.id_number = id_number;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setMusic_content(String music_content) {
        this.music_content = music_content;
    }

    // Class variable

    @PrimaryKey
    @ColumnInfo(name = "id_number")
    private int id_number;

    @ColumnInfo(name = "music_name")
    private String music_name;

    @ColumnInfo(name = "author_name")
    private String author_name;

    @ColumnInfo(name = "music_content")
    private String music_content;


    public Music(){}

    public Music(int id_number, String author_name, String music_name, String music_content){

        this.id_number = id_number;
        this.author_name = author_name;
        this.music_name = music_name;
        this.music_content = music_content;
    }
}