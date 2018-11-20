package ca.klapstein.baudit.presenters;

import android.content.Context;
import ca.klapstein.baudit.data.Account;
import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.views.HomeView;

/**
 * MVP presenter for presenting a {@code ProblemTreeSet} on a {@code ProblemListView}.
 *
 * @see HomeView
 * @see Account
 * @see ProblemTreeSet
 */
public class PatientHomePresenter extends HomePresenter<HomeView> {

    private final Account account;
    private ProblemTreeSet problemTreeSet;

    public PatientHomePresenter(HomeView view, Context context) {
        super(view, context);
        account = dataManager.getLoggedInAccount();
        problemTreeSet = dataManager.getLoggedInPatient().getProblemTreeSet();
    }

    public Problem getProblemAt(int position) {
        return (Problem) problemTreeSet.toArray()[position];
    }

    public int getProblemCount() {
        return problemTreeSet.size();
    }

    public void viewStarted() {
        problemTreeSet = dataManager.getLoggedInPatient().getProblemTreeSet();
        view.updateUsernameDisplay(account.getUsername().toString());
        view.updateEmailDisplay(account.getContactInfo().getEmail().toString());
        view.updateList();
    }
}
