package lzj.DAO;

import java.util.List;

import lzj.entity.Plan;

public interface PlanDao {
	public int addPlan(Plan plan);

	public int delPlan(Plan plan);

	public int updatePlan(Plan plan);

	public List<Plan> findPlanByDeviceId(int did);

	public List<Plan> findPlanByDeviceIdList(List<String> deviceIdList);
	
	public Plan findPlanByPid(int pid);
}
