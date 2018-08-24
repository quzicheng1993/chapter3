package org.smart4j.chapter2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter1.model.Customer;
import org.smart4j.chapter1.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String id = "";
        Customer customer = customerService.getCustomer(id);

    }

    @Test
    public void createCustomerTest() throws Exception{
        Map<String,Object> fileMap = new HashMap<String,Object>();
        fileMap.put("name","customer100");
        fileMap.put("telephone","13026367522");
        fileMap.put("email","13026367522@qq.com");
        boolean result = customerService.createCustomer(fileMap);
        Assert.assertTrue(result);

    }

    @Test
    public void updateCustomerTest() throws Exception{
        String id = "";
        Map<String,Object> fieldMap = new HashMap<String,Object>();
        fieldMap.put("name","customer002");
        boolean result = customerService.updateCustomer("",fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerTest() throws Exception{
        String id = "";
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);

    }

}
