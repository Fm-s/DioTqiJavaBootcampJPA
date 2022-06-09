package classes.common;
import jakarta.persistence.*;

@Entity
public class User implements printerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String name;

    public User(){}
    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Nome: " + name + ", Codigo: " + id;
    }

    @Override
    public String[] toStringDetail() {
        return null;
    }
}
