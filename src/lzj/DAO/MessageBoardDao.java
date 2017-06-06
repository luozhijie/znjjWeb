package lzj.DAO;

import java.util.List;

import lzj.entity.MessageBoard;

public interface MessageBoardDao {
	public int addMessageBorad(MessageBoard messageBoard);

	public int delMessageBorad(int mid);

	public int updateMessageBorad(MessageBoard messageBoard);
	
	public MessageBoard findMessageBoardByMid(int mid);
	
	public List<MessageBoard> findMessageBoardByUid(int uid);
	
}
