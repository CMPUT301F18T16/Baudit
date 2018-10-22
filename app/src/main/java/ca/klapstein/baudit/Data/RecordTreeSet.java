package ca.klapstein.baudit.Data;

import ca.klapstein.baudit.Data.CareProvider;
import ca.klapstein.baudit.Data.Patient;
import ca.klapstein.baudit.Data.Problem;
import ca.klapstein.baudit.Data.Record;

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
public class RecordTreeSet extends TreeSet<Record> {
}
