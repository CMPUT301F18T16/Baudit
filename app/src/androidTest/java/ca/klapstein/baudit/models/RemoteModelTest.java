package ca.klapstein.baudit.models;

import ca.klapstein.baudit.data.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class RemoteModelTest {

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
    public void ValidateLoginInvalidUser() throws ExecutionException, InterruptedException {
        assertNull(new RemoteModel.ValidateLogin().execute(
                new Username("NONSUCH_ACCOUNT").toString(),
                new Password("BADPASSWORD").toString()).get());
    }

    @Test
    public void ValidateLoginPatient() throws ExecutionException, InterruptedException {
        Account account = new RemoteModel.ValidateLogin().execute(new Username("TESTPatient1").toString(), new Password("foobar123").toString()).get();
        assertNotNull(account);
        assertEquals("TESTPatient1", account.getUsername().toString());
    }

    @Test
    public void ValidateLoginCareProvider() throws ExecutionException, InterruptedException {
        Account account = new RemoteModel.ValidateLogin().execute(new Username("TESTCareProvider1").toString(), new Password("foobar123").toString()).get();
        assertNotNull(account);
        assertEquals("TESTCareProvider1", account.getUsername().toString());
    }
}