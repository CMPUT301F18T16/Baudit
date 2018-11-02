package ca.klapstein.baudit.adapters;

import android.support.test.runner.AndroidJUnit4;
import ca.klapstein.baudit.data.RecordTreeSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class RecordAdapterTest {

    private RecordTreeSet recordTreeSet;
    private RecordAdapter recordAdapter;

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