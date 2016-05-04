package tunnelers.Menu.ServerList.GameRoomView;

import java.util.Collection;

/**
 *
 * @author Stepan
 */
public interface IGameRoomView{

	void add(GRTVItem gr);

	void addAll(Collection<GRTVItem> items);

	void clearItems();

	GRTVItem getSelectedItem();
	
}
