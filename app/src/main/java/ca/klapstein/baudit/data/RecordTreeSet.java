package ca.klapstein.baudit.data;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.UUID;

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
    //private ArrayList<UUID> recordIDArrayList; TODO: optional creation of array list to track unique recordID's in a record tree
}
