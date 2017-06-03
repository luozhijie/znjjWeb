package lzj.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lzj.DAO.BaseDao;
import lzj.DAO.PlanDao;
import lzj.entity.Plan;

public class PlanDaoImpl extends BaseDao implements PlanDao {

	@Override
	public int addPlan(Plan plan) {
		String sql = "INSERT INTO `znjj`.`plan` (`p_name`, `p_time`, `p_device_id`, `p_stat`) VALUES (?, ?, ?, ?);";
		return this.exceuteUpdate(sql,
				new Object[] { plan.getpName(), plan.getpTime(), plan.getpDeviceId(), plan.getpStat() });
	}

	@Override
	public int delPlan(Plan plan) {
		return this.exceuteUpdate("DELETE FROM `znjj`.`plan` WHERE `pid`=?;", new Object[] { plan.getPid() });
	}

	@Override
	public int updatePlan(Plan plan) {
		String sql = "UPDATE `znjj`.`plan` SET `pid`=?, `p_name`=?, `p_time`=?, `p_device_id`=?, `p_stat`=? WHERE `pid`=?;";
		return this.exceuteUpdate(sql, new Object[] { plan.getPid(), plan.getpName(), plan.getpTime(),
				plan.getpDeviceId(), plan.getpStat(), plan.getPid() });
	}

	@Override
	public List<Plan> findPlanByDeviceId(int did) {
		List<Plan> planList = new ArrayList<>();
		String sql = "SELECT * FROM znjj.plan where p_device_id = ?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[]{did});
		try {
			while(rs.next()){
				Plan plan = new Plan(rs.getInt("pid"), rs.getString("p_name"), rs.getString("p_time"), rs.getInt("p_device_id"), rs.getInt("p_stat"));
				planList.add(plan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planList.isEmpty() ? null : planList;
	}

	@Override
	public List<Plan> findPlanByDeviceIdList(List<Integer> deviceIdList) {
		List<Plan> planList = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM znjj.plan where ");
		int tag = 0;
		for (int i = 0 ; i< deviceIdList.size() ; i++) {
			if(tag == 0){
				sb.append("p_device_id = ? ");
			}else{
				sb.append("or p_device_id = ? ");
			}
		}
		ResultSet rs = this.execeuteQuary(sb.toString(), deviceIdList.toArray());
		try {
			while(rs.next()){
				Plan plan = new Plan(rs.getInt("pid"), rs.getString("p_name"), rs.getString("p_time"), rs.getInt("p_device_id"), rs.getInt("p_stat"));
				planList.add(plan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planList;
	}

}
