package tunnelers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import javafx.scene.image.Image;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Stepan
 */
public class Assets {
	
	public final static int TANK_BODY = 0, TANK_BODY_DIAG = 1,
							TANK_CANNON = 2, TANK_CANNON_DIAG = 3,
							PROJECTILE = 4, PROJECTILE_DIAG = 5;
	public final static int RESOURCE_COUNT = 6;
	private final static String[] RES_PATHS = {
		"resources/tank_body.png", "resources/tank_body_diag.png",
		"resources/tank_cannon.png", "resources/tank_cannon_diag.png", 
		"resources/projectile.png", "resources/projectile_diag.png", 
	};
	
	private static final Image[] resources;
	static{
		resources = new Image[RESOURCE_COUNT];
		for(int i = 0; i < RESOURCE_COUNT; i++){
			resources[i] = loadImage(i);
		}
	}
	
	private static Image loadImage(int type){
		File resFile = new File(RES_PATHS[type]);
		try{
			if(true)
				throw new IOException("Lel");
			String path = resFile.getCanonicalPath();
			return new Image("file://"+path);
		} catch (IOException e){
			InputStream is = createStdImage(type);
			System.out.println("Loading img from stream");
			return new Image(is);
		}
	}
	private static InputStream createStdImage(int type){
		String encoded = getEncodedImage(type);
		byte[] buf = Base64.getDecoder().decode(encoded);
		return new ByteArrayInputStream(buf);
	}
	private static String getEncodedImage(int type){
		switch(type){
			default: throw new IllegalArgumentException("Unrecognised resource const: "+type);
			case TANK_BODY:
				return "iVBORw0KGgoAAAANSUhEUgAAAAcAAAAHCAYAAADEUlfTAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3wsdDAYokOpMEQAAADJJREFUCNdjYMADGMvKyv5jk+jq6mJkZGBgYPj//z+KAkZGiDgLNkE4H59OvHbicywDALALEA21Z1wmAAAAAElFTkSuQmCC";
			case TANK_BODY_DIAG:
				return "iVBORw0KGgoAAAANSUhEUgAAAAcAAAAHCAYAAADEUlfTAAAABmJLR0QAdgB2AHZmy277AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3wsdDAcl90AB7QAAAC5JREFUCNd1jrENADAMg3I179OpUpo6TJYYcFUDsBKAqqv45BR3Pwkbsb2K9PYAhGU+3Zyl4S4AAAAASUVORK5CYII=";
			case TANK_CANNON:
				return  "iVBORw0KGgoAAAANSUhEUgAAAAcAAAAHCAYAAADEUlfTAAAABmJLR0QAdgB2AHZmy277AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3wsdDAgBTNv58wAAABZJREFUCNdjYKAJYETm/P///z8D7QEAlbgD/jsb/gcAAAAASUVORK5CYII=";
			case TANK_CANNON_DIAG:
				return "iVBORw0KGgoAAAANSUhEUgAAAAcAAAAHCAYAAADEUlfTAAAABmJLR0QAdgB2AHZmy277AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3wsdDAgSyGW4LQAAABRJREFUCNdjYCAV/P///z+VJKgLAOkoC/U1nz3zAAAAAElFTkSuQmCC";
			case PROJECTILE:
				return "iVBORw0KGgoAAAANSUhEUgAAAAEAAAADCAYAAABS3WWCAAAABmJLR0QAAAAAAAD5Q7t/AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3wsdDAQOcNGrbgAAABdJREFUCNdj+P///08mBgaG50wMDAyXADf1BbTazYReAAAAAElFTkSuQmCC";
			case PROJECTILE_DIAG:
				return "iVBORw0KGgoAAAANSUhEUgAAAAMAAAADCAYAAABWKLW/AAAABmJLR0QAAAAAAAD5Q7t/AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3wsdDAUbBBd+xAAAABdJREFUCNdjYICC/////4QxHsAYm2CyAOO4C4Pn0+mkAAAAAElFTkSuQmCC";
		}
	}
	
	public static Image getImage(int type){
		if(type < 0 || type >= RESOURCE_COUNT)
			throw new IllegalArgumentException("Unrecognised resource const: "+type);
		return resources[type];
	}
	
	
	public static void loadAssets(){
		
	}
	public static void printFileToBase64(File file){
		try {
			byte[] encoded = Base64.getEncoder().encode(FileUtils.readFileToByteArray(file));
			String s = new String(encoded);
			System.out.println(file.getName() + " " + s);
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
}
