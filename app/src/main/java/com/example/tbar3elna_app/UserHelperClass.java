package com.example.tbar3elna_app;

public class UserHelperClass {

    String name, email, password, groupe, local;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String email, String password, String groupe, String local) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.groupe = groupe;
        this.local = local;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
