package denv.graphics.textoverimage.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Den on 2/22/2017.
 */
public enum ImageLayerType {
    @SerializedName("text")
    TEXT,
    @SerializedName("image")
    IMAGE,
    @SerializedName("color-fill")
    COLOR_FILL
}
