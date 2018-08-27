package org.smart4j.chapter1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter1.helper.DatabaseHelper;
import org.smart4j.chapter1.model.Customer;

import java.util.List;
import java.util.Map;

public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

//    // 驱动
//    private static final String DRIVER;
//    // 访问数据库url
//    private static final String URL;
//    // 帐号
//    private static final String USERNAME;
//    // 密码
//    private static final String PASSWORD;
//
//    static{
//        Properties pops = PropsUtil.loadProps("config.properties");
//        DRIVER = pops.getProperty("jdbc.driver");
//        URL = pops.getProperty("jdbc.url");
//        USERNAME = pops.getProperty("jdbc.username");
//        PASSWORD = pops.getProperty("jdbc.password");
//
//        try{
//            Class.forName(DRIVER);
//        }catch(ClassNotFoundException e){
//            logger.error("can not load jdbc driver");
//        }
//    }
//
//    /**
//     * 获取数据库连接
//     * @return
//     */
//    public static Connection getConnection(){
//        Connection conn = null;
//        try{
//            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//        }catch(SQLException e){
//            logger.error("get connection failure",e);
//        }
//        return conn;
//    }


    /**
     * 获取客户列表
     * @param
     * @return
     */
    public List<Customer> getCustomerList(){

        String sql = "select * from customer";
        List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class,sql);
        return customerList;
//        Connection conn = null;
//        try{
//            List<Customer> customerList = new ArrayList<Customer>();
//            String sql = "select * from customer";
//            conn = DatabaseHelper.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()){
//                Customer customer = new Customer();
//                customer.setId(rs.getString("id"));
//                customer.setName(rs.getString("name"));
//                customer.setTelephone(rs.getString("telephone"));
//                customer.setEmail(rs.getString("email"));
//                customer.setRemark(rs.getString("remark"));
//                customer.setSex(rs.getBoolean("sex"));
//                customerList.add(customer);
//            }
//            return customerList;
//        }catch(Exception e){
//            logger.error("execute sql failure",e);
//        }finally{
//            DatabaseHelper.closeConnection();
//            if(conn != null){
//                try{
//                    conn.close();
//                }catch(SQLException e){
//                    logger.error("close connection failure",e);
//                }
//            }
//        }

//        return null;
    }

    /**
     * 获取客户
     * @param id
     * @return
     */
    public Customer getCustomer(String id){
        String sql = "select * from customer where id = ?";
        Customer customer = DatabaseHelper.queryEntity(Customer.class,sql,id);
        return customer;
    }

    /**
     * 创建客户
     * @param fieldMap
     * @return
     */
    public boolean createCustomer(Map<String,Object> fieldMap){

        return DatabaseHelper.insertEntity(Customer.class,fieldMap);

    }

    /**
     * 更新客户
     * @param id
     * @param fieldMap
     * @return
     */
    public boolean updateCustomer(String id,Map<String,Object> fieldMap){
        return DatabaseHelper.updateEntity(Customer.class,id,fieldMap);

    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    public boolean deleteCustomer(String id){
        return DatabaseHelper.deleteEntity(Customer.class,id);
    }

}
