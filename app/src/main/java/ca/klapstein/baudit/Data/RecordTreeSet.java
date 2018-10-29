package ca.klapstein.baudit.Data;

import java.util.TreeSet;

/**
 * {@code TreeSet} subclass that models a list of {@code Record}s of a Medical Problem {@code Problem}.
 * <p>
 * This is viewed by both the {@code CareProvider} and the {@code Patients}.
 *
 * @see Record
 * @see Problem
 * @see Patient
 * @see CareProvider
 */
public class RecordTreeSet extends TreeSet<Record> {
    private static final String TAG = "RecordTreeSet";
}
