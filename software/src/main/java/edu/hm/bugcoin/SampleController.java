package edu.hm.bugcoin;

import edu.hm.bugcoin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*@Controller
public class SampleController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/test")
	@ResponseBody
	@Transactional(readOnly = true)
	public String helloWorld() {
		return customerService.getCustomer("bart90").getCity();
	}

}*/
