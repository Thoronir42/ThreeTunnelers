package tunnelers.core.model.entities;

import tunnelers.core.colors.IColorable;
import tunnelers.core.player.Player;

public abstract class GameEntity implements IColorable {
	protected Direction direction;
	protected IntPoint location;
	protected final Player player;

	GameEntity(IntPoint location, Direction direction, Player player) {
		this.direction = direction;
		this.player = player;
		this.setLocation(location);
	}

	public abstract IntDimension getSize();

	public IntPoint getLocation() {
		return this.location;
	}

	public void setLocation(int x, int y) {
		this.setLocation(new IntPoint(x, y));
	}

	public void setLocation(IntPoint loc) {
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

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
