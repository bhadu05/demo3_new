package com.ex.demo3.service;

import com.ex.demo3.MaskedCustomer.MaskedCustomer;
import com.ex.demo3.Pagination.PaginationRepository;
import com.ex.demo3.history.count;
import com.ex.demo3.history.historyRepository;
import com.ex.demo3.mysql.customer;
import com.ex.demo3.mysql.customerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceLayer
{
    @Autowired
    customerRepository customerRepository;
    @Autowired
    historyRepository historyRepository;
    @Autowired
    PaginationRepository paginationRepository;
    //getting all customers record by using the method findaAll() of CrudRepository
    public List<customer> getAllCustomers()
    {
        List<customer> customer = new ArrayList<customer>();
        customerRepository.findAll().forEach(customer1 -> customer.add(customer1));
/*        //For history purpose.
        postHistory("GET");
        handleHistory();*/
        if(historyRepository.findByID("GET")!=null)
            historyRepository.save(new count("GET",historyRepository.findByID("GET").getCount()+1));
        else
            historyRepository.save(new count("GET"));
        return customer;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public MaskedCustomer getCustomerbyId(int id)
    {
/*        //For history purpose.
        postHistory("GET");
        handleHistory();*/
        if(historyRepository.findByID("GET")!=null)
            historyRepository.save(new count("GET",historyRepository.findByID("GET").getCount()+1));
        else
            historyRepository.save(new count("GET"));

        MaskedCustomer maskedCustomer = new MaskedCustomer(customerRepository.findByID(id).getName(),customerRepository.findById(id).get().getage());
        return maskedCustomer;

    }
    //saving a specific record by using the method save() of CrudRepository
    public String  saveOrUpdate(customer customer)
    {
/*        //For history purpose.
        postHistory("POST");
        handleHistory();*/
        if(historyRepository.findByID("POST")!=null)
            historyRepository.save(new count("POST",historyRepository.findByID("POST").getCount()+1));
        else
            historyRepository.save(new count("POST"));

        if(customerRepository.findByAge(customer.getage())==null) {
            customerRepository.save(customer);
            return "Updated successfully";
        }
        else
            return "Customer already exits";


    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void deleteCustomer(int id)
    {
        customerRepository.deleteById(id);
        //For history purpose.
/*        postHistory("DELETE");
        handleHistory();*/
        if(historyRepository.findByID("DELETE")!=null)
            historyRepository.save(new count("DELETE",historyRepository.findByID("DELETE").getCount()+1));
        else
            historyRepository.save(new count("DELETE"));
    }
    //updating a record
    public void updatecustomer(customer customer, int bookid)
    {
        customerRepository.save(customer);
/*        //For history purpose.
        postHistory("PUT");
        handleHistory();*/
        if(historyRepository.findByID("PUT")!=null)
            historyRepository.save(new count("PUT",historyRepository.findByID("PUT").getCount()+1));
        else
            historyRepository.save(new count("PUT"));
    }
    public List<count> getHistory()
    {
        if(historyRepository.findByID("GET")!=null)
            historyRepository.save(new count("GET",historyRepository.findByID("GET").getCount()+1));
        else
            historyRepository.save(new count("GET"));


        List<count> count = new ArrayList<count>();
        historyRepository.findAll().forEach(count1 -> count.add(count1));
        return count;

    }
    /*public void postHistory(String type)
    {
        List<count> all = new ArrayList<>();
        boolean get=false;
        boolean post=false;
        boolean put=false;
        boolean delete=false;
        historyRepository.findAll().forEach(count1->all.add(count1));
        for(count temp:all)
        {
            if(temp.getType()=="GET") {
                get = true;
            }
            if(temp.getType()=="POST") {
                post = true;
            }
            if(temp.getType()=="PUT") {
                put = true;
            }
            if(temp.getType()=="DELETE") {
                delete = true;
            }
        }
        count obj = new count();
        switch (type)
        {

            case "GET":
                if(!get)
                {
                    obj.setType("GET");
                    historyRepository.save(obj);
                }
                break;
            case "POST":
                if(!post)
                {
                    obj.setType("POST");
                    historyRepository.save(obj);
                }
                break;

            case "PUT":
                if(!put)
                {
                    obj.setType("Put");
                    historyRepository.save(obj);
                }
                break;
            case "DELETE":
                if(!delete)
                {
                    obj.setType("DELETE");
                    historyRepository.save(obj);
                }
                break;
        }


    }
    public void handleHistory()
    {
        List<count> all = new ArrayList<>();
        historyRepository.findAll().forEach(count1->all.add(count1));
        for(count temp:all)
        {
            if(temp.getType()=="GET")
            {
                temp.setCount(temp.getCount()+1);
            }
            else if (temp.getType()=="POST")
            {
                temp.setCount(temp.getCount()+1);
            }
            else if (temp.getType()=="PUT")
            {
                temp.setCount(temp.getCount()+1);
            }
            else if (temp.getType()=="DELETE")
            {
                temp.setCount(temp.getCount()+1);
            }
        }

    }*/

    public customer findByName(String name) {
        return customerRepository.findByName(name);
    }

    public customer findByAge(int age) {
        return customerRepository.findByAge(age);
    }

    public List<String> CustomQuery() {
        return customerRepository.CustomQuery();
    }

    public void SetAge() {
        customerRepository.SetAge();
    }

    public int FindCount() {
       return customerRepository.FindTotal();
    }

    public List<customer> FindAgeBetween(int start,int end) {
        return customerRepository.FindAgedBetween(start,end);
    }

    public Page<customer> getPages(int pageno, int size) {
        Pageable firstPageWithTwoElements = PageRequest.of(pageno,size, Sort.by("age").descending().and(Sort.by("name")));
        //return PaginationRepository.findAll(firstPageWithTwoElements);
        Page<customer> allProducts = paginationRepository.findAll(firstPageWithTwoElements);
        return allProducts;
    }

    public void fillUser()
    {

    }


}
