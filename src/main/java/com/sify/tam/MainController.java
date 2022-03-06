package com.sify.tam;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sify.tam.domain.Customer;
import com.sify.tam.repository.CustomerRepository;



@Controller
@EnableAutoConfiguration
public class MainController {

	@Autowired
	CustomerRepository customerRepository;
	
	@RequestMapping("/")
	public String index(){
		return "login";
	}
		
	
	
	@RequestMapping("/orgs")
	@ResponseBody
	public String getCustomer(Model uiModel){		

		// save a couple of customers
		
		Customer cust = new Customer();
		cust.setCustomerName("Om Namah Shivaya");
		cust.setCreatedDate(new Date());
		cust.setCreatedBy("Admin");
		cust.setStatus("Active");		
		
		customerRepository.save(cust);
		
		Customer cust1 = new Customer();
		cust1.setCustomerName("Sify Technology");
		cust1.setCreatedDate(new Date());
		cust1.setCreatedBy("Sify Admin");
		cust1.setStatus("Active");		
		
		customerRepository.save(cust1);
		cust1.setCustomerName("Sify Software");
		customerRepository.save(cust1);
		
		String customerList="List of Organizations";
		
		for (Customer customer : customerRepository.findAll()) {
			customerList += "   "+customer.getCustomerName();
		}			
		System.out.println("customerList :"+customerList);
		
		uiModel.addAttribute("organizations", customerRepository.findAll());
		
		return customerList;
	}
}
