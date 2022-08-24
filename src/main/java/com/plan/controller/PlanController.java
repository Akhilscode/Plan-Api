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

import com.plan.constant.AppConstant;
import com.plan.entity.Plan;
import com.plan.props.AppProperties;
import com.plan.service.PlanServiceImpl;

@RestController
public class PlanController {

	
	private PlanServiceImpl planService;
	
	Map<String, String> messages = null;
	
	public PlanController(PlanServiceImpl planService, AppProperties props) {
		   this.planService = planService;
		   this.messages = props.getMessages();
	}
	
	//Get all Catogeries
	@GetMapping("/getCatogeries")
	public ResponseEntity<Map<Integer, String>>  getAllCategories(){
		Map<Integer, String> catogeries = planService.getCatogeries();
		return new ResponseEntity<Map<Integer,String>>(catogeries, HttpStatus.OK);
	}
	
	//save the plan
	@PostMapping("/savePlan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
		String msg =AppConstant.EMPTY_STR;
		boolean savePlan = planService.savePlan(plan);
		if(savePlan)
			 msg = messages.get(AppConstant.PLAN_SAVE_SUCCESS);
		else 
			msg = messages.get(AppConstant.PLAN_SAVE_FAIL);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	//Get all plans
	@GetMapping("/allPlans")
	public ResponseEntity<List<Plan>> getAllPlans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<List<Plan>>(allPlans, HttpStatus.OK);
	}
	
	//Get plan from planId
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> getPlanByPlanId(@PathVariable Integer planId){
		Plan planById = planService.getPlanById(planId);
		return new ResponseEntity<Plan>(planById, HttpStatus.OK);
	}
	
	//Put plan method
	@PutMapping("/updatePlan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		String msg =AppConstant.EMPTY_STR;
		boolean  planUpdated = planService.updatePlan(plan);
		if(planUpdated)
			msg = messages.get(AppConstant.PLAN_UPDATE_SUCCESS);
		else
			msg = messages.get(AppConstant.PLAN_UPDATE_FAILED);
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
		
	}
	
	//Delete plan method
	@DeleteMapping("/deletePlan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		String msg =  AppConstant.EMPTY_STR;
		boolean planDeleted = planService.deletePlan(planId);
		if(planDeleted)
			msg = messages.get(AppConstant.PLAN_DELETE_SUCCESS);
		else
			msg = messages.get(AppConstant.PLAN_DELETE_FAILED);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	//updating the status
	@PutMapping("/planStatus/{planId}/{status}")
	public ResponseEntity<String> updateStatus(@PathVariable Integer planId, @PathVariable String status){
		String msg =AppConstant.EMPTY_STR;
		boolean activeStatus = planService.activateSw(planId, status);
		if(activeStatus)
			msg =messages.get(AppConstant.PLAN_STATUS_SUCCESS);
		else
			msg = messages.get(AppConstant.PLAN_STATUS_FAILED);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
