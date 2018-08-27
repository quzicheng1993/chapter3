package org.smart4j.chapter2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter1.model.Customer;
import org.smart4j.chapter1.service.CustomerService;

import java.util.*;

public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest(){
        customerService = new CustomerService();
    }

    @Before
    public void init(){

    }

    @Test
    public void getCustomerListTest() throws Exception{
        List<Customer> customerList = customerService.getCustomerList();
        for (Customer customer:customerList) {
            System.out.println(customer);
        }
        Assert.assertEquals(2,customerList.size());

    }

    @Test
    public void getCustomerTest() throws Exception{
        String id = "asdasdasdasdad";
        Customer customer = customerService.getCustomer(id);
        System.out.println(customer);
    }

    @Test
    public void createCustomerTest() throws Exception{
        Map<String,Object> fileMap = new HashMap<String,Object>();
        fileMap.put("id",getUUID());
        fileMap.put("name","customer100");
        fileMap.put("telephone","13026367522");
        fileMap.put("email","13026367522@qq.com");
        fileMap.put("remark","飞哥来也");
        fileMap.put("daytime",new Date());
        fileMap.put("sex",true);
        boolean result = customerService.createCustomer(fileMap);
        Assert.assertTrue(result);

    }

    @Test
    public void updateCustomerTest() throws Exception{
        String id = "00ba91e4ef9d4887946f2bcc7abfe984";
        Map<String,Object> fieldMap = new HashMap<String,Object>();
        fieldMap.put("name","customer002");
        boolean result = customerService.updateCustomer(id,fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerTest() throws Exception{
        String id = "";
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);

    }

    private static String getUUID(){
        return  UUID.randomUUID().toString().replaceAll("-", "");
    }


    @Test
    public void uuid(){
        String name = "22-222-22";
        String char1 = name.substring(name.indexOf('-')+1,name.lastIndexOf('-'));
        System.out.print(char1);

//        StringBuilder values = new StringBuilder("(?,?,?,?,?,?,?,");
//        StringBuilder name =  values.replace(values.lastIndexOf(","),values.length(),")");
//        System.out.print(name);
//        StringBuilder aaa = new StringBuilder("aaaaa");
//        System.out.println(aaa);
//        System.out.println(aaa.length());
//        System.out.println(aaa.lastIndexOf("a"));
//        System.out.print(aaa.replace(2,4,"b"));

    }

}
