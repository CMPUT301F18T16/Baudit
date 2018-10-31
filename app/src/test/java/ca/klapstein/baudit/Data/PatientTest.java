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

    /*
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
        assertFalse(patient.getProblemTreeSet().contains(problem0) &&
                patient.getProblemTreeSet().contains(problem1));
    }

    @Test// UC-01.04.1: delete problem
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
    */

    /*
    // UC-03.02.01: edit account contact information
    public void editContactInfo(ContactInfo contactInfo){ this.setContactInfo(contactInfo);}
    // UC-03.03.01: view username or account contact information
    public ContactInfo getContactInfo(){ return this.getContactInfo(); }
    public Username getUsername(){ return this.getUsername(); }
    // UC-03.03.01: login into account
    public boolean attemptLogin(Username username, Password password){
        if (this.getUsername().equals(username)) {
            if (this.getPassword().equals(password))
                return true;
            else return false;
        }
        else
            return false;
    }
    */

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

        email.setEmail("NotJohn@hotmail.com");
        contactInfo.setEmail(email);
        number.setPhoneNumber("123-456-7890");

        assertTrue(patient.getContactInfo().getEmail().getEmail().equals("NotJohn@hotmail.com"));
        assertTrue(patient.getContactInfo().getPhoneNumber().getPhoneNumber().equals("123-456-7890"));
    }

    @Test
    public void attemptLogin(){
        Patient patient = new Patient();
        Username username = new Username("John");
        Password password = new Password("hidden");
        patient.setUsername(username);
        patient.setPassword(password);

        assertFalse(patient.attemptLogin(new Username("Wrong"), new Password("Wrong")));
        assertFalse(patient.attemptLogin(new Username("Wrong"), new Password("hidden")));
        assertFalse(patient.attemptLogin(new Username("John"), new Password("Wrong")));
        assertTrue(patient.attemptLogin(new Username("John"), new Password("hidden")));
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