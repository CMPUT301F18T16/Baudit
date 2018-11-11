package ca.klapstein.baudit.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.presenters.PatientHomePresenter;
import ca.klapstein.baudit.views.ProblemListView;
import ca.klapstein.baudit.views.ProblemRowView;

/**
 * Activity for listing {@code Problem}s.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
public class PatientHomeActivity extends AppCompatActivity implements ProblemListView {

    private PatientHomePresenter presenter;
    private RecyclerView problemRecyclerView;
    private ProblemListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitent_home);

        presenter = new PatientHomePresenter(this);

        problemRecyclerView = findViewById(R.id.problem_list);
        adapter = new ProblemListAdapter();
        problemRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void update() {
        this.adapter.notifyDataSetChanged();
    }

    private class ProblemListAdapter extends RecyclerView.Adapter<ProblemViewHolder> {

        @Override @NonNull
        public ProblemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_problem, viewGroup, false);
            return new ProblemViewHolder(v); //Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull ProblemViewHolder viewHolder, int i) {
            Problem problem = presenter.getProblemAt(i);

            viewHolder.setProblemTitleText(problem.getTitle());
            viewHolder.setProblemDateText(problem.getTimeStamp());
            viewHolder.setProblemDescriptionText(problem.getDescription());
        }

        @Override
        public int getItemCount() {
            return presenter.getProblemCount();
        }
    }

    private class ProblemViewHolder extends RecyclerView.ViewHolder implements ProblemRowView {

        CardView cardView;
        ImageView imageView;
        TextView titleView;
        TextView dateView;
        TextView descriptionView;

        ProblemViewHolder(CardView card) {
            super(card);
            cardView = card;
            imageView = card.findViewById(R.id.problem_card_image);
            titleView = card.findViewById(R.id.problem_card_title);
            dateView = card.findViewById(R.id.problem_card_date);
            descriptionView = card.findViewById(R.id.problem_card_description);
        }

        @Override
        public void onStart() {
            // Do nothing.
        }

        @Override
        public void setProblemImage(Bitmap bmp) {
            imageView.setImageBitmap(bmp);
        }

        @Override
        public void setProblemTitleText(String title) {
            titleView.setText(title);
        }

        @Override
        public void setProblemDateText(String date) {
            dateView.setText(date);
        }

        @Override
        public void setProblemDescriptionText(String description) {
            descriptionView.setText(description);
        }
    }
}
