package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.hybrid_carousel.model.view_more;

import androidx.annotation.Keep;
import java.io.Serializable;

@Keep
public class ViewMoreMainTitleFormat implements Serializable {

    private final String textColor;
    private final String backgroundColor;

    public ViewMoreMainTitleFormat(final String textColor, final String backgroundColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isValid() {
        return textColor != null && !textColor.isEmpty() && backgroundColor != null && !backgroundColor.isEmpty();
    }
}
