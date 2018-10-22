package ca.klapstein.baudit;

import java.util.TreeSet;

/**
 * {@code TreeSet} subclass that models a list of {@code Record}s of a Medical Problem {@code Problem}.
 *
 * This is viewed by both the {@code CareProvider} and the {@code Patients}.
 *
 * @see Record
 * @see Problem
 * @see Patient
 * @see CareProvider
 */
class RecordTreeSet extends TreeSet<Record> {
}
