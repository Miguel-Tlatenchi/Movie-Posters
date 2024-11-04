package com.example.movieposter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder>{
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));

    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    private List<Poster> posterList;

    /**
     * MAkes a PosterAdapter with a list of posters along with a listener
     * @param posterList List of Poster objects to display
     * @param postersListener Listener to handle poster selection action
     */
    public PosterAdapter(List<Poster> posterList, PosterListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }
    private PosterListener postersListener;

    /**
     * gets list of selected posters
     * @return list of selected posters
     */
    public List<Poster> getSelectedPosters(){
        List<Poster> selectedPosters = new ArrayList<>();
        for(Poster poster : this.posterList) {
            if(poster.isSelected){
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }


    /**
     * ViewHolder class that represents each poster in teh RecyclerView
     */
    class PosterViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutPosters;
        View viewBackground;

        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        /**
         * Creates PosterViewHolder and initializes its views
         * @param itemView view of the poster item
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPosters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * Binds poster data to the views and sets up click listeners to handle
         * selection and deselection of posters
         * @param poster Poster object to bind to view holder
         */
        void bindPosters(final Poster poster) {
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);

            if(poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(poster.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if(getSelectedPosters().isEmpty()){
                            postersListener.onPosterAction(false);
                        }
                    }else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);
                    }
                }
            });
        }

    }
}
