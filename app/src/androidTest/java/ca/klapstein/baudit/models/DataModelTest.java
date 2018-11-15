package ca.klapstein.baudit.models;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import ca.klapstein.baudit.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertNotNull;

// TODO: improve these tests they don't actually cover failure or edge cases well
public class DataModelTest {

    private Context context;
    private DataModel dataModel;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
        dataModel = new DataModel(context);
    }

    @After
    public void tearDown() {
        context = null;
        dataModel = null;
    }

    @Test
    public void validateLogin() {
        // TODO: implement
    }

    @Test
    public void uniqueID() {
        // TODO: implement
    }

    @Test
    public void getPatients() {
        PatientTreeSet patientTreeSet = dataModel.getPatients();
        assertNotNull(patientTreeSet);
    }

    @Test
    public void commitPatients() {
        // commit an patientTreeSet this does nothing to the remote
        PatientTreeSet patientTreeSet = new PatientTreeSet();
        dataModel.commitPatients(patientTreeSet);
    }

    @Test(expected = NoSuchElementException.class)
    public void getCareProvider() {
        dataModel.getCareProvider(new Username("NONSUCHCAREPROVIDER"));
    }

    @Test
    public void commitCareProvider() {
        CareProvider careProvider = new CareProvider(
                new Username("CareProvider"), new Password("foobar123"),
                new ContactInfo(new Email("careprovider@example.com"), new PhoneNumber("123-456-7890"))
        );
        dataModel.commitCareProvider(careProvider);
    }
}