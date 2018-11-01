package ca.klapstein.baudit.adapters;

import android.support.test.runner.AndroidJUnit4;
import ca.klapstein.baudit.Data.PatientTreeSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class PatientAdapterTest {

    private PatientTreeSet patientTreeSet;
    private PatientAdapter patientAdapter;

    @Before
    public void setUp() {
        patientTreeSet = new PatientTreeSet();
        patientAdapter = new PatientAdapter(patientTreeSet);
    }

    @After
    public void tearDown() {
        patientTreeSet.clear();
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder() {
    }

    @Test
    public void getItemCount() {
        assertEquals(0, patientAdapter.getItemCount());
    }
}