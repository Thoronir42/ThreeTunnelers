package tunnelers.network.command;

public enum CommandType {
	Undefined(0),
	//    LEAD
	LeadApprove(1),
	LeadDeny(2),
	LeadStillThere(3),
	LeadBadFormat(4),
	//    CONNECTION
	ConFetchGameList(10),
	ConCreateGame(11),
	ConJoinGame(12),
	ConReconnect(13),
	ConDisconnect(20),
	ConGamesList(25),
	ConGamesListEnd(26),
	//    MESSAGE
	MsgPlain(40),
	MsgRcon(45),
	//    ROOM CONTROLS
	RoomWrongPhase(110),
	RoomPlayerWhoIs(111),
	RoomPlayerIntroduce(112),
	RoomSetColor(113),
	RoomKickPlayer(118),
	RoomPlayerLeft(119),
	RoomSetLeader(120),
	RoomStartGame(125),
	RoomGameStarted(126),
	// GAME-MAP CONTROLS
	GameChunkRequest(140),
	GameChunkData(141),
	GameVerifyChunk(142),
	//    GAME-ENTITY CONTROLS
	GameControlsSet(201),
	GameTankRequest(210),
	GameTankInfo(211),
	GameProjAdd(220),
	GameProjRem(221),;

	private final short value;

	private CommandType(int v) {
		this((short) v);
	}

	private CommandType(short v) {
		this.value = v;
	}

	public short value() {
		return value;
	}
}
