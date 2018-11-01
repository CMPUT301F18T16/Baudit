package ca.klapstein.baudit.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatientTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addProblem() {

        Patient patient = new Patient();

        // One Problem
        Problem problem0 = new Problem();
        patient.addProblem(problem0);
        assertTrue(patient.getProblemTreeSet().contains(problem0));

        // Two Problems
        Problem problem1 = new Problem();
        patient.addProblem(problem1);
        assertTrue(patient.getProblemTreeSet().contains(problem0) &&
                patient.getProblemTreeSet().contains(problem1));
    }

    @Test
    public void getProblemTreeSet() {

        Patient patient = new Patient();

        // One Problem
        Problem problem0 = new Problem();
        patient.getProblemTreeSet().add(problem0);
        assertTrue(patient.getProblemTreeSet().contains(problem0));

        // Two Problems
        Problem problem1 = new Problem();
        patient.getProblemTreeSet().add(problem1);
        assertTrue(patient.getProblemTreeSet().contains(problem0) &&
                patient.getProblemTreeSet().contains(problem1));
    }

    @Test
    public void deleteProblem() {

        Patient patient = new Patient();

        // Add Three Problems
        Problem problem0 = new Problem();
        Problem problem1 = new Problem();
        Problem problem2 = new Problem();
        patient.addProblem(problem0);
        patient.addProblem(problem1);
        patient.addProblem(problem2);

        assertTrue(patient.getProblemTreeSet().contains(problem2));
        patient.deleteProblem(problem2);
        assertFalse(patient.getProblemTreeSet().contains(problem2));

        patient.deleteProblem(problem0);
        assertFalse(patient.getProblemTreeSet().contains(problem1));
    }

    @Test
    public void listProblemRecords() {

        Patient patient = new Patient();
        Problem problem0 = new Problem();
        patient.addProblem(problem0);

        Record record0 = new Record();
        patient.addRecordToProblem(record0, problem0);

        assertTrue(patient.getProblemTreeSet().contains(problem0));
        assertTrue(problem0.getRecordTreeSet().contains(record0));

        Record record1 = new Record();
        patient.addRecordToProblem(record1, problem0);
        assertTrue(patient.getProblemTreeSet().contains(problem0));
        assertTrue(problem0.getRecordTreeSet().contains(record1));

    }

    @Test
    public void addRecordToProblem() {

        Patient patient = new Patient();

        Problem problem0 = new Problem();
        patient.addProblem(problem0);

        Record record0 = new Record();
        Record record1 = new Record();
        patient.addRecordToProblem(record0, problem0);
        patient.addRecordToProblem(record1, problem0);

        RecordTreeSet recordTreeSet = patient.getProblemTreeSet().first().getRecordTreeSet();

        assertTrue(recordTreeSet.contains(record0));
        assertTrue(recordTreeSet.contains(record1));
    }


    @Test
    public void searchProblem(){

        Patient patient = new Patient();

        Problem problem0 = new Problem();
        Problem problem1 = new Problem();
        Problem problem2 = new Problem();
        problem0.setTitle("helloo");
        problem1.setTitle("yellow");
        problem2.setTitle("melo");


        patient.addProblem(problem0);
        patient.addProblem(problem1);
        patient.addProblem(problem2);

        ProblemTreeSet results = patient.searchProblem("hello");
        assertTrue(results.contains(problem0));
        results = patient.searchProblem("elo");
        assertTrue(results.contains(problem0));
        assertTrue(results.contains(problem1));
        assertTrue(results.contains(problem2));
    }

    @Test
    public void searchRecords(){
        Patient patient = new Patient();

        Problem problem0 = new Problem();

        Record record0 = new Record();
        Record record1 = new Record();
        Record record2 = new Record();
        record0.setComment("hello");
        record1.setComment("yellow");
        record2.setComment("melo");

        patient.addRecordToProblem(record0, problem0);
        patient.addRecordToProblem(record1, problem0);
        patient.addRecordToProblem(record2, problem0);

        RecordTreeSet results = patient.searchRecords("hello");
        assertTrue(results.contains(record0));
        results = patient.searchRecords("elo");
        assertTrue(results.contains(record0));
        assertTrue(results.contains(record1));
        assertTrue(results.contains(record2));
    }

    /* Done in activity???
    @Test
    public void editContactInfo(){
        Patient patient = new Patient();
        ContactInfo contactInfo = new ContactInfo();

        Email email = new Email("John@hotmail.com");
        contactInfo.setEmail(email);
        PhoneNumber number = new PhoneNumber("780-123-4567");
        contactInfo.setPhoneNumber(number);
        patient.setContactInfo(contactInfo);

        assertTrue(patient.getContactInfo().getEmail().getEmail().equals("John@hotmail.com"));
        assertTrue(patient.getContactInfo().getPhoneNumber().getPhoneNumber().equals("780-123-4567"));

        ContactInfo newContactInfo = new ContactInfo();
        newContactInfo.setEmail(new Email("NotJohn@hotmail.com"));
        newContactInfo.setPhoneNumber(new PhoneNumber("123-456-7890"));
        patient.edit

        assertTrue(patient.getContactInfo().getEmail().getEmail().equals("NotJohn@hotmail.com"));
        assertTrue(patient.getContactInfo().getPhoneNumber().getPhoneNumber().equals("123-456-7890"));
    }
    */

    @Test
    public void getUsername() {
        Patient patient = new Patient();
        Username username = new Username("John");

        patient.setUsername(username);
        assertTrue(patient.getUsername().equals(username));
    }

    @Test
    public void setUsername() {
        Patient patient = new Patient();
        Username username = new Username("John");

        patient.setUsername(username);
        assertTrue(patient.getUsername().equals(username));

    }

    @Test
    public void getContactInfo() {
        Patient patient = new Patient();
        ContactInfo contactInfo = new ContactInfo();

        Email email = new Email("John@hotmail.com");
        contactInfo.setEmail(email);
        PhoneNumber number = new PhoneNumber("780-123-4567");
        contactInfo.setPhoneNumber(number);
        patient.setContactInfo(contactInfo);

        assertTrue(patient.getContactInfo().equals(contactInfo));
        assertTrue(patient.getContactInfo().getEmail().equals(email));
        assertTrue(patient.getContactInfo().getPhoneNumber().equals(number));

    }

    @Test
    public void setContactInfo() {
        Patient patient = new Patient();
        ContactInfo contactInfo = new ContactInfo();

        Email email = new Email("John@hotmail.com");
        contactInfo.setEmail(email);
        PhoneNumber number = new PhoneNumber("780-123-4567");
        contactInfo.setPhoneNumber(number);
        patient.setContactInfo(contactInfo);

        assertTrue(patient.getContactInfo().equals(contactInfo));
        assertTrue(patient.getContactInfo().getEmail().equals(email));
        assertTrue(patient.getContactInfo().getPhoneNumber().equals(number));
    }

    @Test
    public void getPassword() {
        Patient patient = new Patient();
        Password password = new Password("hidden");

        patient.setPassword(password);
        assertTrue(patient.getPassword().equals(password));

    }

    @Test
    public void setPassword() {
        Patient patient = new Patient();
        Password password = new Password("hidden");

        patient.setPassword(password);
        assertTrue(patient.getPassword().equals(password));
    }
}