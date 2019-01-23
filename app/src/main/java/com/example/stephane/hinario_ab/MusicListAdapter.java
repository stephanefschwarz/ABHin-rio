package com.example.stephane.hinario_ab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicViewHolder> {

    class MusicViewHolder extends RecyclerView.ViewHolder {

        private final TextView music_name_textview;
        private final TextView author_name_textview;
        private final TextView music_number_textview;

        private final RelativeLayout item_list;

        private MusicViewHolder(View itemView){
            super(itemView);

            music_number_textview = (TextView) itemView.findViewById(R.id.number_icon);
            music_name_textview = (TextView) itemView.findViewById(R.id.music_name);
            author_name_textview = (TextView) itemView.findViewById(R.id.author_name);

            item_list = (RelativeLayout) itemView.findViewById(R.id.linear_layout);
        }
    }

    private final LayoutInflater mInflater;
    private List<Music> mMusics;

    MusicListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new MusicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MusicViewHolder holder, final int position) {

        final Music current;

        if (mMusics != null){

            current = mMusics.get(position);

            holder.music_number_textview.setText(Integer.toString(current.getId_number()));
            holder.music_name_textview.setText(current.getMusic_name());
            holder.author_name_textview.setText(current.getAuthor_name());


        }else{
            holder.music_name_textview.setText("No musics");
            current = null;
        }

        holder.item_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MusicShowLetter.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("MUSIC_NAME", current.getMusic_name());
                intent.putExtra("AUTHOR_NAME", current.getAuthor_name());
                intent.putExtra("MUSIC_CONTENT", current.getMusic_content());

                v.getContext().startActivity(intent);

            }
        });

    }

    void setMusics(List<Music> musics){
        mMusics = musics;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mMusics != null){
            return mMusics.size();
        }else{
            return 0;
        }
    }

    public void updateList(List<Music> newMusicList){

        mMusics = new ArrayList<>();
        mMusics.addAll(newMusicList);
        notifyDataSetChanged();
    }

}
