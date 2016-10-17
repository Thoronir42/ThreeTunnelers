package tunnelers.core.model.entities;

import tunnelers.core.model.player.APlayer;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 *
 * @author Stepan
 */
public class Projectile extends GameEntity {

	private static final Dimension2D SHOT_HORIZONTAL = new Dimension2D(3, 1),
			SHOT_DIAGONAL = new Dimension2D(3, 3);

	public Projectile(Point2D location, Direction direction, APlayer player) {
		super(direction, location, player);
	}

	@Override
	public int update() {
		return 0;
	}

	@Override
	public Dimension2D getSize() {
		return this.direction.isDiagonal() ? SHOT_DIAGONAL : SHOT_HORIZONTAL;
	}
}
