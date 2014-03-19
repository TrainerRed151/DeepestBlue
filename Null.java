//Brian Pomerantz

import java.util.*;

//Used to fill empty square of Piece array
public class Null extends Piece {
	public Null(byte r, byte f) {
		super(false, ' ', (byte) 0, r, f);
	}
	
	public void update(Board b) {
		//return getValue();
	}
	
	public ArrayList<Piece> getMoves() {
		return moves;
	}
	
	public ArrayList<Piece> getAttack() {
		return attack;
	}
//	
//	public ArrayList<Piece> getDefend() {
//		return defend;
//	}
}