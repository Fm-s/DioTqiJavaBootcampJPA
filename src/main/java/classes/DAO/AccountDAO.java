package classes.DAO;


import classes.accounts.Account;
import classes.accounts.CheckingAccount;
import classes.accounts.SavingsAccount;
import classes.common.DbAcess;
import classes.common.User;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class AccountDAO {

    public static Account getAccountById(int id) {
        DbAcess db = new DbAcess();
        Account ret = null;
        if (id > 0) {
            ret = db.mrg().find(Account.class, id);
            db.close();
        }
        return ret;
    }

    public static Account getAccountById(String id) {
        DbAcess db = new DbAcess();
        Account ret = null;
        if (Integer.parseInt(id) > 0) {
            ret = db.mrg().find(Account.class, Integer.parseInt(id));
            db.close();
        }
        return ret;
    }

    private List<Account> accountList;

    public List<Account> getAccountsList() {
        DbAcess db = new DbAcess();
        //Criteria Builder from entity manager
        CriteriaBuilder cb = db.mrg().getCriteriaBuilder();
        //Create Query Builder with Account format
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        //Get Account Path
        Root<Account> accountPath = cq.from(Account.class);
        //Select Account from Path
        CriteriaQuery<Account> all = cq.select(accountPath);
        //Make the Query
        TypedQuery<Account> allQuery = db.mrg().createQuery(all);

        accountList = allQuery.getResultList();
        db.close();
        return accountList;
    }

    public List<Account> getAccountsByUserName(String str) {
        String columnName = "name";
        DbAcess db = new DbAcess();
        //Criteria Builder from entity manager
        CriteriaBuilder cb = db.mrg().getCriteriaBuilder();
        //Create Query Builder with User format
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        //Get User Path
        Root<Account> accountPath = cq.from(Account.class);
        //Select User from Path where Like str recived
        CriteriaQuery<Account> nameLike = cq.select(accountPath).where(cb.like(accountPath.get(columnName), "%" + str + "%"));
        //Make the Query
        TypedQuery<Account> customQuery = db.mrg().createQuery(nameLike);
        this.accountList = customQuery.getResultList();
        db.close();
        return this.accountList;

        //Referencia
        //cr.select(root).where(cb.like(root.get("itemName"), "%chair%"));
    }

    public static Account getAccountsByUserId(int id) {
        return null;
    }

    public static Account getAccountsByUserId(String id) {
        return null;
    }
}




