package ca.klapstein.baudit.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CareProviderTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getProblemTreeSet() {

        Patient patient = new Patient();
        Problem problem0 = new Problem();
        patient.getProblemTreeSet().add(problem0);
        Problem problem1 = new Problem();
        patient.getProblemTreeSet().add(problem1);

        CareProvider cp = new CareProvider();
        cp.assignPatient(patient);
        ProblemTreeSet patientProblemTreeSet = cp.getProblemTreeSet(patient);
        assertTrue(patientProblemTreeSet.contains(problem0) &&
                patientProblemTreeSet.contains(problem1));
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
    public void searchProblem(){
        CareProvider cp = new CareProvider();

        Problem problem0 = new Problem();
        Problem problem1 = new Problem();
        Problem problem2 = new Problem();
        problem0.setTitle("helloo");
        problem1.setTitle("yellow");
        problem2.setTitle("melo");


        Patient patient = new Patient();
        patient.getProblemTreeSet().add(problem0);
        patient.getProblemTreeSet().add(problem1);
        patient.getProblemTreeSet().add(problem2);

        ProblemTreeSet results = cp.searchProblem("hello");
        assertTrue(results.contains(problem0));
        results = cp.searchProblem("elo");
        assertTrue(results.contains(problem0));
        assertTrue(results.contains(problem1));
        assertTrue(results.contains(problem2));
    }

    @Test
    public void searchRecords(){

        CareProvider cp = new CareProvider();

        Problem problem0 = new Problem();
        Record record0 = new Record();
        Record record1 = new Record();
        Record record2 = new Record();
        record0.setComment("hello");
        record1.setComment("yellow");
        record2.setComment("melo");

        Patient patient = new Patient();
        patient.addRecordToProblem(record0, problem0);
        patient.addRecordToProblem(record1, problem0);
        patient.addRecordToProblem(record2, problem0);

        RecordTreeSet results = cp.searchRecords("hello");
        assertTrue(results.contains(record0));
        results = cp.searchRecords("elo");
        assertTrue(results.contains(record0));
        assertTrue(results.contains(record1));
        assertTrue(results.contains(record2));
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
    public void getContactInfo() {
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
    public void setContactInfo() {
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
        Password password = new Password("hidden");

        cp.setPassword(password);
        assertTrue(cp.getPassword().equals(password));
    }

    @Test
    public void setPassword() {
        CareProvider cp = new CareProvider();
        Password password = new Password("hidden");

        cp.setPassword(password);
        assertTrue(cp.getPassword().equals(password));
    }
}