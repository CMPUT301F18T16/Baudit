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
import ca.klapstein.baudit.presenters.PatientListPresenter;
import ca.klapstein.baudit.views.PatientListView;
import ca.klapstein.baudit.views.PatientRowView;

/**
 * Activity for listing {@code Patient}s.
 *
 * @see ca.klapstein.baudit.data.Patient
 */
public class PatientListActivity extends AppCompatActivity implements PatientListView {
    private static final String TAG = "PatientListActivity";

    private PatientListPresenter presenter;
    private RecyclerView patientRecyclerView;
    private PatientListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        presenter = new PatientListPresenter(this);

        // TODO: get patientTreeSet from local storage/remote

        patientRecyclerView = findViewById(R.id.patient_list);
        adapter = new PatientListAdapter();
        patientRecyclerView.setAdapter(adapter);
    }

    @Override
    public void update() {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    private class PatientListAdapter extends RecyclerView.Adapter<PatientViewHolder> {
        private static final String TAG = "PatientListAdapter";

        public PatientListAdapter() {
            super();
        }

        @NonNull
        @Override
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_patient, viewGroup, false);
            return new PatientViewHolder(v); //Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull PatientViewHolder viewHolder, int i) {
            presenter.onBindPatientRowViewAtPosition(viewHolder, i);
        }

        @Override
        public int getItemCount() {
            return presenter.getPatientCount();
        }
    }

    private class PatientViewHolder extends RecyclerView.ViewHolder implements PatientRowView {
        private static final String TAG = "PatientViewHolder";

        CardView mCardView;
        TextView mNameView;

        PatientViewHolder(CardView card) {
            super(card);
            mCardView = card;
            mNameView = card.findViewById(R.id.patient_name);
        }

        @Override
        public void setPatientNameText(String patientName) {
            mNameView.setText(patientName);
        }

        @Override
        public void onStart() {

        }
    }
}
