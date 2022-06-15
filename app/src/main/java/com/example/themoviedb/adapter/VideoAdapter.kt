package com.example.themoviedb.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.model.MovieVideo
import com.example.themoviedb.view.DetailActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer

class VideoAdapter(private var listVideo: List<MovieVideo>, private var activity: Activity)
    :RecyclerView.Adapter<VideoAdapter.VideoViewHolder>()
{
    class VideoViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_video)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoAdapter.VideoViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: VideoAdapter.VideoViewHolder, position: Int) {
        val apiKey="AIzaSyBjD4_paeMArvh8245A-pLd1VbQr6gsea4"
        with(holder){
            with(listVideo[position]) {
                button.text = "Video ${position + 1}"
                button.setOnClickListener {
                    var context = view.context
                    var playVideo = YouTubeStandalonePlayer.createVideoIntent(activity, apiKey, key)
                    context.startActivity(playVideo)
                }
            }
        }
    }

    override fun getItemCount() = listVideo.size
}