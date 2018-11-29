package ca.klapstein.baudit.views;

import android.graphics.Bitmap;

public interface ProblemRowView extends View {
    void updateProblemImage(Bitmap bmp);

    void updateProblemTitleText(String title);

    void updateProblemDateText(String date);

    void updateProblemDescriptionText(String description);
}
