package ca.klapstein.baudit.views;

public interface HomeView extends View {
    void updateUsernameDisplay(String name);
    void updateEmailDisplay(String email);
    void updateList();
}
