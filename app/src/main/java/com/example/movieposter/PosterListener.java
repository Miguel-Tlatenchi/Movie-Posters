package com.example.movieposter;

/**
 * Listener interface for receiving poster action events
 */
public interface PosterListener {

    /**
     * Invoked when a poster action occurs, such as selection or deselection.
     * @param isSelected boolean indicating if the poster is selected(true) or deselected(false).
     */
    void onPosterAction(boolean isSelected);
}
