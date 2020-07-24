package com.mercadolibre.android.mlbusinesscomponents.components.row;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;

@Keep
public class FeatureFormatResponse implements Parcelable {

    public static final Parcelable.Creator<FeatureFormatResponse> CREATOR = new Parcelable.Creator<FeatureFormatResponse>() {
        @Override
        public FeatureFormatResponse createFromParcel(final Parcel in) {
            return new FeatureFormatResponse(in);
        }

        @Override
        public FeatureFormatResponse[] newArray(final int size) {
            return new FeatureFormatResponse[size];
        }
    };

    private final String backgroundColor;
    private final String textColor;

    /**
     * Constructor
     *
     * @param backgroundColor the feature background color.
     * @param textColor the feature text color.
     */
    public FeatureFormatResponse(final String backgroundColor, final String textColor) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    /**
     * Constructor
     *
     * @param in the input data.
     */
    protected FeatureFormatResponse(final Parcel in) {
        backgroundColor = in.readString();
        textColor = in.readString();
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(backgroundColor);
        dest.writeString(textColor);
    }

    //CPD-OFF
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FeatureFormatResponse)) {
            return false;
        }

        final FeatureFormatResponse that = (FeatureFormatResponse) o;

        if (getBackgroundColor() == null ? that.getBackgroundColor() != null
            : !getBackgroundColor().equals(that.getBackgroundColor())) {
            return false;
        }
        return getTextColor() == null ? that.getTextColor() == null : getTextColor().equals(that.getTextColor());
    }

    @SuppressWarnings("checkstyle:magicnumber")
    @Override
    public int hashCode() {
        int result = getBackgroundColor() == null ? 0 : getBackgroundColor().hashCode();
        result = 31 * result + (getTextColor() == null ? 0 : getTextColor().hashCode());
        return result;
    }
}
