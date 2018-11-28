package ca.klapstein.baudit.data;

import java.util.TreeSet;

/**
 * {@code TreeSet} subclass that models a list of {@code Problem}s of a {@code Patient}.
 * <p>
 * This is viewed by both the {@code CareProvider} and the {@code Patients}.
 *
 * @see Problem
 * @see Patient
 * @see CareProvider
 */
public class ProblemTreeSet extends TreeSet<Problem> {
    private static final String TAG = "ProblemTreeSet";
}

