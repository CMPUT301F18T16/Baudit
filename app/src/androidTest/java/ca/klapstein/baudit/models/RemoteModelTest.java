package ca.klapstein.baudit.models;

import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PatientTreeSet;
import org.junit.Test;

import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

// TODO: coverage is provided by DataModelTest but we should still develop unit tests for this
public class RemoteModelTest {

    @Test
    public void uniqueID() {
        // TODO: implement
    }

    @Test
    public void validateLogin() {
        // TODO: implement
    }

    @Test
    public void getPatients() throws ExecutionException, InterruptedException {
        PatientTreeSet patientTreeSet = new RemoteModel.GetPatients().execute("").get();
        assertNotNull(patientTreeSet);
    }

    @Test
    public void addPatientsNull() {
        // adding a null list for an initial test (this does effectively nothing to the remote)
        new RemoteModel.AddPatientTask().execute(
                new PatientTreeSet().toArray(new Patient[0])
        );
    }

    @Test
    public void getCareProviderNull() throws ExecutionException, InterruptedException {
        CareProvider careProvider = new RemoteModel.GetCareProvider().execute("").get();
        assertNull(careProvider);
    }

    @Test
    public void addCareProviderNull() {
        // adding a null list for an initial test (this does effectively nothing to the remote)
        new RemoteModel.AddCareProviderTask().execute(
                new TreeSet<CareProvider>().toArray(new CareProvider[0])
        );
    }
}