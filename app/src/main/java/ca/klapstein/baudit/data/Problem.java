package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

/**
 * Data class representing a Medical Problem for a {@code Patient}.
 *
 * @see Patient
 */
public class Problem implements Comparable<Problem> {
    private static final String TAG = "Problem";
    private String title;
    private String description;
    private RecordTreeSet recordTreeSet;

    public RecordTreeSet getRecordTreeSet() {
        return recordTreeSet;
    }


//Create Medical Problem Object
    //Add,edit,delete   Medical Problem    UC-01.01.01, UC-01.02.01, UC-01.03.01, UC-01.04.0, User has a
    //List,Add,Edit     Record to Medical Problem UC-02.01.01, UC-02.02.01, UC-02.03.01

    public Problem(@NonNull String title, String description) throws IllegalArgumentException{
        this.setTitle(title);
        this.setDescription(description);
    }

    //BauditRemoteManager: handles data for remote
    public void setTitle(@NonNull String title)throws IllegalArgumentException {
        int len = title.length();
        if( len < 30 && 0 < len ){
            this.title = title;
        } else throw new IllegalArgumentException();


    }

    public void setDescription(String description) {
        int len = description.length();
        if (len < 300){
            this.description = description;
        } else throw new IllegalArgumentException();
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }


    @Override
    public int compareTo(@NonNull Problem problem) {
      return this.getTitle().compareTo(problem.getTitle());
    }
}
