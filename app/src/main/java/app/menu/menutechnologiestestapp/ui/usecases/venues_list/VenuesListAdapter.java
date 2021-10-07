package app.menu.menutechnologiestestapp.ui.usecases.venues_list;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.menu.menutechnologiestestapp.R;
import app.menu.menutechnologiestestapp.domain.model.Venue;

public class VenuesListAdapter extends RecyclerView.Adapter<VenuesListAdapter.VenuesListViewHolder> {

    private final List<Venue> venues;
    private Typeface typeface;
    private OnVenueClickListener onVenueClickListener;

    public VenuesListAdapter(List<Venue> venues) {
        this.venues = venues;
    }

    @NonNull
    @Override
    public VenuesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_item, parent, false);
        typeface = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/Roboto-Light.ttf");
        return new VenuesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenuesListViewHolder holder, int position) {
        Venue venue = venues.get(position);
        holder.venueNameTV.setTypeface(typeface);
        holder.venueNameTV.setText(venue.getName());
        holder.venueNameTV.setOnClickListener(view -> {
            if(onVenueClickListener != null) {
                onVenueClickListener.onVenueClick(venue);
            }
        });
    }

    @Override
    public int getItemCount() {
        return venues != null ? venues.size() : 0;
    }

    static class VenuesListViewHolder extends RecyclerView.ViewHolder {

        private final TextView venueNameTV;

        public VenuesListViewHolder(@NonNull View itemView) {
            super(itemView);

            venueNameTV = itemView.findViewById(R.id.venue_name_TV);
        }
    }

    public interface OnVenueClickListener {
        void onVenueClick(Venue venue);
    }

    public void setOnVenueClickListener(OnVenueClickListener onVenueClickListener) {
        this.onVenueClickListener = onVenueClickListener;
    }
}
