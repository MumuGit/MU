package com.mu.example.myapplication.core.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mu.example.myapplication.greendao.DaoMaster;
import com.mu.example.myapplication.greendao.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by mu on 2018/2/26.
 */

public class GreenDaoOpenHelper extends DaoMaster.OpenHelper {
    public GreenDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, UserDao.class);
    }


}
