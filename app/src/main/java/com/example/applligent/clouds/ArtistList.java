package com.example.applligent.clouds;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArtistList extends ArrayAdapter<Artist> {
   private Activity context;
   private List<Artist> artistList;
   public ArtistList(Activity context, List<Artist> artistList){
       super(context, R.layout.list_view,artistList);
       this.context=context;
       this.artistList=artistList;

   }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem =inflater.inflate(R.layout.list_view,null,true);
        TextView name=(TextView)listViewItem.findViewById(R.id.name);
        TextView genre=(TextView)listViewItem.findViewById(R.id.genre);
        Artist artist = artistList.get(position);
        name.setText(artist.artistName);
        genre.setText(artist.artistGenre);
        return listViewItem;

    }
}
