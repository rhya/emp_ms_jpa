package empjpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by admin on 2017/10/18.
 */
public class EMUtil {
    private EntityManagerFactory entityManagerFactory;
    private static EMUtil instance;

    private EMUtil(){
        entityManagerFactory = Persistence.createEntityManagerFactory("Development_Environment");
    }
    public static EMUtil getInstance(){
        if(instance == null){
            instance = new EMUtil();
        }
        return instance;
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return getInstance().entityManagerFactory;
    }
    public static EntityManager getEntityManager(){
        return getEntityManagerFactory().createEntityManager();
    }
}
