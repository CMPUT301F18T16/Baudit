package ca.klapstein.baudit.views;

public interface EditAccountView extends View {
    void updateFields(String name, String email, String phoneNumber);
}
