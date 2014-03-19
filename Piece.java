//Brian Pomerantz

import java.util.*;

abstract class Piece {
	private byte value, rank, file;
	private char symbol;
	private boolean side;
	protected ArrayList<Piece> moves, attack;//, defend;
	
	public Piece(boolean si, char sy, byte v, byte r, byte f) {
		side = si;
		symbol = sy;
		value = v;
		rank = r;
		file = f;
		moves = new ArrayList<Piece>();
		attack = new ArrayList<Piece>();
		//defend = new ArrayList<Piece>();
	}
	
	protected byte getRank() {
		return rank;
	}
	
	protected byte getFile() {
		return file;
	}
	
	protected void setPosition(byte r, byte f) {
		rank = r;
		file = f;
	}
		
	
	protected byte getValue() {
		return value;
	}
	
	protected void setValue(byte v) {
		value = v;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public String toString() {
		if (this instanceof Null) {
			return "  ";
		}
		
		if (side) {
			return "W" + symbol;
		}
		
		return "B" + symbol;
	}
	
	public boolean getSide() {
		return side;
	}
	
	abstract void update(Board b);
	
	abstract ArrayList<Piece> getMoves();
	
	abstract ArrayList<Piece> getAttack();
	
	//abstract ArrayList<Piece> getDefend();
}