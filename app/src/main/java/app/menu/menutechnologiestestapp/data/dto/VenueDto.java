package app.menu.menutechnologiestestapp.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueDto {

    @SerializedName("distance")
    @Expose
    private double distance;
    @SerializedName("distance_in_miles")
    @Expose
    private double distanceInMiles;
    @SerializedName("venue")
    @Expose
    private VenueObjectDto venueObjectDto;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistanceInMiles() {
        return distanceInMiles;
    }

    public void setDistanceInMiles(double distanceInMiles) {
        this.distanceInMiles = distanceInMiles;
    }

    public VenueObjectDto getVenueObjectDto() {
        return venueObjectDto;
    }

    public void setVenueObjectDto(VenueObjectDto venueObjectDto) {
        this.venueObjectDto = venueObjectDto;
    }
}
