package classes.common;
import classes.accounts.Account;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements printerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="owner")
    List<Account> accounts = new ArrayList<>();

    public User(){}
    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Nome: " + name + ", ID do Cliente: " + id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String[] toStringDetail() {
        return null;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
