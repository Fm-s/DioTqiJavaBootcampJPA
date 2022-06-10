package classes.DAO;
import classes.common.DbAcess;
import classes.common.User;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class UserDAO {

    private List<User> userList;

    public List<User> getUsersList(){
        DbAcess db = new DbAcess();
        //Criteria Builder from entity manager
        CriteriaBuilder cb = db.mrg().getCriteriaBuilder();
        //Create Query Builder with User format
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        //Get User Path
        Root<User> userPath = cq.from(User.class);
        //Select User from Path
        CriteriaQuery<User> all = cq.select(userPath);
        //Make the Query
        TypedQuery<User> allQuery = db.mrg().createQuery(all);

        this.userList = allQuery.getResultList();
        db.close();
        return this.userList;
    }

    public List<User> getUsersByName(String str){
        String columnName = "name";
        DbAcess db = new DbAcess();
        //Criteria Builder from entity manager
        CriteriaBuilder cb = db.mrg().getCriteriaBuilder();
        //Create Query Builder with User format
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        //Get User Path
        Root<User> userPath = cq.from(User.class);
        //Select User from Path where Like str recived
        CriteriaQuery<User> nameLike = cq.select(userPath).where(cb.like(userPath.get(columnName), "%"+str+"%"));
        //Make the Query
        TypedQuery<User> customQuery = db.mrg().createQuery(nameLike);
        this.userList = customQuery.getResultList();
        db.close();
        return this.userList;
    }

    public static User getUserById(int id) {
        DbAcess db = new DbAcess();
        User ret = null;
        if (id > 0) {
            ret = db.mrg().find(User.class, id);
            db.close();
        }
        return ret;
    }

    public static User getUserById(String id) {
        DbAcess db = new DbAcess();
        User ret = null;
        if (Integer.parseInt(id) > 0) {
            ret = db.mrg().find(User.class, Integer.parseInt(id));
            db.close();
        }
        return ret;
    }
}
