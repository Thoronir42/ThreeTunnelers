package tunnelers.core.model.entities;

import tunnelers.core.player.Player;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import tunnelers.app.render.colors.Colorable;

/**
 *
 * @author Stepan
 */
public abstract class GameEntity  implements Colorable{
	protected Direction direction;
	protected Point2D location;
	protected Player player;

	public GameEntity(Direction direction, Point2D location, Player player) {
		this.direction = direction;
		this.location = location;
		this.player = player;
	}

	public abstract int update();

	public abstract Dimension2D getSize();

	public Point2D getLocation() {
		return this.location;
	}

	public void setLocation(Point2D loc) {
		this.location = loc;
	}

	public double getX() {
		return this.location.getX();
	}

	public double getY() {
		return this.location.getY();
	}

	public double getWidth() {
		return this.getSize().getWidth();
	}

	public double getHeight() {
		return this.getSize().getHeight();
	}

	public double getTopBorder() {
		return this.getY() - (this.getHeight() - 1) / 2;
	}

	public double getLeftBorder() {
		return this.getX() - (this.getWidth() - 1) / 2;
	}

	public double getRightBorder() {
		return this.getX() + (this.getWidth() - 1) / 2;
	}

	public double getBottomBorder() {
		return this.getY() + (this.getHeight() - 1) / 2;
	}

	public Player getPlayer() {
		return this.player;
	}

	@Override
	public int getColor() {
		return this.player.getColor();
	}
	
	public int getPlayerId(){
		return this.player.getID();
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
}
