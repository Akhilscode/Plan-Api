package com.plan.service;

import java.util.List;
import java.util.Map;

import com.plan.entity.Plan;


public interface PlanService {
	
	public Map<Integer, String>   getCatogeries();
	
	public  boolean savePlan(Plan plan);
	
	public List<Plan> getAllPlans();
	
	public Plan getPlanById(Integer planId);
	
	public boolean updatePlan(Plan plan);
	
	public boolean deletePlan(Integer planId);
	
	public boolean activateSw(Integer planId, String sw);

}
