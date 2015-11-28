package generic;

/**
 *
 * @author Stepan
 * @param <Type> type specificator
 */
public class BackPasser<Type> implements Runnable{

    private Type content;

    public Type get() {
        return content;
    }
    
	@Override
    public void run() {
        throw new UnsupportedOperationException("Command hasn't been passed: no uzuý");
    }
    
    public void passCommand(Type cmd){
        this.content = cmd;
        this.run();
    }
    
}
