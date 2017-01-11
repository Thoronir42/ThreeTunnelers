package tunnelers.core.gameRoom;

public class GameRoomFacade implements IGameRoomInfo {

	private final short id;
	private final byte maxPlayers;
	private final byte curPlayers;
	private final byte flags;
	private final byte difficulty;

	public GameRoomFacade(short id, byte maxPlayers, byte curPlayers, byte difficulty, byte flags) {
		this.id = id;
		this.maxPlayers = maxPlayers;
		this.curPlayers = curPlayers;
		this.flags = flags;
		this.difficulty = difficulty;
	}

	@Override
	public short getId() {
		return id;
	}

	@Override
	public byte getMaxPlayers() {
		return maxPlayers;
	}

	@Override
	public byte getCurrentPlayers() {
		return curPlayers;
	}

	@Override
	public byte getFlags() {
		return flags;
	}

	@Override
	public byte getDifficulty() {
		return difficulty;
	}

	@Override
	public boolean isFull() {
		return this.curPlayers >= this.maxPlayers;
	}

	@Override
	public boolean isSpectacable() {
		return (this.flags & FLAG_SPECTATABLE) > 0;
	}

	@Override
	public boolean isRunning() {
		return (this.flags & FLAG_SPECTATABLE) > 0;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + this.id;
		hash = 53 * hash + this.maxPlayers;
		hash = 53 * hash + this.curPlayers;
		hash = 53 * hash + this.flags;
		hash = 53 * hash + this.difficulty;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final GameRoomFacade other = (GameRoomFacade) obj;
		if (this.id != other.id) {
			return false;
		}
		if (this.maxPlayers != other.maxPlayers) {
			return false;
		}
		if (this.curPlayers != other.curPlayers) {
			return false;
		}
		if (this.flags != other.flags) {
			return false;
		}
		if (this.difficulty != other.difficulty) {
			return false;
		}
		return true;
	}
}