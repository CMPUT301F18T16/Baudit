package ca.klapstein.baudit.Data;

import org.junit.Test;

import static org.junit.Assert.*;

public class CareProviderTest {

    @Test
    public void getProblemTreeSet() {

        try {
            Patient patient = new Patient();
            Problem problem0 = new Problem();
            patient.getProblemTreeSet().add(problem0);
            Problem problem1 = new Problem();
            patient.getProblemTreeSet().add(problem1);

            CareProvider cp = new CareProvider();
            cp.getAssignedPatientTreeSet().add(patient);
            ProblemTreeSet patientProblemTreeSet = cp.getProblemTreeSet(patient);
            assertTrue(patientProblemTreeSet.contains(problem0) &&
                    patientProblemTreeSet.contains(problem1));
        }catch (IllegalArgumentException e){
            fail();
        }
    }

    @Test
    public void getRecordTreeSet(){

        Patient patient = new Patient();
        Problem problem0 = new Problem();
        patient.getProblemTreeSet().add(problem0);
        CareProvider cp = new CareProvider();
        cp.assignPatient(patient);

        Record record0 = new Record();
        problem0.getRecordTreeSet().add(record0);
        RecordTreeSet patientRecordTreeSet = cp.getRecordTreeSet(patient, problem0);
        assertTrue(patientRecordTreeSet.contains(record0));

        Record record1 = new Record();
        problem0.getRecordTreeSet().add(record1);
        patientRecordTreeSet = cp.getRecordTreeSet(patient, problem0);
        assertTrue(patientRecordTreeSet.contains(record1));
    }

    @Test
    public void getAssignedPatientTreeSet(){

        Patient patient0 = new Patient();
        CareProvider cp = new CareProvider();
        cp.assignPatient(patient0);
        PatientTreeSet patientTreeSet = cp.getAssignedPatientTreeSet();
        assertTrue(patientTreeSet.contains(patient0));

        Patient patient1 = new Patient();
        cp.assignPatient(patient1);
        patientTreeSet = cp.getAssignedPatientTreeSet();
        assertTrue(patientTreeSet.contains(patient1));
    }

    @Test
    public void assignPatient(){
        Patient patient = new Patient();
        CareProvider cp = new CareProvider();
        cp.assignPatient(patient);
        PatientTreeSet patientTreeSet = cp.getAssignedPatientTreeSet();
        assertTrue(patientTreeSet.contains(patient));

        Patient patient1 = new Patient();
        cp.assignPatient(patient1);
        patientTreeSet = cp.getAssignedPatientTreeSet();
        assertTrue(patientTreeSet.contains(patient1));
    }

    @Test
    public void getUsername() {
        CareProvider cp = new CareProvider();
        Username username = new Username("John");

        cp.setUsername(username);
        assertTrue(cp.getUsername().equals(username));
    }

    @Test
    public void setUsername() {
        CareProvider cp = new CareProvider();
        Username username = new Username("John");

        cp.setUsername(username);
        assertTrue(cp.getUsername().equals(username));

    }

    @Test
    public void setGetContactInfo() {

        // Test for both setContactInfo and getContactInfo

        CareProvider cp = new CareProvider();
        ContactInfo contactInfo = new ContactInfo();

        Email email = new Email("John@hotmail.com");
        contactInfo.setEmail(email);
        PhoneNumber number = new PhoneNumber("780-123-4567");
        contactInfo.setPhoneNumber(number);
        cp.setContactInfo(contactInfo);

        assertTrue(cp.getContactInfo().equals(contactInfo));
        assertTrue(cp.getContactInfo().getEmail().equals(email));
        assertTrue(cp.getContactInfo().getPhoneNumber().equals(number));

    }

    @Test
    public void getPassword() {
        CareProvider cp = new CareProvider();
        Password password = new Password("hidden12");

        cp.setPassword(password);
        assertTrue(cp.getPassword().equals(password));
    }

    @Test
    public void setPassword() {
        CareProvider cp = new CareProvider();
        String string = "hidden12";
        Password password = new Password(string);

        cp.setPassword(password);
        assertTrue(cp.getPassword().equals(password));
    }
}