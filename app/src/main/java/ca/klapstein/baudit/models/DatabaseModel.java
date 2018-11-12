package ca.klapstein.baudit.models;

import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;

/**
 * Helper class for managing Baudit's local SQL database usage.
 */
public class DatabaseModel {

    private ProblemTreeSet testProblemTreeSet;

    public DatabaseModel() {
        super();
        testProblemTreeSet = new ProblemTreeSet();
        testProblemTreeSet.add(new Problem(
            "Ouch",
            "Big hole in my brain"
        ));

        testProblemTreeSet.add(new Problem(
            "Strange spot",
            "Large purple spot on my eyeball"
        ));

        testProblemTreeSet.add(new Problem(
            "Absence of limbs",
            "I swear I had them all not too long ago..."
        ));
    }

    public ProblemTreeSet getTestProblemTreeSet() {
        return testProblemTreeSet;
    }
}
