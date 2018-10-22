package ca.klapstein.baudit;

import java.util.TreeSet;

/**
 * A {@code TreeSet} the models a list of {@code Problem}s of a {@code Patient}.
 *
 * This is viewed by both the {@code CareProvider} and the {@code Patients}.
 *
 * @see Problem
 * @see Patient
 * @see CareProvider
 */
class ProblemTreeSet extends TreeSet<Problem> {
}
