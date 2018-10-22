package ca.klapstein.baudit.Data;

import ca.klapstein.baudit.Data.CareProvider;
import ca.klapstein.baudit.Data.Patient;

import java.util.TreeSet;

/**
 * {@code TreeSet} subclass that models a list of {@code Patient}s.
 *
 * This is viewed only by {@code CareProvider}.
 *
 * @see Patient
 * @see CareProvider
 */
public class PatientTreeSet extends TreeSet<Patient> {
}
