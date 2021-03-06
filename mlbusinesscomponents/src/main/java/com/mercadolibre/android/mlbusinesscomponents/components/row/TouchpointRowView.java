package com.mercadolibre.android.mlbusinesscomponents.components.row;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointAssetLoader;
import com.mercadolibre.android.mlbusinesscomponents.common.TouchpointImageLoader;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.MainDescriptionLabelsText;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.MainDescriptionLabesImage;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.PickUpView;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.view.RightBottomInfoView;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.model.DescriptionItemsInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.callback.OnClickCallback;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card.AssetLoader;
import java.util.List;

public class TouchpointRowView extends ViewSwitcher implements OnClickCallback {

    private static final int TRANSLATION_PIXELS_X = 6;
    private static final int TRANSLATION_PIXELS_Y = 5;
    private static final int SKELETON_INDEX = 1;
    private static final int VIEW_INDEX = 0;

    private final SimpleDraweeView leftImage;
    private final SimpleDraweeView leftImageAccessory;
    private final TextView mainTitle;
    private final TextView mainSubtitle;
    private final TextView rightTopLabel;
    private final TextView rightPrimaryLabel;
    private final TextView rightSecondaryLabel;
    private final TextView rightMiddleLabel;
    private final RightBottomInfoView rightBottomInfoContainer;
    private final PickUpView mainDescriptionContainer;
    private final PickUpView mainCharacteristicsContainer;
    private final TouchpointRowPresenter presenter;
    private OnClickCallback onClickCallback;

    /**
     * Constructor
     *
     * @param context The execution context.
     **/
    public TouchpointRowView(final Context context) {
        this(context, null);
    }

