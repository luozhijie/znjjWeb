package lzj.DaoImpl;

import java.util.List;

import lzj.DAO.BaseDao;
import lzj.DAO.MessageBoardDao;
import lzj.entity.MessageBoard;

public class MessageBoradDaoImpl extends BaseDao implements MessageBoardDao {

	@Override
	public int addMessageBorad(MessageBoard messageBoard) {
		String sql = "";
		
		return 0;
	}

	@Override
	public int delMessageBorad(MessageBoard messageBoard) {
		return 0;
	}

	@Override
	public int updateMessageBorad(MessageBoard messageBoard) {
		return 0;
	}

	@Override
	public MessageBoard findMessageBoardByMid(int mid) {
		return null;
	}

	@Override
	public List<MessageBoard> findMessageBoardByUid(int uid) {
		return null;
	}

	@Override
	public List<MessageBoard> findMessageBoradByFid(int fid) {
		return null;
	}

}
