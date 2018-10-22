package ca.klapstein.baudit.Data;

import ca.klapstein.baudit.Data.CareProvider;
import ca.klapstein.baudit.Data.Patient;
import ca.klapstein.baudit.Data.Problem;

import java.util.TreeSet;

/**
 * {@code TreeSet} subclass that models a list of {@code Problem}s of a {@code Patient}.
 *
 * This is viewed by both the {@code CareProvider} and the {@code Patients}.
 *
 * @see Problem
 * @see Patient
 * @see CareProvider
 */
public class ProblemTreeSet extends TreeSet<Problem> {
}
