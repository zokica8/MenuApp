package app.menu.menutechnologiestestapp.ui.usecases.venue_details;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import app.menu.menutechnologiestestapp.R;
import app.menu.menutechnologiestestapp.databinding.FragmentVenueDetailsBinding;
import app.menu.menutechnologiestestapp.domain.model.Venue;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VenueDetailsFragment extends Fragment {

    private Venue venue;
    private FragmentVenueDetailsBinding binding;

    public VenueDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            venue = getArguments().getParcelable("venue");
        }
        binding = FragmentVenueDetailsBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        setupTypeface();
        binding.nameTV.setText(venue.getName());
        if (venue.getWelcomeMessage() != null && !venue.getWelcomeMessage().isEmpty()) {
            binding.welcomeMessageTV.setText(venue.getWelcomeMessage());
        } else {
            binding.welcomeMessageTV.setVisibility(View.GONE);
        }
        if (venue.getDescription() != null && !venue.getWelcomeMessage().isEmpty()) {
            binding.introTV.setText(venue.getDescription());
        } else {
            binding.introTV.setVisibility(View.GONE);
        }
        binding.openClosedTV.setText(venue.isOpen() ? getResources().getString(R.string.open) : getResources().getString(R.string.closed));
        if(venue.getMediumThumbnailImage() != null && !venue.getMediumThumbnailImage().isEmpty()) {
            Picasso.get().load(venue.getMediumThumbnailImage()).into(binding.downloadedImageIV);
        }
        else {
            binding.downloadedImageIV.setImageResource(R.drawable.image_not_available);
        }
    }

    private void setupTypeface() {
        Typeface typeface = Typeface.createFromAsset(requireContext().getAssets(), "fonts/Roboto-Light.ttf");
        binding.nameTV.setTypeface(typeface);
        binding.welcomeMessageTV.setTypeface(typeface);
        binding.introTV.setTypeface(typeface);
        binding.openClosedTV.setTypeface(typeface);
    }
}