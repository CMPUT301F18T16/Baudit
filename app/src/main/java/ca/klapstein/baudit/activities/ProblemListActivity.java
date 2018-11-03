package ca.klapstein.baudit.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.presenters.ProblemListPresenter;
import ca.klapstein.baudit.views.ProblemListView;
import ca.klapstein.baudit.views.ProblemRowView;

/**
 * Activity for listing {@code Problem}s.
 *
 * @see ca.klapstein.baudit.data.Problem
 */
public class ProblemListActivity extends AppCompatActivity implements ProblemListView {
    private static final String TAG = "ProblemListActivity";

    private ProblemListPresenter presenter;
    private RecyclerView problemRecyclerView;
    private ProblemListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);

        // TODO: get problemTreeSet from local storage/remote

        presenter = new ProblemListPresenter(this);

        problemRecyclerView = findViewById(R.id.problem_list);
        adapter = new ProblemListAdapter();
        problemRecyclerView.setAdapter(adapter);
    }

    @Override
    public void update() {
        this.adapter.notifyDataSetChanged();
    }

    private class ProblemListAdapter extends RecyclerView.Adapter<ProblemViewHolder> {
        private static final String TAG = "ProblemListAdapter";

        public ProblemListAdapter() {
            super();
        }

        @NonNull
        @Override
        public ProblemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_problem, viewGroup, false);
            return new ProblemViewHolder(v); //Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull ProblemViewHolder viewHolder, int i) {
            presenter.onBindProblemRowViewAtPosition(viewHolder, i);
        }

        @Override
        public int getItemCount() {
            return presenter.getProblemCount();
        }
    }

    private class ProblemViewHolder extends RecyclerView.ViewHolder implements ProblemRowView {
        private static final String TAG = "ProblemViewHolder";

        CardView mCardView;
        TextView mTitleView;

        ProblemViewHolder(CardView card) {
            super(card);
            mCardView = card;
            mTitleView = card.findViewById(R.id.problem_title);
        }

        @Override
        public void setProblemTitleText(String title) {
            mTitleView.setText(title);
        }
    }
}
