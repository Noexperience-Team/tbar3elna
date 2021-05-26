package com.example.tbar3elna_app;

public class User  {
    private String Nom_et_prénom;
    private String Email;
    private String Password;
    private String Groupe_sanguin;
    private String Localisation;
    private String need;

    public User() {
    }

    public User(String nom_et_prénom, String email, String password, String groupe_sanguin, String localisation, String need) {
        Nom_et_prénom = nom_et_prénom;
        Email = email;
        Password = password;
        Groupe_sanguin = groupe_sanguin;
        Localisation = localisation;
        this.need = need;
    }

    public String getNom_et_prénom() {
        return Nom_et_prénom;
    }

    public void setNom_et_prénom(String nom_et_prénom) {
        Nom_et_prénom = nom_et_prénom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGroupe_sanguin() {
        return Groupe_sanguin;
    }

    public void setGroupe_sanguin(String groupe_sanguin) {
        Groupe_sanguin = groupe_sanguin;
    }

    public String getLocalisation() {
        return Localisation;
    }

    public void setLocalisation(String localisation) {
        Localisation = localisation;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }
}
