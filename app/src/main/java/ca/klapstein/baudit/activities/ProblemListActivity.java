package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
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

    /**
     * Launch a EditProblemActivity with the selected Problem noted by its position within the {@code ProblemTreeSet}.
     *
     * @param view     {@code View}
     * @param position {@code int} the position of the selected Problem within the {@code ProblemTreeSet}.
     */
    private void onProblemListItemClick(View view, final int position) {
        Intent intent = new Intent(this, EditProblemActivity.class);
        // TODO: add required args
        startActivity(intent);
    }

    /**
     * Launch a AddProblemActivity.
     */
    private void onAddProblemClick() {
        Intent intent = new Intent(this, AddProblemActivity.class);
        // TODO: add required args
        startActivity(intent);
    }

    @Override
    public void addProblem(Problem problem) {

    }

    @Override
    public void editProblem(Problem problem, int position) {

    }

    @Override
    public void setProblemList(ProblemTreeSet problemTreeSet) {

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
