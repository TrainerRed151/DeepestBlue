//Brian Pomerantz

import java.util.*;

public class King extends Piece {
	private boolean castled, canCKS, canCQS;
	
	public King(boolean si, byte r, byte f) {
		super(si, 'K', (byte) 0, r, f);
		castled = false;
		canCKS = true;
		canCQS = true;
	}
	
	public King(boolean si, byte r, byte f, boolean cKS, boolean cQS) {
		super(si, 'K', (byte) 0, r, f);
		castled = false;
		canCKS = cKS;
		canCQS = cQS;
	}
	
	//Updates legal move list
	public void update(Board bd) {
		moves.clear();
		attack.clear();
		//defend.clear();
		
		Piece[][] b = bd.getBoard();
		//int pieceValue = 0, a = 0, d = 0, m;
		byte r = getRank();
		byte c = getFile();
					
		if (bd.isValid(r + 1, c)) {
			if (b[r + 1][c] instanceof Null) {
				moves.add(b[r + 1][c]);
			}
			
			else if (b[r + 1][c].getSide() != getSide()) {
				moves.add(b[r + 1][c]);
				attack.add(b[r + 1][c]);
			}
			
			//else {
			//	defend.add(b[r + 1][c]);
			//}
		}
		
		if (bd.isValid(r - 1, c)) {
			if (b[r - 1][c] instanceof Null) {
				moves.add(b[r - 1][c]);
			}
			
			else if (b[r - 1][c].getSide() != getSide()) {
				moves.add(b[r - 1][c]);
				attack.add(b[r - 1][c]);
			}
			
			//else {
			//	defend.add(b[r - 1][c]);
			//}
		}
		
		if (bd.isValid(r, c + 1)) {
			if (b[r][c + 1] instanceof Null) {
				moves.add(b[r][c + 1]);
			}
			
			else if (b[r][c + 1].getSide() != getSide()) {
				moves.add(b[r][c + 1]);
				attack.add(b[r][c + 1]);
			}
			
			//else {
			//	defend.add(b[r][c + 1]);
			//}
		}
		
		if (bd.isValid(r, c - 1)) {
			if (b[r][c - 1] instanceof Null) {
				moves.add(b[r][c - 1]);
			}
			
			else if (b[r][c - 1].getSide() != getSide()) {
				moves.add(b[r][c - 1]);
				attack.add(b[r][c - 1]);
			}
			
			//else {
			//	defend.add(b[r][c - 1]);
			//}
		}
		
		if (bd.isValid(r + 1, c + 1)) {
			if (b[r + 1][c + 1] instanceof Null) {
				moves.add(b[r + 1][c + 1]);
			}
			
			else if (b[r + 1][c + 1].getSide() != getSide()) {
				moves.add(b[r + 1][c + 1]);
				attack.add(b[r + 1][c + 1]);
			}
			
			//else {
			//	defend.add(b[r + 1][c + 1]);
			//}
		}
		
		if (bd.isValid(r - 1, c + 1)) {
			if (b[r - 1][c + 1] instanceof Null) {
				moves.add(b[r - 1][c + 1]);
			}
			
			else if (b[r - 1][c + 1].getSide() != getSide()) {
				moves.add(b[r - 1][c + 1]);
				attack.add(b[r - 1][c + 1]);
			}
			
			//else {
			//	defend.add(b[r - 1][c + 1]);
			//}
		}
		
		if (bd.isValid(r + 1, c - 1)) {
			if (b[r + 1][c - 1] instanceof Null) {
				moves.add(b[r + 1][c - 1]);
			}
			
			else if (b[r + 1][c - 1].getSide() != getSide()) {
				moves.add(b[r + 1][c - 1]);
				attack.add(b[r + 1][c - 1]);
			}
			
			//else {
			//	defend.add(b[r + 1][c - 1]);
			//}
		}
		
		if (bd.isValid(r - 1, c - 1)) {
			if (b[r - 1][c - 1] instanceof Null) {
				moves.add(b[r - 1][c - 1]);
			}
			
			else if (b[r - 1][c - 1].getSide() != getSide()) {
				moves.add(b[r - 1][c - 1]);
				attack.add(b[r - 1][c - 1]);
			}
			
			//else {
			//	defend.add(b[r - 1][c - 1]);
			//}
		}

		//m = moves.size();
		//pieceValue = (45/getValue()) * (getValue() + a + d + m);
		
		//return pieceValue;
	}
	
	public ArrayList<Piece> getMoves() {
		return moves;
	}
	
	public void castle() {
		setValue((byte) (getValue() + 1));
		canCQS = false;
		canCKS = false;
	}
	
	public void cantC(int KQ) {
		if (KQ == 1) {
			canCKS = false;
		}
		
		else if (KQ == -1) {
			canCQS = false;
		}
		
		else {
			canCKS = canCQS = false;
		}
	}
	
	public boolean getCKS() {
		return canCKS;
	}
	
	public boolean getCQS() {
		return canCQS;
	}
	
	public ArrayList<Piece> getAttack() {
		return attack;
	}
//	
//	public ArrayList<Piece> getDefend() {
//		return defend;
//	}
}