package app.menu.menutechnologiestestapp.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VenuesDto {

    @SerializedName("venues")
    @Expose
    private List<VenueDto> venues;

    public List<VenueDto> getVenues() {
        return venues;
    }

    public void setVenues(List<VenueDto> venues) {
        this.venues = venues;
    }
}
