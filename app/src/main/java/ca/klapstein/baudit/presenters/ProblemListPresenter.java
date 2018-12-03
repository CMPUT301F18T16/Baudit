package ca.klapstein.baudit.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.views.ProblemListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ProblemListPresenter<V extends ProblemListView> extends Presenter<V> {
    private static final String TAG = "ProblemListPresenter";

    @NonNull
    protected ProblemTreeSet problemTreeSet = new ProblemTreeSet();
    protected Patient patient;

    public ProblemListPresenter(V view, Context context) {
        super(view, context);
    }

    public int getTrueProblemIndex(int position) {
        try {
            Problem problem = (Problem) problemTreeSet.toArray()[position];
            return new ArrayList<Problem>(Arrays.asList(patient.getProblemTreeSet().toArray(new Problem[0]))).indexOf(problem);
        } catch (Exception e) {
            Log.e(TAG, "failed to obtain true problem index", e);
            view.updateAccountLoadError();
            return 0;
        }
    }

    public void filterProblemsByKeyWords(CharSequence constraint) {
        try {
            problemTreeSet.clear();
            problemTreeSet.addAll(patient.getProblemTreeSet());
        } catch (Exception e) {
            Log.e(TAG, "failed to obtain patient account info", e);
        }

        ArrayList<String> searchTokens = new ArrayList<>(Arrays.asList(constraint.toString().toLowerCase().split(" ")));
        Log.d(TAG, "filtering with tokens: " + searchTokens);
        Problem[] problemArray = problemTreeSet.toArray(new Problem[0]);
        for (Problem aProblemArray : problemArray) {
            if (Collections.disjoint(searchTokens, aProblemArray.getKeywords()))
                problemTreeSet.remove(aProblemArray);
        }
        view.updateList();
        view.updateProblemNumber(problemTreeSet.size());
    }
}
