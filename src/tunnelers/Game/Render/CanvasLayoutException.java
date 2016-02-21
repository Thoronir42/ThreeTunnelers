package tunnelers.Game.Render;

/**
 *
 * @author Stepan
 */
public class CanvasLayoutException extends IllegalStateException {

	public CanvasLayoutException(int playerAmount) {
		super("Couldn't create layout for " + playerAmount + " players");
	}
}
