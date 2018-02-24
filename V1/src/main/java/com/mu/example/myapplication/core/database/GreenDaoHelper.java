package com.mu.example.myapplication.core.database;

import android.database.sqlite.SQLiteDatabase;

import com.mu.example.myapplication.App;
import com.mu.example.myapplication.greendao.DaoMaster;
import com.mu.example.myapplication.greendao.DaoSession;
import com.mu.example.myapplication.greendao.UserDao;
import com.mu.example.myapplication.model.User;
import com.mu.example.myapplication.util.ToastUtil;

import java.util.List;

/**
 * Created by mu on 2018/2/14.
 */

public class GreenDaoHelper {
    private static DaoMaster.DevOpenHelper devOpenHelper;
    private static SQLiteDatabase database;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    /**
     * 初始化greenDao
     * 建议放在Application 中进行
     */
    public static void initDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        devOpenHelper = new DaoMaster.DevOpenHelper(App.getApplication(), "cache-db", null);//数据库名
        database = devOpenHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static SQLiteDatabase getDb() {
        return database;
    }

    public void insertUserData(User user) {
        //数据库的增删改查我们都将通过UserDao来进行，插入操作如下：
        getDaoSession().getUserDao().insert(user);
    }

    public void deleteUserData() {
        //查询id等于3的所有行并删除
        User user = getDaoSession().getUserDao().queryBuilder()
                .where(UserDao.Properties.Id.eq(3)).build().unique();
        if (user == null)
            ToastUtil.toastShort("用户不存在!");
        else
            getDaoSession().getUserDao().deleteByKey(user.getId());

    }

    public void deleteUserData2() {
        //查询id小于5的集合并删除
        List<User> userList = (List<User>) getDaoSession().getUserDao()
                .queryBuilder().where(UserDao.Properties.Id.lt(5)).build().list();
        for (User user : userList) {
            getDaoSession().getUserDao().delete(user);
        }

    }

    public void deleteAllUser() {
        //删除所有数据
        getDaoSession().getUserDao().deleteAll();
    }

    public void updateUser(User user) {
        //修改id为2的行
        //User user = new User((long) 2, "Nancy", 23, "female");
        getDaoSession().getUserDao().update(user);
    }

    public void updateUser2() {
        //查询id>= 3 且like ("%david%")
        User user = getDaoSession().getUserDao().queryBuilder()
                .where(UserDao.Properties.Id.ge(3), UserDao.Properties.Firstname.
                        like("%david%")).build().unique();
        if (user == null) {
            ToastUtil.toastShort("用户不存在!");
        } else {
            user.setFirstname("王五");
            getDaoSession().getUserDao().update(user);
        }
    }

    public static void queryUserData() {
        //查出所有数据
        List<User> users = getDaoSession().getUserDao().loadAll();
        ToastUtil.toastShort(users.get(0).getUser_id());
        //查询id为1~4之间的数，查出前2个
//        List<User> users = getDaoSession().getUserDao().queryBuilder()
//                .where(UserDao.Properties.Id.between(1, 4)).limit(2).build().list();

    }

}
