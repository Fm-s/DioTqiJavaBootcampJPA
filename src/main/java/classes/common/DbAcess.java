package classes.common;

import jakarta.persistence.*;


public class DbAcess {

    private EntityManager entityMgr;
    private EntityManagerFactory emf;

    public DbAcess() {
    }

    private void newConn() {
        try {
            this.emf = Persistence.createEntityManagerFactory("bootcamp");
            this.entityMgr = emf.createEntityManager();
        } catch (Exception e) {
            System.out.println("Erro ao acessar o banco" + e.getMessage());
        }
    }


    public EntityManager mrg() {
        this.newConn();
        return this.entityMgr;
    }

    public void add(Object obj) {
        this.newConn();
        this.entityMgr.getTransaction().begin();
        this.entityMgr.persist(obj);
        this.entityMgr.getTransaction().commit();
        this.close();
    }

    public void close() {
        this.entityMgr.close();
        this.emf.close();
    }
}
