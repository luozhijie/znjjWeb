package lzj.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lzj.DAO.BaseDao;
import lzj.DAO.FamilyGroupDao;
import lzj.DAO.MessageBoardDao;
import lzj.entity.FamilyGroup;
import lzj.entity.MessageBoard;

public class MessageBoradDaoImpl extends BaseDao implements MessageBoardDao {

	@Override
	public int addMessageBorad(MessageBoard messageBoard) {
		String sql = "INSERT INTO `znjj`.`message_board` (`uid`, `content`) VALUES (?, ?);";
		return this.exceuteUpdate(sql, new Object[] { messageBoard.getUid(), messageBoard.getContent() });
	}

	@Override
	public int delMessageBorad(int mid) {
		String sql = "DELETE FROM `znjj`.`message_board` WHERE `mid`=?;";
		return this.exceuteUpdate(sql, new Object[] { mid });
	}

	@Override
	public int updateMessageBorad(MessageBoard messageBoard) {
		String sql = "UPDATE `znjj`.`message_board` SET `uid`=?, `content`=?, `time`=?, `isread`=? WHERE `mid`=?;";
		return this.exceuteUpdate(sql, new Object[] { messageBoard.getUid(), messageBoard.getContent(),
				messageBoard.getDate(), messageBoard.getIsRead(), messageBoard.getMid() });
	}

	@Override
	public MessageBoard findMessageBoardByMid(int mid) {
		String sql = "SELECT * FROM znjj.message_view where mid = ?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { mid });
		MessageBoard messageBoard = null;
		try {
			if (rs.next()) {
				messageBoard = new MessageBoard();
				messageBoard.setContent(rs.getString("content"));
				messageBoard.setDate(rs.getString("time"));
				messageBoard.setIconName(rs.getString("user_icon"));
				messageBoard.setIsRead(rs.getInt("isread"));
				messageBoard.setMid(rs.getInt("mid"));
				messageBoard.setUid(rs.getInt("uid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageBoard;
	}

	@Override
	public List<MessageBoard> findMessageBoardByUid(int uid) {
		StringBuffer sb = new StringBuffer("SELECT * FROM znjj.message_view where ");
		int tag = 0;
		List<Integer> pList = new ArrayList<>();
		FamilyGroupDao familyGroupDao = new FamilyGroupDaoImpl();
		List<FamilyGroup> familyGroupList = familyGroupDao.findAllFamilyGroupByUid(uid);
		System.out.println(familyGroupList.toString());
		List<MessageBoard> massageBoardList = new ArrayList<>();
		for (FamilyGroup familyGroup : familyGroupList) {

			if (familyGroup.getUser1() != null) {
				if (tag == 0) {
					sb.append("uid = ? ");
				} else {
					sb.append("or uid = ? ");
				}
				pList.add(familyGroup.getUser1().getUserId());
				tag++;
			}
			if (familyGroup.getUser2() != null) {
				if (tag == 0) {
					sb.append("uid = ? ");
				} else {
					sb.append("or uid = ? ");
				}
				pList.add(familyGroup.getUser2().getUserId());
				tag++;
			}
			if (familyGroup.getUser3() != null) {
				if (tag == 0) {
					sb.append("uid = ? ");
				} else {
					sb.append("or uid = ? ");
				}
				pList.add(familyGroup.getUser3().getUserId());
				tag++;
			}
			if (familyGroup.getUser4() != null) {
				if (tag == 0) {
					sb.append("uid = ? ");
				} else {
					sb.append("or uid = ? ");
				}
				pList.add(familyGroup.getUser4().getUserId());
				tag++;
			}
			if (familyGroup.getUser5() != null) {
				if (tag == 0) {
					sb.append("uid = ? ");
				} else {
					sb.append("or uid = ? ");
				}
				pList.add(familyGroup.getUser5().getUserId());
				tag++;
			}
			ResultSet rs = this.execeuteQuary(sb.toString(), pList.toArray());
			try {
				while (rs.next()) {
					MessageBoard messageBoard = new MessageBoard();
					messageBoard.setContent(rs.getString("content"));
					messageBoard.setDate(rs.getString("time"));
					messageBoard.setIsRead(rs.getInt("isread"));
					messageBoard.setMid(rs.getInt("mid"));
					messageBoard.setUid(rs.getInt("uid"));
					messageBoard.setIconName(rs.getString("user_icon"));
					massageBoardList.add(messageBoard);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return massageBoardList;
	}

}
