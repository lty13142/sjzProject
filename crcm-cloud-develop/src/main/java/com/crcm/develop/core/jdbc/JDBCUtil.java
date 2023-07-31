package com.crcm.develop.core.jdbc;

import com.crcm.develop.common.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JDBCUtil
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/6
 **/
public class JDBCUtil {
    private volatile static JDBCUtil instance = null;

    // 私有化构造方法
    private JDBCUtil() {

    }

    /**
     * 单例模式
     * @return
     */
    public static JDBCUtil getInstance() {
        if (instance == null) {
            synchronized (JDBCUtil.class) {
                if (instance == null) {
                    instance = new JDBCUtil();
                }
            }

        }
        return instance;
    }


    public Connection getConnection(String url, String username, String password, String driverClassName) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        return DriverManager.getConnection(url, username, password);
    }


    public <T> T selectOne(Connection conn, String sql, Object obj, Class resultType) throws SQLException {
        return (T) this.selectList(conn, sql, obj, resultType).get(0);
    }

    //查询多条
    public <T> List<T> selectList(Connection conn, String sql, Object obj, Class resultType) throws SQLException {
        List<T> list = new ArrayList<>();
        PreparedStatement pstate = null;
        ResultSet rs = null;

        try {
            //1、解析SQL
            SQLAndKey sqlAndKey = SQLHandler.parseSQL(sql);
            //2、获取连接对象
//            ConnectionPool pool = ConnectionPool.getInstance();
//            Connection conn = pool.getConnection();
            //3、获取状态参数
            pstate = conn.prepareStatement(sqlAndKey.getSQL());
            //4、把SQL和问号拼接在一起
            if (obj != null) {
                SQLHandler.handleParameter(pstate, obj, sqlAndKey.getKeyList());
            }
            //5、执行操作
            rs = pstate.executeQuery();
            //6、处理结果
            while (rs.next()) {
                //通过handleResult获取到
                T result = (T) SQLHandler.handleResult(rs, resultType);
                list.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstate != null) {
                    pstate.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    public void update(Connection conn, String sql, Object obj) throws SQLException {
        PreparedStatement pstate = null;
        try {
            //1、解析sql语句
            SQLAndKey sqlAndKey = SQLHandler.parseSQL(sql);
            //2、获取连接池对象
//            ConnectionPool pool = ConnectionPool.getInstance();
//            //3、获取连接对象
//            Connection conn = pool.getConnection();
            //4、获取状态参数
            pstate = conn.prepareStatement(sqlAndKey.getSQL());
            //5、将SQL语句和问号值组装完整，调用handler的方法将obj对象替代掉？
            if (obj != null) {
                SQLHandler.handleParameter(pstate, obj, sqlAndKey.getKeyList());
            }
            pstate.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (pstate != null) {
                    pstate.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    //删
    public void delete(Connection conn, String sql, Object obj) throws SQLException {
        this.update(conn, sql, obj);
    }

    //增
    public void insert(Connection conn, String sql, Object obj) throws SQLException {
        this.update(conn, sql, obj);
    }

    /**
     * 根据类型获取驱动
     * @param type
     * @return
     */
    public String getDriverByType(String type) {
        if (StringUtils.equals(type,"mysql")) {
            return "com.mysql.cj.jdbc.Driver";
        }
        return null;
    }
}
