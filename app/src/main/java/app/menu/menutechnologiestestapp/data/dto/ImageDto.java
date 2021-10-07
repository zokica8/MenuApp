package app.menu.menutechnologiestestapp.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageDto {

    @SerializedName("thumbnail_small")
    @Expose
    private String thumbnailSmallUrl;
    @SerializedName("thumbnail_medium")
    @Expose
    private String thumbnailMediumUrl;
    @SerializedName("fullsize")
    @Expose
    private String fullSizeUrl;

    public String getThumbnailSmallUrl() {
        return thumbnailSmallUrl;
    }

    public void setThumbnailSmallUrl(String thumbnailSmallUrl) {
        this.thumbnailSmallUrl = thumbnailSmallUrl;
    }

    public String getThumbnailMediumUrl() {
        return thumbnailMediumUrl;
    }

    public void setThumbnailMediumUrl(String thumbnailMediumUrl) {
        this.thumbnailMediumUrl = thumbnailMediumUrl;
    }

    public String getFullSizeUrl() {
        return fullSizeUrl;
    }

    public void setFullSizeUrl(String fullSizeUrl) {
        this.fullSizeUrl = fullSizeUrl;
    }
}
