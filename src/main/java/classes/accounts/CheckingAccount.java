package classes.accounts;

import jakarta.persistence.*;
import classes.common.User;
import classes.movements.AccountMovement;

@Entity
public class CheckingAccount extends Account {

    @Column
    private double creditLimit;

    protected CheckingAccount() {}

    public CheckingAccount(User user, double strBalance, accountType type) {
        super(user, strBalance, type);
        if (strBalance < 500) {
            this.creditLimit = 500;
        } else if (strBalance < 2000) {
            this.creditLimit = 1000;
        } else this.creditLimit = Math.round(strBalance / 2);
    }

    @Override
    public double getBalance() {
        return 0;
    }

    @Override
    public AccountMovement withdraw(float value) {
        return null;
    }

    @Override
    public AccountMovement deposit(float value) {
        return null;
    }

    @Override
    public AccountMovement transfer(Account destination, float value) {
        return null;
    }

    @Override
    public String[] toStringDetail() {
        return new String[0];
    }
}
