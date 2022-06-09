package classes.movements;
import classes.accounts.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public abstract class AccountMovement {
    @Id
    protected int id;
    @Column
    protected String description;
    @Column
    protected float value;
    @Column
    protected movementStatus status = movementStatus.PEDING;

    private AccountMovement(){}
    public AccountMovement(Account origin, float value, String desc) {
        this.description = desc;
        this.value = value;
    }

    public AccountMovement(Account origin, float value) {
        this.description = "";
    }


    String getDescription() {
        return this.description;
    }

    float getValue() {
        return this.value;
    }

    String getStatus() {
        return this.status.toString();
    }

    void setStatus(movementStatus status) {
        this.status = status;
    }

    public void setStatus(String status) {
        movementStatus enumStatus = movementStatus.valueOf(status.toUpperCase());
        this.setStatus(enumStatus);
    }

}
