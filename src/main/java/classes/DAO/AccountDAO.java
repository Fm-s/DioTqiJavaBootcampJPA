package classes.DAO;


import classes.accounts.Account;
import classes.accounts.CheckingAccount;
import classes.accounts.SavingsAccount;
import classes.common.DbAcess;
import classes.common.User;
import classes.common.printerDetail;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;

import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public static Account getAccountById(int id) {
        DbAcess db = new DbAcess();
        Account ret = null;
        if (id > 0) {
            ret = db.mrg().find(Account.class, (id));
            db.close();
        }
        return ret;
    }

    public static Account getAccountById(String id) {
        DbAcess db = new DbAcess();
        Account ret = null;
        if (Integer.parseInt(id) > 0) {
            ret = db.mrg().find(Account.class, Integer.parseInt(id));
            System.out.println("Entrou");
            for (String item : ret.toStringDetail()) {
                System.out.println(item);
                System.out.println("foi");
            }
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

    public List<Account> getAccountsListDetail() {
        DbAcess db = new DbAcess();
        CriteriaBuilder cb = db.mrg().getCriteriaBuilder();

        CriteriaQuery<Account> cq1 = cb.createQuery(Account.class);
        CriteriaQuery<Account> cq2 = cb.createQuery(Account.class);

        Root<SavingsAccount> svAcc = cq1.from(SavingsAccount.class);
        Root<CheckingAccount> ckAcc = cq2.from(CheckingAccount.class);

        svAcc.fetch("id", JoinType.LEFT);
        ckAcc.fetch("id", JoinType.LEFT);
        cq1.select(svAcc);
        cq2.select(ckAcc);

        TypedQuery<Account> query1 = db.mrg().createQuery(cq1);
        TypedQuery<Account> query2 = db.mrg().createQuery(cq2);

        List<Account> tempList = query1.getResultList();
        tempList.addAll(query2.getResultList());

        db.close();

        System.out.println(tempList);


        return tempList;
    }

    public List<Account> getAccountsByUserName(String str) {
        String columnName = "name";
        DbAcess db = new DbAcess();
        CriteriaBuilder cb = db.mrg().getCriteriaBuilder();
        CriteriaQuery<User> cqUser = cb.createQuery(User.class);
        Root<User> userRoot = cqUser.from(User.class);
        CriteriaQuery<User> whereLike = cqUser.select(userRoot).where(cb.like(userRoot.get(columnName), "%" + str + "%"));
        TypedQuery<User> customQuery = db.mrg().createQuery(whereLike);
        List<User> users = customQuery.getResultList();
        List<Account> accList = new ArrayList<>();
        for (User usr : users) {
            accList.addAll(usr.getAccounts());
        }
        db.close();
        this.accountList = accList;
        return accList;
    }

    public static Account getAccountsByUserId(int id) {
        return null;
    }

    public static Account getAccountsByUserId(String id) {
        return null;
    }
}




