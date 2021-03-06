package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class PatientTreeSetTest {

    private PatientTreeSet patientTreeSet;

    @Before
    public void setUp() {
        patientTreeSet = new PatientTreeSet();
    }

    @After
    public void tearDown() {
        patientTreeSet = null;
    }

    @Test
    public void testPatientTreeSetConstructor() {
        assertNotNull(this.patientTreeSet);
    }

    @Test
    public void testPatientTreeSetComparable() {
        // Assert PatientTreeSet is sorted by username alphabetically

        Patient patient0 = new Patient(
                new Username("AName123"),
                new ContactInfo("FirstNameA", "LastNameA", new Email("fooA@example.com"), new PhoneNumber("111-111-1111")));
        Patient patient1 = new Patient(
                new Username("BName123"),
                new ContactInfo("FirstNameB", "LastNameC", new Email("fooB@example.com"), new PhoneNumber("222-222-2222")));
        Patient patient2 = new Patient(
                new Username("CName123"),
                new ContactInfo("FirstNameC", "LastNameC", new Email("fooC@example.com"), new PhoneNumber("333-333-3333")));

        patientTreeSet.add(patient2);
        patientTreeSet.add(patient1);
        patientTreeSet.add(patient0);

        assertTrue(patientTreeSet.first().equals(patient0));
        assertTrue(patientTreeSet.last().equals(patient2));
        assertTrue(patientTreeSet.size() == 3);
    }
}