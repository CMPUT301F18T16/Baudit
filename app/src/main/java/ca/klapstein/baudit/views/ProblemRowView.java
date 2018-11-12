package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

public interface ProblemRowView extends View {
    void setProblemImage(Bitmap bmp);

    void setProblemTitleText(String title);

    void setProblemDateText(String date);

    void setProblemDescriptionText(String description);
}
