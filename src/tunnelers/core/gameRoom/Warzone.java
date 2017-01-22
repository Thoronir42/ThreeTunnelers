package tunnelers.core.gameRoom;

import tunnelers.core.model.entities.Direction;
import tunnelers.core.model.entities.IntPoint;
import tunnelers.core.model.entities.Projectile;
import tunnelers.core.model.entities.Tank;
import tunnelers.core.model.map.Map;
import tunnelers.core.player.Player;

/**
 *
 * @author Stepan
 */
public class Warzone {
	private final WarzoneRules rules;
	private Map map;

	private Tank[] tanks;
	private Projectile[] projectiles;

	public Warzone(WarzoneRules rules) {
		this.rules = rules;
	}
	protected void setMap(Map map){
		this.map = map;
	}
	protected void setTanks(Tank[] tanks){
		this.tanks = tanks;
		this.projectiles = new Projectile[tanks.length * this.rules.getProjectilesPerTank()];
	}

	public WarzoneRules getRules(){
		return this.rules;
	}
	
	public Projectile[] getProjectiles() {
		return projectiles;
	}

	public Map getMap() {
		return this.map;
	}

	public void setProjectile(int id, IntPoint location, Direction direction, Player player) {
		this.projectiles[id] = new Projectile(location, direction, player);
	}

	public void removeProjectile(int id) {
		this.projectiles[id] = null;
	}

	public Tank getTank(int roomId) {
		if (roomId < 1 || roomId > tanks.length) {
			throw new GameRoomIndexException(1, tanks.length, roomId);
		}
		
		return this.tanks[roomId - 1];
		
	}

	public Tank[] getTanks() {
		return this.tanks;
	}
}
