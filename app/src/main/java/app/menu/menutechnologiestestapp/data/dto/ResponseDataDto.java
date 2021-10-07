package app.menu.menutechnologiestestapp.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDataDto {

    @SerializedName("data")
    @Expose
    private VenuesDto venuesDto;

    public VenuesDto getVenuesDto() {
        return venuesDto;
    }

    public void setVenuesDto(VenuesDto venuesDto) {
        this.venuesDto = venuesDto;
    }
}
