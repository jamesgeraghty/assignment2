package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


import models.Assessment;
import play.db.jpa.Model;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Trainer<trainer> extends Model
{
    public String trainer;
    public String email;
    public String password;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> members = new ArrayList<Member>();


    public Trainer(String email, String password, String trainer)
    {
        this.trainer = trainer;
        this.email = email;
        this.password = password;
    }
    public static Trainer findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }
}