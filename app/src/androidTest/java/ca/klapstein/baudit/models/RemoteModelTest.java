package ca.klapstein.baudit.models;

import ca.klapstein.baudit.data.*;
import org.junit.Test;

import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

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

    @Test
    public void ValidateLoginInvalidUser() {
        assertNull(new RemoteModel.ValidateLogin().execute(
                new Username("NONSUCH_ACCOUNT").getUsernameString(),
                new Password("BADPASSWORD").getPassword()));
    }

    @Test
    public void uniqueIDTrue() throws InterruptedException, ExecutionException {
        assertTrue(new RemoteModel.UniqueID().execute("NONSUCH_ACCOUNT").get());
    }

    @Test
    public void uniqueIDFalse() throws InterruptedException, ExecutionException {
        // TODO: add hook to ensure the test account is added to the remote or make some mock
        assertFalse(new RemoteModel.UniqueID().execute("TESTCareProvider1").get());
        assertFalse(new RemoteModel.UniqueID().execute("TESTPatient1").get());
    }
}