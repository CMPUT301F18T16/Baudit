package ca.klapstein.baudit.Data;

import java.util.TreeSet;

/**
 * {@code TreeSet} subclass that models a list of {@code Patient}s.
 * <p>
 * This is viewed only by {@code CareProvider}.
 *
 * @see Patient
 * @see CareProvider
 */
public class PatientTreeSet extends TreeSet<Patient> {
    private static final String TAG = "PatientTreeSet";
}
