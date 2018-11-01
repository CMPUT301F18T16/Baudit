package ca.klapstein.baudit.adapters;

import android.support.test.runner.AndroidJUnit4;
import ca.klapstein.baudit.data.ProblemTreeSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ProblemAdapterTest {

    private ProblemTreeSet problemTreeSet;
    private ProblemAdapter problemAdapter;

    @Before
    public void setUp() {
        problemTreeSet = new ProblemTreeSet();
        problemAdapter = new ProblemAdapter(problemTreeSet);
    }

    @After
    public void tearDown() {
        problemTreeSet.clear();
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder() {
    }

    @Test
    public void getItemCount() {
        assertEquals(0, problemAdapter.getItemCount());
    }
}