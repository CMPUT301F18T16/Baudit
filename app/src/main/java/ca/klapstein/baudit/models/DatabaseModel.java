package ca.klapstein.baudit.models;

import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;

/**
 * Helper class for managing Baudit's local SQL database usage.
 */
public class DatabaseModel {
    private static final String TAG = "DatabaseModel";

    private ProblemTreeSet testProblemTreeSet = new ProblemTreeSet();

    public DatabaseModel() {
        super();
        Problem testProblem1 = new Problem();
        testProblem1.setTitle("Ouch");
        testProblem1.setDescription("Big hole in my brain");
        testProblemTreeSet.add(testProblem1);

        Problem testProblem2 = new Problem();
        testProblem2.setTitle("Strange spot");
        testProblem2.setDescription("Large purple spot on my eyeball");
        testProblemTreeSet.add(testProblem2);

        Problem testProblem3 = new Problem();
        testProblem3.setTitle("Absence of limbs");
        testProblem3.setDescription("I swear I had them all not too long ago...");
        testProblemTreeSet.add(testProblem3);
    }

    public ProblemTreeSet getTestProblemTreeSet() {
        return testProblemTreeSet;
    }
}
