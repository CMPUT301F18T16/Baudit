package ca.klapstein.baudit.Adapters;

import android.support.test.runner.AndroidJUnit4;

import ca.klapstein.baudit.Data.RecordTreeSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class RecordAdapterTest {

    RecordTreeSet recordTreeSet;
    RecordAdapter recordAdapter;

    @Before
    public void setUp() {
        recordTreeSet = new RecordTreeSet();
        recordAdapter = new RecordAdapter(recordTreeSet);
    }

    @After
    public void tearDown() {
        recordTreeSet.clear();
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder() {
    }

    @Test
    public void getItemCount() {
        assertEquals(0, recordAdapter.getItemCount());
    }
}