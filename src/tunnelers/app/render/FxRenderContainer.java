package tunnelers.app.render;

import java.util.Collection;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import tunnelers.app.assets.Assets;
import tunnelers.app.render.colors.FxDefaultColorScheme;
import tunnelers.core.engine.Engine;
import tunnelers.core.model.entities.IntPoint;
import tunnelers.core.model.entities.Projectile;
import tunnelers.core.model.entities.Tank;
import tunnelers.core.player.Player;

/**
 *
 * @author Stepan
 */
public class FxRenderContainer {

	private final Engine engine;
	private final FxDefaultColorScheme colorScheme;

	private Dimension2D blockSize;

	protected final MapRenderer mapRenderer;
	protected final AssetsRenderer assetsRenderer;
	protected final AfterFX afterFx;

	public FxRenderContainer(Engine engine, FxDefaultColorScheme colorScheme, Assets assets) {
		this.colorScheme = colorScheme;
		this.engine = engine;

		this.mapRenderer = new MapRenderer(colorScheme);
		this.assetsRenderer = new AssetsRenderer(colorScheme, assets);
		this.afterFx = new AfterFX(colorScheme);
	}

	public void setBlockSize(Dimension2D blockSize) {
		this.blockSize = blockSize;
		this.mapRenderer.setBlockSize(blockSize);
		this.assetsRenderer.setBlockSize(blockSize);
	}

	public void setGraphicsContext(GraphicsContext context) {
		this.mapRenderer.setGraphicsContext(context);
		this.assetsRenderer.setGraphicsContext(context);
	}

	public FxDefaultColorScheme getColorScheme() {
		return this.colorScheme;
	}

	public MapRenderer getMapRenderer() {
		return this.mapRenderer;
	}

	public AssetsRenderer getAssetsRenderer() {
		return this.assetsRenderer;
	}
	
	public AfterFX getAfterFX(){
		return this.afterFx;
	}

	public Player[] getPlayers() {
		return this.engine.getGameRoom().getPlayers();
	}

	public Projectile[] getProjectiles() {
		return this.engine.getWarzone().getProjectiles();
	}

	public Tank[] getTanks() {
		return this.engine.getWarzone().getTanks();
	}

	public void offsetBlocks(GraphicsContext gc, double x, double y) {
		gc.translate(
				x * this.blockSize.getWidth(),
				y * this.blockSize.getHeight()
		);
	}

	public void offsetBlocks(GraphicsContext gc, IntPoint point) {
		this.offsetBlocks(gc, point.getX(), point.getY());
	}
}
