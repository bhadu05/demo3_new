package com.ex.demo3.controller;

import com.ex.demo3.MaskedCustomer.MaskedCustomer;
import com.ex.demo3.entity.AuthRequest;
import com.ex.demo3.entity.Mobile;
import com.ex.demo3.history.count;
import com.ex.demo3.mysql.customer;
import com.ex.demo3.service.ServiceLayer;
import com.ex.demo3.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class Controller
{

    @Autowired
    RestTemplate restTemplate;
    @Autowired
     ServiceLayer service;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;
    //creating a get mapping that retrieves all the customer detail from the database
    @GetMapping("/customer")
    private List<customer> getAllCustomers() {
        return service.getAllCustomers();
    }


    @GetMapping("/callUserApi/getDeatils")
    private String  generateToken()
    {

        System.out.println("Calling secheduled method");
        AuthRequest authRequest = new AuthRequest("AshutoshRathore","Ashutosh@123");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
       // headers.add("Authorization");
       // Mobile number = new Mobile("9876543210");
        HttpEntity<AuthRequest> entity = new HttpEntity<AuthRequest>(authRequest,headers);



        String token= restTemplate.exchange(
                "http://localhost:8080/token", HttpMethod.POST, entity, String.class).getBody();
        headers.add("Authorization","Bearer "+token);
         return restTemplate.exchange(
                "http://localhost:8080/user?userID=1", HttpMethod.GET, entity, String.class).getBody();

        }

    @GetMapping(value = "/instagram/{userID}")
    private String  callFreeApi(@PathVariable String  userID)
    {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String > httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange( "https://www.instagram.com/"+userID+"/?__a=1", HttpMethod.GET, httpEntity, String.class).getBody();

    }
    @GetMapping("/pincode/{pincode}")
    private String findPincodeDetails(@PathVariable String pincode)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange("https://api.postalpincode.in/pincode/"+pincode,HttpMethod.GET,httpEntity,String.class).getBody();
    }
    @GetMapping("/sendOtp/{mobileNumber}")
    private String sendOtp(@PathVariable String mobileNumber)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
        HttpEntity<Mobile> httpEntity = new HttpEntity<Mobile>(new Mobile(mobileNumber),httpHeaders);

        return restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/auth/public/generateOTP",HttpMethod.POST,httpEntity,String.class).getBody();
    }
    @GetMapping("/findSlot/{pincode}")
    private String findSlots(@PathVariable String pincode)
    {
        String date="31-3-2021";
        /*Date date1 = new Date();
        String strDateFormat = "dd-MM-yyyy";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        date=dateFormat.format(date1);
*/


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
        HttpEntity<String> httpEntity = new HttpEntity<String >(httpHeaders);


        return restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+pincode+"&date="+date,HttpMethod.GET,httpEntity,String.class).getBody();
    }


    @GetMapping("/customer/name/{name}")
    public customer findByName(@PathVariable String name)
    {
        return service.findByName(name);
    }
    @GetMapping("/customquery")
    List<String>CustomQuery()
    {
        return service.CustomQuery();
    }

    @GetMapping("/customer/age/{age}")
    public customer findByAge(@PathVariable int age)
    {
        return service.findByAge(age);

    }
    @GetMapping("/setage")
    void SetAge()
    {
        service.SetAge();

    }
    @GetMapping("/count")
    public int FindCount()
    {
        return service.FindCount();
    }
    @GetMapping("/findagebetween")
    List<customer>FindAged(@RequestParam("from") int start,@RequestParam("to") int end)
    {
        return service.FindAgeBetween(start,end);
    }
    //creating a get mapping to find history of operations done.
    @GetMapping("/customer/history")
    public List<count>getHistory()
    {
        return service.getHistory();
    }

    //Creating a get mapping to find the pages.
    @GetMapping("/customer/pages/{pageno}/{size}")
    public Page<customer> getPages(@PathVariable int pageno, @PathVariable int size)
    {
        return service.getPages(pageno,size);
    }

   //creating a get mapping that retrieves the detail of a specific customer
    @GetMapping("/customer/{customerid}")
    @ResponseBody
    private MaskedCustomer getCustomer(@PathVariable("customerid") int customerid)
    {
        return service.getCustomerbyId(customerid);
    }
    //creating a delete mapping that deletes a specified customer
    @DeleteMapping("/customer/{customerid}")
    private void deleteCustomer(@PathVariable("customerid") int customerid)
    {
        service.deleteCustomer(customerid);
    }
    //creating post mapping that post the customer detail in the database
    @PostMapping("/customer")
    private String saveCustomer(@RequestBody customer customer)
    {
        return service.saveOrUpdate(customer);
     //   return customer.getName();
    }
    //creating put mapping that updates the customer detail
    @PutMapping("/customer")
    private customer updateCustomer(@RequestBody customer customer)
    {
        service.saveOrUpdate(customer);
        return customer;
    }

    @PostMapping("/token")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        }
        catch (Exception e)
        {
            throw new Exception("Invalid username or password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

    //Ignore commented part.

/*
@RestController
public class Controller {


  // @RequestMapping(path = "/hello/{id}", method = RequestMethod.GET)
    @Autowired
    public ServiceLayer services;
    @GetMapping("/hello/{id}")
   public int getBook(@PathVariable int id) {
       // code here
      return services.getresult(id);


   }*/
    /*
   @RequestMapping(method = RequestMethod.GET, value = "/hello")
   public String controllerMethod(@RequestParam int id) {
     String s="";
     for(int i=0;i<10;i++)
         s+=(i*id)+" ";

     return s;
   }
   /*
     */
  /*
   @RequestMapping(method = RequestMethod.GET, value = "/hello")
   public String controllerMethod(@RequestParam Map<String,String> customQuery) {

       System.out.println("customQuery = brand " + customQuery.containsKey("brand"));
       System.out.println("customQuery = limit " + customQuery.containsKey("limit"));
       System.out.println("customQuery = price " + customQuery.containsKey("price"));
       System.out.println("customQuery = other " + customQuery.containsKey("other"));
       System.out.println("customQuery = sort " + customQuery.containsKey("sort"));

       return customQuery.toString();
   }
   */

        public int  getmessage()
    {
         System.out.println("Hi you are you there");
         return 3;
    }
    public String putmessage()
    {
        return "Hi you are in put";
    }
}