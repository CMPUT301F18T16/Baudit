package ca.klapstein.baudit.models;

import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PatientTreeSet;
import org.junit.Test;

import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;

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
    public void addPatients() {
        // adding a null list for an initial test (this does effectively nothing to the remote)
        new RemoteModel.AddPatientTask().execute(
                new PatientTreeSet().toArray(new Patient[0])
        );

        // TODO: actually add a test Patient to the test remote?
    }

    @Test
    public void getCareProvider() throws ExecutionException, InterruptedException {
        // TODO: implement
        TreeSet<CareProvider> careProvider = new RemoteModel.GetCareProviders().execute("").get();
        assertNotNull(careProvider);
    }

    @Test
    public void addCareProvider() {
        // adding a null list for an initial test (this does effectively nothing to the remote)
        new RemoteModel.AddCareProviderTask().execute(
                new TreeSet<CareProvider>().toArray(new CareProvider[0])
        );

        // TODO: actually add a test CareProvider to the test remote?
    }
}