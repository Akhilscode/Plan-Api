package com.plan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plan.entity.Plan;
import com.plan.service.PlanServiceImpl;

@RestController
public class PlanController {

	@Autowired
	private PlanServiceImpl planService;
	
	@GetMapping("/getCatogeries")
	public ResponseEntity<Map<Integer, String>>  getAllCategories(){
		Map<Integer, String> catogeries = planService.getCatogeries();
		return new ResponseEntity<Map<Integer,String>>(catogeries, HttpStatus.OK);
	}
	
	@PostMapping("/savePlan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
		String msg = null;
		boolean savePlan = planService.savePlan(plan);
		if(savePlan)
			 msg = "Plan saved";
		else 
			msg = "Plan not saved";
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/allPlans")
	public ResponseEntity<List<Plan>> getAllPlans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<List<Plan>>(allPlans, HttpStatus.OK);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> getPlanByPlanId(@PathVariable Integer planId){
		Plan planById = planService.getPlanById(planId);
		return new ResponseEntity<Plan>(planById, HttpStatus.OK);
	}
	
	@PutMapping("/updatePlan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		String msg = null;
		boolean  planUpdated = planService.updatePlan(plan);
		if(planUpdated)
			msg = "Plan  updated";
		else
			msg = "Plan Not updated";
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deletePlan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		String msg = null;
		boolean planDeleted = planService.deletePlan(planId);
		if(planDeleted)
			msg = "Plan Deleted";
		else
			msg = "Plan not Deleted";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/planStatus/{planId}/{status}")
	public ResponseEntity<String> updateStatus(@PathVariable Integer planId, @PathVariable String status){
		String msg = null;
		boolean activeStatus = planService.activateSw(planId, status);
		if(activeStatus)
			msg = "Status updated";
		else
			msg = "Status not updated";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
