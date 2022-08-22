package com.plan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plan.entity.Plan;
import com.plan.entity.PlanCategory;
import com.plan.repository.PlanCategoryRespository;
import com.plan.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanCategoryRespository pcrepo;
	
	@Autowired
	private PlanRepository planrepo;

	@Override
	public Map<Integer, String> getCatogeries() {
		Map<Integer, String> hashmap = new HashMap<Integer, String>();
		List<PlanCategory> planCategories = pcrepo.findAll();
		planCategories.forEach(planCategory ->{
			hashmap.put(planCategory.getCategoryId(), planCategory.getCategoryName());
		});
		return hashmap;
	}

	@Override
	public boolean savePlan(Plan plan) {
	   Plan savedPlan = planrepo.save(plan);
		return savedPlan.getPlanId()!=null;
	}

	@Override
	public List<Plan> getAllPlans() {
	   List<Plan> planList = planrepo.findAll();
		return planList;
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> findById = planrepo.findById(planId);
	     return findById.isPresent() ? findById.get() : null;
	}

	//in update plan if plan already there it will update plan otherwise it will create plan
	@Override
	public boolean updatePlan( Plan plan) {
		Plan savePlan = planrepo.save(plan);
		return savePlan.getPlanId()!=null;
	}

	@Override
	public boolean deletePlan(Integer planId) {
		boolean status = false;
		try {
			planrepo.deleteById(planId);
			status = true;	
		}catch (Exception e) {
			e.getMessage();
		}
		return status;
	}

	@Override
	public boolean activateSw(Integer planId, String sw) {
		Optional<Plan> plan = planrepo.findById(planId);
		if(plan.isPresent()) {
			Plan plan1 = plan.get();
			plan1.setActiveSw(sw);
			planrepo.save(plan1);
			return true;
		}
		return false;
	}

}
