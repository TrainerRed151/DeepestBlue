//Brian Pomerantz

import java.util.*;

public class AI {
	private boolean side;
	private int depth;
	
	public AI(boolean si, int dep) {
		side = si;
		depth = dep;
	}
	
	//Calculates the best move using a MinMax algorithm with ALphaBeta Pruning
	//Input: ArrayList of legal moves
	//Output: The particular legal move which maximizes (or minimizes) the engine's score
	public String move(Board bd) {
		ArrayList<String> moves = bd.legalMoves(side);
		
		//Stalemate
		if (moves.size() == 0) {
			return '\u00AB' + "-" + '\u00AB';
		}

		byte alpha = Byte.MIN_VALUE, beta = Byte.MAX_VALUE;
		
		byte best = -1, val, bestVal;
		if (side) {bestVal = Byte.MIN_VALUE;}
		else {bestVal = Byte.MAX_VALUE;}
		
		for (byte i = 0; i < moves.size(); i++) {
			Board nBD = new Board(bd.getBoard());
			val = alphabeta(nBD.move(moves.get(i), side), depth, alpha, beta, !side);
			//System.out.println(moves.get(i) + ": " + val);
			
			if (side && val > bestVal) {
				bestVal = val;
				best = i;
				alpha = (byte) Math.max(alpha, val);
			}
			
			if (!side && val < bestVal) {
				bestVal = val;
				best = i;
				beta = (byte) Math.min(beta, val);
			}
			
			if (beta <= alpha) {
    			break;
    		}
		}
		
		//Resign if opponent has forced mate within depth
		//Alternatively, the engine could chose a random move
		if (best == -1) {
			if (side){return "0-1";}
			else{return "1-0";}
		}
		
		return moves.get(best);
	}
	
	//Old MinMax function.  Superseded by AlphaBeta function
//	private byte minmax(Board bd, int dep, boolean maxPlayer) {
//		byte bestValue, val;
//		
//		if (dep == 0) {
//        	return bd.score();
//		}
//		
//    	if (maxPlayer) {
//    		bestValue = Byte.MIN_VALUE;
//    		
//    		for (String s : bd.legalMoves(maxPlayer)) {
//    			Board nBD = new Board(bd.getBoard());
//    			val = minmax(nBD.move(s, maxPlayer), dep - 1, false);
//    			bestValue = (byte) Math.max(bestValue, val);
//    		}
//			
//			return bestValue;
//		}
//    	
//    	else {
//			bestValue = Byte.MAX_VALUE;
//			
//			for (String s : bd.legalMoves(!maxPlayer)) {
//				Board nBD = new Board(bd.getBoard());
//				val = minmax(nBD.move(s, maxPlayer), dep - 1, true);
//    			bestValue = (byte) Math.min(bestValue, val);
//    		}
//			
//			return bestValue;
//		}
//	}
	
	//AlphaBeta function
	//Recursively determines best possible move assuming perfect play within given depth
	//Discontinues branch of search tree if necessarily worse than previously calculated branch
	//More efficient than MinMax
	private byte alphabeta(Board bd, int dep, byte alpha, byte beta, boolean maxPlayer) {
		if (dep == 0) {
        	return bd.score();
		}
		
    	if (maxPlayer) {
    		ArrayList<String> list = bd.legalMoves(true);
    		
    		//Checkmate	
    		if (bd.checkmate(false)) {
    			return Byte.MIN_VALUE;
    		}
    		
    		//Stalemate
    		if (list.size() == 0) {
    			return 0;
    		}
    		
    		for (String s : list) {
    			Board nBD = new Board(bd.getBoard());
    			alpha = (byte) Math.max(alpha, alphabeta(nBD.move(s, true), dep-1, alpha, beta, false));
    			
    			if (beta <= alpha) {
    				break;
    			}
    		}
			
			return alpha;
		}
    	
    	else {
    		ArrayList<String> list = bd.legalMoves(false);
    		
    		//Checkmate
    		if (bd.checkmate(true)) {
    			return Byte.MAX_VALUE;
    		}
    		
    		//Stalemate
    		if (list.size() == 0) {
    			return 0;
    		}
    		
			for (String s : list) {
				Board nBD = new Board(bd.getBoard());
    			beta = (byte) Math.min(beta, alphabeta(nBD.move(s, false), dep-1, alpha, beta, true));
    			
    			if (beta <= alpha) {
    				break;
    			}
    		}
			
			return beta;
		}
	}
}
