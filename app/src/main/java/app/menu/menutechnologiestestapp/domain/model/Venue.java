package app.menu.menutechnologiestestapp.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Venue implements Parcelable {

    private String name;
    private String description;
    private boolean isOpen;
    private String welcomeMessage;
    private String mediumThumbnailImage;

    // parcelable part to send this object to venue details screen
    protected Venue(Parcel in) {
        name = in.readString();
        description = in.readString();
        isOpen = in.readByte() != 0;
        welcomeMessage = in.readString();
        mediumThumbnailImage = in.readString();
    }

    public static final Creator<Venue> CREATOR = new Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };

    // constructors
    public Venue() {
    }

    public Venue(String name, String description, boolean isOpen, String welcomeMessage) {
        this.name = name;
        this.description = description;
        this.isOpen = isOpen;
        this.welcomeMessage = welcomeMessage;
    }

    public Venue(String name, String description, boolean isOpen, String welcomeMessage, String mediumThumbnailImage) {
        this.name = name;
        this.description = description;
        this.isOpen = isOpen;
        this.welcomeMessage = welcomeMessage;
        this.mediumThumbnailImage = mediumThumbnailImage;
    }

    // getters and setters
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getMediumThumbnailImage() {
        return mediumThumbnailImage;
    }

    public void setMediumThumbnailImage(String mediumThumbnailImage) {
        this.mediumThumbnailImage = mediumThumbnailImage;
    }

    // parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeByte((byte) (isOpen ? 1 : 0));
        parcel.writeString(welcomeMessage);
        parcel.writeString(mediumThumbnailImage);
    }
}
