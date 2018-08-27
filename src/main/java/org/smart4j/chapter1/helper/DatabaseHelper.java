package org.smart4j.chapter1.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter1.service.CustomerService;
import org.smart4j.chapter1.util.CollectionUtil;
import org.smart4j.chapter1.util.PropsUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DatabaseHelper {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private static final BasicDataSource DATA_SOURCE;

    private static final QueryRunner QUERY_RUNNER;

    private static final ThreadLocal<Connection> CONNECTION_HOLDER;


//    // 驱动
//    private static final String DRIVER;
//    // 访问数据库url
//    private static final String URL;
//    // 帐号
//    private static final String USERNAME;
//    // 密码
//    private static final String PASSWORD;

    static{
        CONNECTION_HOLDER = new ThreadLocal<Connection>();

        QUERY_RUNNER = new QueryRunner();

        Properties pops = PropsUtil.loadProps("config.properties");
        String driver = pops.getProperty("jdbc.driver");
        String url = pops.getProperty("jdbc.url");
        String username = pops.getProperty("jdbc.username");
        String password = pops.getProperty("jdbc.password");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);

//        try{
//            Class.forName(DRIVER);
//        }catch(ClassNotFoundException e){
//            logger.error("can not load jdbc driver");
//        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if(conn == null){
        try{
            conn = DATA_SOURCE.getConnection();
//            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch(SQLException e){
            logger.error("get connection failure",e);
            throw new RuntimeException(e);
        }finally{
            CONNECTION_HOLDER.set(conn);
        }
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("close connection failure",e);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    /**
     * 查询实体列表
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object... params){
        List<T> entityList;
        try{
            Connection conn = getConnection();
            entityList = QUERY_RUNNER.query(conn,sql,new BeanListHandler<T>(entityClass),params);
        }catch(SQLException e){
            logger.error("query entity list failure",e);
            throw new RuntimeException(e);
        }finally{
            closeConnection();
        }
        return entityList;
    }


    /**
     * 查询单个实体
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass,String sql,Object... params){
        T entity;

        try {
            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn,sql,new BeanHandler<T>(entityClass),params);
        } catch (SQLException e) {
            logger.error("query entity failure",e);
            throw new RuntimeException(e);
        }finally{
            closeConnection();
        }
        return entity;
    }

    /**
     * 执行更新语句（包括 update insert delete）
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql,Object... params){
        int rows = 0;

        try {
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(conn,sql,params);
        } catch (SQLException e) {
            logger.error("execute update failure",e);
            throw new RuntimeException(e);
        }finally{
            closeConnection();
        }
        return  rows;
    }

    /**
     * 插入实体
     * @param entityClass
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            logger.error("can not insert entity: filedMap is empty");
            return false;
        }

        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName:fieldMap.keySet()
             ) {
            columns.append(fieldName).append(",");
            values.append("?,");
        }
        columns.replace(columns.lastIndexOf(","),columns.length(),")");
        values.replace(values.lastIndexOf(","),values.length(),")");
        sql += columns + "VALUES" + values;
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql,params) == 1;

    }

    /**
     * 更新实体
     * @param entityClass
     * @param id
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T> entityClass,String id,Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            logger.error("can not update entity: fieldMap is empty");
            return false;
        }

        String sql = "UPDATE "+getTableName(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName:fieldMap.keySet()
             ) {
            columns.append(fieldName).append("=?,");
        }
        sql += columns.substring(0,columns.lastIndexOf(","))+" WHERE id=?";
        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();
        return executeUpdate(sql,params) == 1;

    }


    /**
     * 删除实体
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> entityClass,String id){
        String sql = "DELETE FROM" + getTableName(entityClass) + "WHERE id = ?";
        return executeUpdate(sql,id) == 1;
    }

    /**
     * 获取表名
     * @param entityClass
     * @return
     */
    private static String getTableName(Class<?> entityClass){
        return entityClass.getSimpleName();
    }


}
