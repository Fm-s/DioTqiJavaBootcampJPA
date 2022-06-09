package classes.accounts;

import classes.common.User;
import classes.common.printerDetail;
import jakarta.persistence.*;

import static jakarta.persistence.InheritanceType.JOINED;
import static jakarta.persistence.EnumType.STRING;

import classes.movements.AccountMovement;

@Entity
@Inheritance(strategy = JOINED)
public abstract class Account  implements printerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = classes.common.User.class)
    private User owner;

    @Column
    private double balance;

    @Enumerated(STRING)
    private accountType accType;

    protected Account() {
    }

    protected Account(User owner, double strBalance, accountType accType) {
        this.owner = owner;
        this.balance = strBalance;
        this.accType = accType;
    }

    public double getBalance(){
        return this.balance;
    }

    public abstract AccountMovement withdraw(float value);

    public abstract AccountMovement deposit(float value);

    public abstract AccountMovement transfer(Account destination, float value);

    @Override
    public String toString() {
        return "Numero: " + id + ", Cliente: " + owner.toString();
    }


}
