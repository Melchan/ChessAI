#ChessBoard Char[] array 

##Index 0 - 63:
Coordinates for chess pieces on board. 

##Index 64:
Indicator how many moves has been made.
From move count indicator we can determine is it black or white turn.

##Index 65:
Will tell if it is possible to make enPassant move on this turn.
This indicator is here for the draw rules. 

#Chess Piece explanation.

##Three basic groups
**empty square:** 0

**white pieces:** 1 - 12

**black pieces:** 13 - 24

##Information about piece status. 

**even numbers:** chess piece has not moved during game.

**odd numbers:** chess piece has moved during game.

##Pieces by number.

###White

**white pawn:** 1 and 2

**white rook:** 3 and 4

**white knight:** 5 and 6

**white bishop:** 7 and 8

**white queen:** 9 and 10

**white king:** 11 and 12

###Black

**black pawn:** 13 and 14

**black rook:** 15 and 16

**black knight:** 17 and 18

**black bishop:** 19 and 20

**black queen:** 21 and 22

**black king:** 23 and 24
