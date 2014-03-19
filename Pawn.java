//Brian Pomerantz

import java.util.*;

public class Pawn extends Piece {
	public Pawn(boolean si, byte r, byte f) {
		super(si, 'P', (byte) 1, r, f);
	}
	
	//Updates legal move list
	public void update(Board bd) {
		moves.clear();
		attack.clear();
		//defend.clear();
		
		Piece[][] b = bd.getBoard();
		//int pieceValue, a = 0, d = 0, m;
		byte r = getRank();
		byte c = getFile();

		if (getSide()) {
				
			if (bd.isValid(r + 1, c)) {
				if (b[r + 1][c] instanceof Null) {
					moves.add(b[r + 1][c]);
				}
			}
				
			if (bd.isValid(r + 1, c + 1)) {
				if (b[r + 1][c + 1] instanceof Null) {}
				
				else if (b[r + 1][c + 1].getSide() != getSide()) {
					moves.add(b[r + 1][c + 1]);
					attack.add(b[r + 1][c + 1]);
				}
				
				//else if (b[r + 1][c + 1].getSide() == getSide()) {
				//	defend.add(b[r + 1][c + 1]);
				//}
			}
				
			if (bd.isValid(r + 1, c - 1)) {
				if (b[r + 1][c - 1] instanceof Null) {}
				
				else if (b[r + 1][c - 1].getSide() != getSide()) {
					moves.add(b[r + 1][c - 1]);
					attack.add(b[r + 1][c - 1]);
				}
				
				//else if (b[r + 1][c - 1].getSide() == getSide()) {
				//	defend.add(b[r + 1][c - 1]);
				//}
			}
			
			
			if (r == 1) {
				if (b[r + 2][c] instanceof Null && b[r + 1][c] instanceof Null) {
					moves.add(b[r + 2][c]);
				}
			}
			
			//for (int i = 0; i < attack.size(); i++) {
			//	a += attack.get(i).getValue();
			//	
			//	if (attack.get(i) instanceof King) {
			//		a += 10;
			//	}
			//}
			
			//for (int i = 0; i < defend.size(); i++) {
			//	d += defend.get(i).getValue();
			//}
		}	
			
		else {
				
			if (bd.isValid(r - 1, c)) {
				if (b[r - 1][c] instanceof Null) {
					moves.add(b[r - 1][c]);
				}
			}
				
			if (bd.isValid(r - 1, c + 1)) {
				if (b[r - 1][c + 1] instanceof Null) {}
				
				else if (b[r - 1][c + 1].getSide() != getSide()) {
					moves.add(b[r - 1][c + 1]);
					attack.add(b[r - 1][c + 1]);
				}
					
				//else if (b[r - 1][c + 1].getSide() == getSide()) {
				//	defend.add(b[r - 1][c + 1]);
				//}
			}
				
			if (bd.isValid(r - 1, c - 1)) {
				if (b[r - 1][c - 1] instanceof Null) {}
				
				else if (b[r - 1][c - 1].getSide() != getSide()) {
					moves.add(b[r - 1][c - 1]);
					attack.add(b[r - 1][c - 1]);
				}
				
				//else if (b[r - 1][c - 1].getSide() == getSide()) {
				//	defend.add(b[r - 1][c - 1]);
				//}
			}
				
				
			if (r == 6) {
				if (b[r - 2][c] instanceof Null && b[r - 1][c] instanceof Null) {
					moves.add(b[r - 2][c]);
				}
			}
					
			//for (int i = 0; i < attack.size(); i++) {
			//	a += attack.get(i).getValue();
			//	
			//	if (attack.get(i) instanceof King) {
			//		a += 10;
			//	}
			//}
				
			//for (int i = 0; i < defend.size(); i++) {
			//	d += defend.get(i).getValue();
			//}
		}
			
								
		//m = moves.size();
		//pieceValue = (45/getValue()) * (getValue() + a + d + m);
		//pieceValue = getValue();
			
		//return pieceValue;
	}	
		
		
	public ArrayList<Piece> getMoves() {
		//update();
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