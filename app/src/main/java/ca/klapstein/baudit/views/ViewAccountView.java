package ca.klapstein.baudit.views;

public interface ViewAccountView extends View {
    void setViewAccountError();
    void setUsername(String username);
    void setEmail(String email);
}
