package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;

@RestController
public class OrderController {

	@Autowired
	private CustomerRepository custRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/placeOrder")
	public Customer placeOrder(@RequestBody OrderRequest orderReq) {
		return custRepository.save(orderReq.getCustomer());		
	}
	
	@GetMapping("/getOrderDetails")
	public List<Customer> getOrderDetails(){
		return custRepository.findAll();
	}
	
	@GetMapping("/getOrderResponse")
    public List<OrderResponse> getOrderResponse(){
        return custRepository.getOrderReponse();
    }
}
