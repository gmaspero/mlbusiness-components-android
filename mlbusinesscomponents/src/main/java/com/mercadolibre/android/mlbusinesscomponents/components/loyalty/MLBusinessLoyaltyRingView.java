package com.mercadolibre.android.mlbusinesscomponents.components.loyalty;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import java.lang.ref.WeakReference;

public class MLBusinessLoyaltyRingView extends ConstraintLayout {

    public interface OnClickLoyaltyRing {
        void onClickLoyaltyButton(@NonNull final String deepLink);
    }

    private final LoyaltyProgress progress;
    private final TextView loyaltyTitle;
    private final TextView loyaltySubtitle;
    private final TextView loyaltyButton;
    private WeakReference<OnClickLoyaltyRing> onClickLoyaltyRing;
    private MLBusinessLoyaltyRingData businessLoyaltyRingData;

    public MLBusinessLoyaltyRingView(final Context context) {
        this(context, null);
    }

    public MLBusinessLoyaltyRingView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MLBusinessLoyaltyRingView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.ml_view_business_loyalty_ring, this);

        progress = findViewById(R.id.loyaltyRing);
        loyaltyTitle = findViewById(R.id.loyaltyTitle);
        loyaltySubtitle = findViewById(R.id.loyaltySubtitle);
        loyaltyButton = findViewById(R.id.loyaltyButton);
    }

    private void configLoyaltyRingView() {
        setProgress();
        setTitle();
        setButton();
        setSubtitle();
    }

    private void setProgress() {
        final int colorRing = Color.parseColor(businessLoyaltyRingData.getRingHexaColor());
        progress.setColorText(colorRing);
        progress.setColorProgress(colorRing);
        progress.setProgress(businessLoyaltyRingData.getRingPercentage());
        progress.setAnimation();
        progress.setNumber(businessLoyaltyRingData.getRingNumber());
    }

    private void setTitle() {
        loyaltyTitle.setText(businessLoyaltyRingData.getTitle());
    }

    private void setButton() {
        loyaltyButton.setText(businessLoyaltyRingData.getButtonTitle());
        if (businessLoyaltyRingData.getButtonDeepLink() != null) {
            loyaltyButton.setOnClickListener(v -> {
                if (onClickLoyaltyRing != null) {
                    final OnClickLoyaltyRing listener = onClickLoyaltyRing.get();
                    if (listener != null) {
                        listener.onClickLoyaltyButton(businessLoyaltyRingData.getButtonDeepLink());
                    }
                }
            });
        } else {
            loyaltyButton.setVisibility(GONE);
        }
    }



    private void setSubtitle() {
        if (businessLoyaltyRingData.getSubtitle() != null) {
            loyaltySubtitle.setText(businessLoyaltyRingData.getSubtitle());
            loyaltySubtitle.setVisibility(VISIBLE);
        } else {
            loyaltySubtitle.setVisibility(GONE);
        }
    }

    public void init(@NonNull final MLBusinessLoyaltyRingData businessLoyaltyRingData,
                     @NonNull final OnClickLoyaltyRing onClick) {
        this.businessLoyaltyRingData = businessLoyaltyRingData;
        onClickLoyaltyRing = new WeakReference<>(onClick);
        configLoyaltyRingView();
    }

}