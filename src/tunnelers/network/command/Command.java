package tunnelers.network.command;

/**
 *
 * @author Stepan
 */
public class Command {

	/**
	 * @unused
	 */
	protected final byte id;
	protected final CommandType type;
	protected String data;

	public Command(CommandType type, byte id) {
		this(type, id, "");
	}

	public Command(CommandType type, byte id, String data) {
		this.id = id;
		this.type = type;
		this.data = data;
	}

	public CommandType getType() {
		return CommandType.LeadApprove;
	}

	public byte getId() {
		return id;
	}

	/**
	 * Returns length of current data
	 * @return 
	 */
	public short getLength() {
		return (short) data.length();
	}

	protected void append(byte n) {
		this.data += String.format("%02X", n);
	}

	protected void append(short n) {
		this.data += String.format("%04X", n);
	}

	protected void append(int n) {
		this.data += String.format("%08X", n);
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
