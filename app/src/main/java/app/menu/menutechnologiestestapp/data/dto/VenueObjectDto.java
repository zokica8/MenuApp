package app.menu.menutechnologiestestapp.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueObjectDto {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("welcome_message")
    @Expose
    private String welcome_message;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("is_open")
    @Expose
    private boolean isOpen;
    @SerializedName("will_open")
    @Expose
    private boolean willOpen;
    @SerializedName("has_discounts")
    @Expose
    private boolean hasDiscounts;
    @SerializedName("has_loyalty")
    @Expose
    private boolean hasLoyalty;
    @SerializedName("has_promotions")
    @Expose
    private boolean hasPromotions;
    @SerializedName("image")
    @Expose
    private ImageDto imageDto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWelcome_message() {
        return welcome_message;
    }

    public void setWelcome_message(String welcome_message) {
        this.welcome_message = welcome_message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isWillOpen() {
        return willOpen;
    }

    public void setWillOpen(boolean willOpen) {
        this.willOpen = willOpen;
    }

    public boolean isHasDiscounts() {
        return hasDiscounts;
    }

    public void setHasDiscounts(boolean hasDiscounts) {
        this.hasDiscounts = hasDiscounts;
    }

    public boolean isHasLoyalty() {
        return hasLoyalty;
    }

    public void setHasLoyalty(boolean hasLoyalty) {
        this.hasLoyalty = hasLoyalty;
    }

    public boolean isHasPromotions() {
        return hasPromotions;
    }

    public void setHasPromotions(boolean hasPromotions) {
        this.hasPromotions = hasPromotions;
    }

    public ImageDto getImageDto() {
        return imageDto;
    }

    public void setImageDto(ImageDto imageDto) {
        this.imageDto = imageDto;
    }
}
