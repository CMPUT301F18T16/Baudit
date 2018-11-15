package ca.klapstein.baudit.models;

import ca.klapstein.baudit.data.Problem;
import ca.klapstein.baudit.data.ProblemTreeSet;
import ca.klapstein.baudit.data.Record;
import ca.klapstein.baudit.data.RecordTreeSet;

/**
 * Helper class for managing Baudit's local SQL database usage.
 */
public class DatabaseModel {

    private ProblemTreeSet testProblemTreeSet;

    public DatabaseModel() {
        super();
        testProblemTreeSet = new ProblemTreeSet();

        Problem problem1 = new Problem(
            "Ouch",
            "Big hole in my brain"
        );
        RecordTreeSet recordTreeSet1 = new RecordTreeSet();
        recordTreeSet1.add(new Record("record1", "No comment"));
        recordTreeSet1.add(new Record("record2", "A comment"));
        problem1.setRecordTreeSet(recordTreeSet1);
        testProblemTreeSet.add(problem1);

        Problem problem2 = new Problem(
            "Strange spot",
            "Large purple spot on my eyeball"
        );
        RecordTreeSet recordTreeSet2 = new RecordTreeSet();
        recordTreeSet2.add(new Record("record1", "Hello!"));
        recordTreeSet2.add(new Record("record2", "Wow!"));
        problem2.setRecordTreeSet(recordTreeSet2);
        testProblemTreeSet.add(problem2);

        Problem problem3 = new Problem(
            "Absence of limbs",
            "I swear I had them all not too long ago..."
        );
        RecordTreeSet recordTreeSet3 = new RecordTreeSet();
        recordTreeSet3.add(new Record("record1", "record1"));
        recordTreeSet3.add(new Record("record2", "record2"));
        problem3.setRecordTreeSet(recordTreeSet3);
        testProblemTreeSet.add(problem3);
    }

    public ProblemTreeSet getTestProblemTreeSet() {
        return testProblemTreeSet;
    }
}
