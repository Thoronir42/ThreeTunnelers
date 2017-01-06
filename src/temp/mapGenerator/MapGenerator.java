package temp.mapGenerator;

import generic.RNG;
import tunnelers.core.model.map.Map;

/**
 *
 * @author Stepan
 */
public class MapGenerator {

	private IMapGeneratorStep[] steps;

	public MapGenerator() {
		RNG mapRng = new RNG(119);
		steps = new IMapGeneratorStep[]{
			new MapRockifier(mapRng, 6),
			new MapBasePlanter(mapRng),
		};
	}

	public Map mockMap(int chunkSize, int width, int height, int playerCount) {
		Map map = new Map(chunkSize, width, height, playerCount);
		for(IMapGeneratorStep step : this.steps){
			step.applyOn(map);
		}
		return map;
	}
}