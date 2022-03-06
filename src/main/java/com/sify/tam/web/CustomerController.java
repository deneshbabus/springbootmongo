package com.sify.tam.web;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sify.tam.domain.Customer;
import com.sify.tam.repository.CustomerRepository;

@RequestMapping("/admin/orgs")
@Controller
@EnableAutoConfiguration
public class CustomerController {

	
	@Autowired CustomerRepository customerRepository;
	
	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Customer());
        return "org/createb";
    }
	
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Customer customer){
		System.out.println("customer :"+customer);
		customer.setCreatedDate(new Date());
		customer.setCreatedBy("Account Manager");
		customer.setStatus("Active");
		customerRepository.save(customer);
		return "redirect:/admin/orgs/";
	}
	
	@RequestMapping(produces = "text/html")	
	public String getCustomer(Model uiModel){
		uiModel.addAttribute("organizations", customerRepository.findAll());
		System.out.println("Customers"+customerRepository.findAll().toString());
		return "org/listb";
	}
	
	void populateEditForm(Model uiModel, Customer customer) {
        uiModel.addAttribute("customer", customer);
    }

}
