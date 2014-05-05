DeepestBlue
===========

Chess Engine

Deepest Blue is a serial version of a chess engine which uses a MinMax algorithm with AlphaBeta pruning.  It evluates the value of a given position by considering material alone.

To run the program, exacute the following command:

java runChess

To run tests, enter "T" at the main menu.  To have the computer evaluate a single position, enter "F".  Once in the FEN option, enter the desired half-move depth.  Finally, enter the board setup using FEN notation (see http://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation for more information).  The FEN used here is slightly different from the standard notation.  First, begin the notation string with a "W" for white or "B" for black to move.  Do not include the total move number, the number of half moves since the last pawn move, or whether the last move was a pawn.  Lastly, replace the KQkq castle indication with 1s and 0s.  For example, the starting position would be enter as:

Wrnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR 1111

See the tests for more examples of the appropriate FEN notation.
