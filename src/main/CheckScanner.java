package main;

import pieces.Piece;

public class CheckScanner {
    Board board;

    public CheckScanner(Board board) {
        this.board = board;
    }

    public boolean ischecked(Move move) {

        Piece king = board.findking(move.piece.isWhite);
        assert king != null;

        int kingcol = king.col;
        int kingrow = king.row;

        if (board.selectedpiece != null && board.selectedpiece.name.equals("King")) {
            kingcol = move.newcol;
            kingrow = move.newrow;
        }

        return  hitbyrook(move.newcol, move.newrow, king, kingcol, kingrow, 0, 1) ||
                hitbyrook(move.newcol, move.newrow, king, kingcol, kingrow, 1, 0) ||
                hitbyrook(move.newcol, move.newrow, king, kingcol, kingrow, 0, -1) ||
                hitbyrook(move.newcol, move.newrow, king, kingcol, kingrow, -1, 0) ||

                hitbybishop(move.newcol, move.newrow, king, kingcol, kingrow, -1, -1) ||
                hitbybishop(move.newcol, move.newrow, king, kingcol, kingrow, 1, -1) ||
                hitbybishop(move.newcol, move.newrow, king, kingcol, kingrow, 1, 1) ||
                hitbybishop(move.newcol, move.newrow, king, kingcol, kingrow, -1, 1) ||

                hitbyknight(move.newcol, move.newrow, king, kingcol, kingrow) ||
                hitbypawn(move.newcol, move.newrow, king, kingcol, kingrow) ||
                hitbyking(king, kingcol, kingrow);
    }

    private boolean hitbyrook(int col, int row, Piece king, int kingcol, int kingrow, int colval, int rowval) {
        for (int i = 1; i<8; i++) {
            if (kingcol + (i * colval) == col && kingrow + (i * rowval) == row) {
                break;
            }

            Piece piece = board.getpiece(kingcol + (i * colval), kingrow + (i * rowval));

            if (piece != null && piece != board.selectedpiece) {
                if (!board.sameteam(piece,king) && (piece.name.equals("Rook") || piece.name.equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitbybishop(int col, int row, Piece king, int kingcol, int kingrow, int colval, int rowval) {
        for (int i = 1; i<8; i++) {
            if (kingcol - (i * colval) == col && kingrow - (i * rowval) == row) {
                break;
            }

            Piece piece = board.getpiece(kingcol - (i * colval), kingrow - (i * rowval));

            if (piece != null && piece != board.selectedpiece) {
                if (!board.sameteam(piece,king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitbyknight(int col, int row, Piece king, int kingcol, int kingrow) {
        return  checkknight(board.getpiece(kingcol - 1, kingrow -2), king, col, row) ||
                checkknight(board.getpiece(kingcol + 1, kingrow -2), king, col, row) ||
                checkknight(board.getpiece(kingcol + 2, kingrow -1), king, col, row) ||
                checkknight(board.getpiece(kingcol + 2, kingrow +1), king, col, row) ||
                checkknight(board.getpiece(kingcol + 1, kingrow +2), king, col, row) ||
                checkknight(board.getpiece(kingcol - 1, kingrow +2), king, col, row) ||
                checkknight(board.getpiece(kingcol - 2, kingrow +1), king, col, row) ||
                checkknight(board.getpiece(kingcol - 2, kingrow -1), king, col, row);
    }

    private boolean checkknight(Piece p, Piece k, int col, int row) {
        return p != null && !board.sameteam(p,k) && p.name.equals("Knight") && !(p.col == col && p.row == row);
    }

    private boolean hitbyking(Piece king, int kingcol, int kingrow) {
        return  checkking(board.getpiece(kingcol - 1, kingrow - 1), king) ||
                checkking(board.getpiece(kingcol + 1, kingrow - 1), king) ||
                checkking(board.getpiece(kingcol, kingrow - 1), king) ||
                checkking(board.getpiece(kingcol - 1, kingrow), king) ||
                checkking(board.getpiece(kingcol + 1, kingrow), king) ||
                checkking(board.getpiece(kingcol - 1, kingrow + 1), king) ||
                checkking(board.getpiece(kingcol + 1, kingrow + 1), king) ||
                checkking(board.getpiece(kingcol, kingrow + 1), king);
    }

    private boolean checkking(Piece p, Piece k) {
        return p != null && !board.sameteam(p,k) && p.name.equals("King");
    }

    private boolean hitbypawn(int col, int row, Piece king, int kingcol, int kingrow) {
        int colorval = king.isWhite? -1:1;
        return checkpawn(board.getpiece(kingcol + 1, kingrow + colorval), king, col, row) ||
                checkpawn(board.getpiece(kingcol - 1, kingrow + colorval), king, col, row);
    }

    private boolean checkpawn(Piece p, Piece k, int col, int row) {
        return p != null && !board.sameteam(p,k) && p.name.equals("Pawn");
    }
}