    /**
     * Constructor
     *
     * @param context The execution context.
     * @param attrs The style attributes.
     **/
    public TouchpointRowView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.touchpoint_row_view, this);
        leftImage = findViewById(R.id.left_image);
        leftImageAccessory = findViewById(R.id.left_image_accessory);
        mainTitle = findViewById(R.id.main_title);
        mainSubtitle = findViewById(R.id.main_subtitle);
        rightTopLabel = findViewById(R.id.right_top_label);
        rightPrimaryLabel = findViewById(R.id.right_primary_label);
        rightSecondaryLabel = findViewById(R.id.right_secondary_label);
        rightMiddleLabel = findViewById(R.id.right_middle_label);
        mainDescriptionContainer = findViewById(R.id.main_description_container);
        mainCharacteristicsContainer = findViewById(R.id.main_characteristics_container);
        rightBottomInfoContainer = findViewById(R.id.right_bottom_info_container);
        rightBottomInfoContainer.bindViews();
        presenter = new TouchpointRowPresenter();
    }

    /**
     * Binds the model
     *
     * @param cardResponse the model
     **/
    public void bind(final TouchpointRowItemInterface cardResponse) {
        presenter.onBind(cardResponse, this);
    }

    /**
     * Show skeleton
     */
    public void showSkeleton() {
        setDisplayedChild(SKELETON_INDEX);
    }

    /**
     * Hide skeleton
     */
    public void hideSkeleton() {
        setDisplayedChild(VIEW_INDEX);
    }

    /**
     * Show brand logo
     *
     * @param logo the image source
     **/
    public void showImage(final String logo) {
        AssetLoader.getImage(logo, leftImage, (shouldLoadImage -> {
            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(leftImage).withSource(logo).load();
            }
        }));

        leftImage.setVisibility(VISIBLE);
    }

    /**
     * Hide the brand logo
     **/
    public void hideImage() {
        leftImage.setVisibility(GONE);
    }

    /**
     * Show brand name
     *
     * @param title The brand name
     **/
    public void showTitle(final String title) {
        mainTitle.setText(title);
        mainTitle.setVisibility(VISIBLE);
    }

    /**
     * Hide brand name
     **/
    public void hideTitle() {
        mainTitle.setVisibility(GONE);
    }

    /**
     * Show subtitle
     *
     * @param subtitle The subtitle
     **/
    public void showSubtitle(final String subtitle) {
        mainSubtitle.setText(subtitle);
        mainSubtitle.setVisibility(VISIBLE);
    }

    /**
     * hide subtitle
     **/
    public void hideSubtitle() {
        mainSubtitle.setVisibility(GONE);
    }

    /**
     * Show topLabel
     *
     * @param topLabel The untilText
     **/
    public void showTopLabel(final String topLabel) {
        rightTopLabel.setText(topLabel);
        rightTopLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide topLabel
     **/
    public void hideTopLabel() {
        rightTopLabel.setVisibility(GONE);
    }

    /**
     * Show discount mainLabel
     *
     * @param mainLabel The discount mainLabel
     **/
    public void showMainLabel(final String mainLabel) {
        rightPrimaryLabel.setText(mainLabel);
        rightPrimaryLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide discount rightLabel
     **/
    public void hideRightLabel() {
        rightSecondaryLabel.setVisibility(GONE);
    }

    /**
     * Show discount rightLabel
     *
     * @param rightLabel The discount rightLabel
     **/
    public void showRightLabel(final String rightLabel) {
        rightSecondaryLabel.setText(rightLabel);
        rightSecondaryLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide discount mainLabel
     */
    public void hideMainLabel() {
        rightPrimaryLabel.setVisibility(GONE);
    }

    /**
     * Show discount bottom label
     *
     * @param bottomLabel The discount bottom label
     */
    public void showBottomLabel(final String bottomLabel) {
        rightMiddleLabel.setText(bottomLabel);
        rightMiddleLabel.setVisibility(VISIBLE);
    }

    /**
     * Hide discount bottom label
     */
    public void hideBottomLabel() {
        rightMiddleLabel.setVisibility(GONE);
    }

    /**
     * Show store distance
     *
     * @param mainDescription the labels.
     */
    public void showDescriptionLabels(final List<DescriptionItemsInterface> mainDescription) {
        if(mainDescriptionContainer != null) {
            mainDescriptionContainer.bindViews(mainDescription);
        }
    }

    /**
     * Hide store description
     */
    public void hideDescriptionLabels() {
        if (mainDescriptionContainer.getChildCount() > 0) {
            mainDescriptionContainer.removeAllViews();
        }
        mainDescriptionContainer.setVisibility(GONE);
    }

    /**
     * Set on click listener
     *
     * @param link The deep link to launch
     */
    public void onClick(final String link) {
        setOnClickListener(view -> onClickEvent(link, view));
    }

    private void onClickEvent(final String link, final View view) {
        if (onClickCallback != null) {
            onClickCallback.onClick(link);
        }
    }

    public void setOnClickCallback(final OnClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    public void hideImageAccessory() {
        leftImageAccessory.setVisibility(GONE);
    }

    public void showImageAccessory(final String leftImageAccessory) {
        AssetLoader.getImage(leftImageAccessory, this.leftImageAccessory, (shouldLoadImage -> {
            if (shouldLoadImage) {
                TouchpointAssetLoader.create().withContainer(this.leftImageAccessory).withSource(leftImageAccessory).load();
            }
        }));

        this.leftImageAccessory.setVisibility(VISIBLE);
    }

    public void setRightLabelsToBlockedStatus() {
        rightMiddleLabel.setAlpha(0.4f);
        rightPrimaryLabel.setAlpha(0.4f);
        rightSecondaryLabel.setAlpha(0.4f);
    }

    public void setRightLabelsToDefaultStatus() {
        rightMiddleLabel.setAlpha(1f);
        rightPrimaryLabel.setAlpha(1f);
        rightSecondaryLabel.setAlpha(1f);
    }

    public void hideRightBottomInfo() {
        rightBottomInfoContainer.hideRightBottomInfoView();
    }

    public void showRightBottomInfo(final PillResponseInterface pill) {
        rightBottomInfoContainer.bind(pill);
        rightBottomInfoContainer.setTranslationX(TRANSLATION_PIXELS_X * getResources().getDisplayMetrics().density);
        rightBottomInfoContainer.setTranslationY(-TRANSLATION_PIXELS_Y * getResources().getDisplayMetrics().density);
    }

    /**
     * Sets the image loader
     *
     * @param imageLoader the image loader.
     */
    public void setImageLoader(final TouchpointImageLoader imageLoader) {
        AssetLoader.setStrategy(imageLoader);
    }


    /**
     * Show store distance
     *
     * @param mainDescription the labels.
     */
    public void showCharacterísticsLabels(final List<DescriptionItemsInterface> mainDescription) {
        if(mainCharacteristicsContainer != null) {
            mainCharacteristicsContainer.bindViews(mainDescription);
        }
    }

    /**
     * Hide store characteristics
     */
    public void hideCharacterísticsLabels() {
        if (mainCharacteristicsContainer.getChildCount() > 0) {
            mainCharacteristicsContainer.removeAllViews();
        }
        mainCharacteristicsContainer.setVisibility(GONE);
    }
}
