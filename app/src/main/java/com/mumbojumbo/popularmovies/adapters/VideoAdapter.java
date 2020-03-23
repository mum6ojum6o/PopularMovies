package com.mumbojumbo.popularmovies.adapters;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mumbojumbo.popularmovies.R;
import com.mumbojumbo.popularmovies.model.Videos;
import com.mumbojumbo.popularmovies.viewholders.VideosViewHolder;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideosViewHolder> {
    private List<Videos> mVideos;
    private IMovieVideoClickListener iMovieVideoClickListener;

    public VideoAdapter(List<Videos> mVideos, IMovieVideoClickListener iMovieVideoClickListener) {
        this.mVideos = mVideos;
        if(iMovieVideoClickListener!= null)
            this.iMovieVideoClickListener = iMovieVideoClickListener;
        else new Exception();
    }

    @NonNull
    @Override
    public VideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.video_list_items,parent,false);
        return new VideosViewHolder(view,iMovieVideoClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosViewHolder holder, int position) {
        Videos aVideo = mVideos.get(position);
        holder.bind(aVideo.getmName(),aVideo.getmType(),aVideo.getmKey());
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public interface IMovieVideoClickListener{
        public void onVideoItemClick(String videoName, String videoType, String key);
    }

    public List<Videos> getVideos(){
        return this.mVideos;
    }

    public void setmVideos(List<Videos> videos){
        this.mVideos=videos;
    }
}
