package classes.accounts;

import jakarta.persistence.*;
import classes.common.User;
import classes.movements.AccountMovement;

@Entity
public class SavingsAccount extends Account {

    @Column
    double interestRate = 0.01;

    @Column
    double gains;

    protected SavingsAccount(){}
    public SavingsAccount(User owner, Double strBalance, accountType type) {
        super(owner,strBalance, type);
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
