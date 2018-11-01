package ca.klapstein.baudit.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatientTest {

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
    public void listProblemRecords() {

        Patient patient = new Patient();
        Problem problem0 = new Problem();
        patient.getProblemTreeSet().add(problem0);

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
        patient.getProblemTreeSet().add(problem0);

        Record record0 = new Record();
        Record record1 = new Record();
        patient.addRecordToProblem(record0, problem0);
        patient.addRecordToProblem(record1, problem0);

        RecordTreeSet recordTreeSet = patient.getProblemTreeSet().first().getRecordTreeSet();

        assertTrue(recordTreeSet.contains(record0));
        assertTrue(recordTreeSet.contains(record1));
    }

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
        Password password = new Password("hidden12");

        patient.setPassword(password);
        assertTrue(patient.getPassword().equals(password));

    }

    @Test
    public void setPassword() {
        Patient patient = new Patient();
        Password password = new Password("hidden12");

        patient.setPassword(password);
        assertTrue(patient.getPassword().equals(password));
    }
}