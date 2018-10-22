package ca.klapstein.baudit;

import java.util.TreeSet;

/**
 * {@code TreeSet} subclass that models a list of {@code Patient}s.
 *
 * This is viewed only by {@code CareProvider}.
 *
 * @see Patient
 * @see CareProvider
 */
class PatientTreeSet extends TreeSet<Patient> {
}
