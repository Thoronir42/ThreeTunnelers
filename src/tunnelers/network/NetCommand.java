package tunnelers.network;

import java.lang.reflect.Field;

/**
 *
 * @author Stepan
 */
public abstract class NetCommand {
    
	private static final Class[] RcvCmdClasses = {
		LeadCommand.class, ConnectionCommand.class,
		GameCommand.class, MessageCommand.class
		
	};
	private static byte RoomNumber;
	
	static NetCommand parse(String msg) throws NetworksException{
		String room = msg.substring(0, 1),
				MID = msg.substring(2, 3),
				mType= msg.substring(4, 7),
				body = msg.substring(8);
		/*
		if(cmdClass != null){
			try {
				NetCommand nc = (NetCommand)cmdClass.newInstance();
				nc.setParams(params);
				return nc;
				
			} catch (InstantiationException | IllegalAccessException ex) {
				throw new NetworksException(ex.getMessage());
			}
		}
		*/
		System.out.format("%s, %s, %s, %s%n", room, MID, mType, body);
		return null;				
	}
    
	private static Class getCmdGroup(char aspect){
		for(Class c : RcvCmdClasses){
			try{
				Field aspectField = c.getDeclaredField("CMD_RANGE");
				if(aspectField.get(null).equals(aspect)){
					return c;
				}
			} catch (NoSuchFieldException | IllegalAccessException e){
				System.err.println(e.getMessage());
			}
		}
		
		
		return null;
	}
	private static Class getCmdClass(char aspect, String command){
		
		Class group = getCmdGroup(aspect);
		Class[] receivableClasses = group.getDeclaredClasses();
		for(Class c : receivableClasses){
			if(c.getSimpleName().equals("Receivable")){ continue; }
			try{
				Field cmdField = c.getDeclaredField("CMD_TYPE");
				if(cmdField.get(null).equals(command)){
					return c;
				}
			} catch (NoSuchFieldException | IllegalAccessException e){
				System.err.println(e.toString()+" on "+c.getSimpleName());
			}
		}
		
		
		return null;
	}
	
	static byte LastMsgId = 0;
	final byte message_id;
	final short cmd_type;
    
	final Object[] params;
	
	
	public NetCommand(short cmd_type){
		this(cmd_type, new Object[0]);
	}
	
	public NetCommand(byte message_id, short cmd_type){
		this(message_id, cmd_type, new Object[0]);
	}
	
	public NetCommand(short cmd_type, Object[] params){
		this(++LastMsgId, cmd_type, params);
	}
	
	public NetCommand(byte message_id, short cmd_type, Object[] params){
		this.message_id = message_id;
		this.cmd_type = cmd_type;
		this.params = params;
	}
	
    public String getCommandCode() {
        StringBuilder sb = new StringBuilder();
		sb.append(bts(RoomNumber))
			.append(bts(this.message_id))
			.append(sts(this.cmd_type));
		sb.append("\0");
        return sb.toString();
    }

	
	private String bts(byte  n){ return Integer.toString(n & 0xFF, 16); }
	private String sts(short n){ return Integer.toString(n & 0xFFFF, 16); }
	
	private void strAsParams(String body) {
		
	}
	
}