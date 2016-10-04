package tunnelers.Menu.Settings.Controls;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author Stepan
 */
public class PortTextField extends TextField{ 

	public static final int MAX_PORT = 65535;
	public static final int MIN_PORT = 1025;

	public final SimpleIntegerProperty Port;
	
	public void setPort(int port){
		this.Port.set(port);
	}
	
	public int getPort(){
		return this.Port.get();
	}

	public PortTextField() {
		this(MIN_PORT);
	}

	public PortTextField(int serverPort) {
		this.Port = createPortProperty();
		this.Port.set(serverPort);

		this.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			if (!newValue.matches("\\d*")) {
				setText(newValue.replaceAll("[^\\d]", ""));
				return;
			}
			if(newValue.length() < 4){
				setText(MIN_PORT + "");
				return;
			}
			
			try {
				int n = Integer.parseInt(newValue);
				Port.set(n);
			} catch (NumberFormatException ex) {
				System.err.println("Format exception: " + ex);
				setText(Port.get() + "");
			}
		});
	}

	private SimpleIntegerProperty createPortProperty() {
		SimpleIntegerProperty port = new SimpleIntegerProperty();

		port.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if (newValue.intValue() > MAX_PORT) {
				port.set(MAX_PORT);
				return;
			}
			if (newValue.intValue() < MIN_PORT) {
				port.set((oldValue.intValue() < MIN_PORT) ? MIN_PORT : MIN_PORT);
				return;
			}
			setText(newValue.intValue() + "");
		});

		return port;
	}
}