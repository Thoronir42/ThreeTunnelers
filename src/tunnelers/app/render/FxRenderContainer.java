package tunnelers.app.render;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import tunnelers.app.assets.Assets;
import tunnelers.app.render.colors.FxDefaultColorScheme;
import tunnelers.core.engine.EngineUserInterface;
import tunnelers.core.gameRoom.WarZoneRules;
import tunnelers.core.model.entities.IntPoint;
import tunnelers.core.model.entities.Projectile;
import tunnelers.core.model.entities.Tank;
import tunnelers.core.model.map.Map;
import tunnelers.core.player.Player;

public class FxRenderContainer {

	private final EngineUserInterface engine;
	private final FxDefaultColorScheme colorScheme;

	private Dimension2D blockSize;

	protected final MapRenderer mapRenderer;
	protected final AssetsRenderer assetsRenderer;
	protected final AfterFX afterFx;

	public FxRenderContainer(EngineUserInterface engine, FxDefaultColorScheme colorScheme, Assets assets) {
		this.colorScheme = colorScheme;
		this.engine = engine;

		this.mapRenderer = new MapRenderer(colorScheme);
		this.assetsRenderer = new AssetsRenderer(assets, colorScheme);
		this.afterFx = new AfterFX(colorScheme);
	}

	public void prepareGame(Map map, Player[] players) {
		this.mapRenderer.setMap(map);
		this.assetsRenderer.initGameAssets(players);
	}

	public void setBlockSize(Dimension2D blockSize) {
		this.blockSize = blockSize;
		this.mapRenderer.setBlockSize(blockSize);
		this.assetsRenderer.setBlockSize(blockSize);
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

	public AfterFX getAfterFX() {
		return this.afterFx;
	}

	public Projectile[] getProjectiles() {
		return this.engine.getGameRoom().getWarZone().getProjectiles();
	}

	public Tank[] getTanks() {
		return this.engine.getGameRoom().getWarZone().getTanks();
	}

	public WarZoneRules getWarzoneRules() {
		// todo: gaze upon all those possible null pointer exceptions
		return this.engine.getGameRoom().getWarZone().getRules();
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
