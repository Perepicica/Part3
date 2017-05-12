import java.util.HashMap;
import java.util.Map;

public final class ClassForGame {
    
    public enum Move {
        X, O;
        public Move opposite() {
            return this == X ? O : X;
        }
    }

    private final int size;
    private String winner ;

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public Map<Cell, Move> getMoves() {
        return moves;
    }

    public String getWinner() {
        return winner;
    }

    private final Map<Cell, Move> moves = new HashMap<>();
    private Move turn = Move.X;
    public String player1;
    private String player2;


    public ClassForGame(int size, String player1, String player2) {
        this.size = size;
        this.player1 = player1;
        this.player2 = player2;
    }

    private final static class Cell {
        private final int x;
        private final int y;

        private Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof Cell) {
                Cell cell = (Cell) obj;
                return x == cell.x && y == cell.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result1 = x;
            result1 = 31 * result1 + y;
            return result1;
        }
    }

    public   Move get(int x, int y) {
        return get(new Cell(x, y));
    }

    private Move get(Cell cell) {
        return moves.get(cell);
    }

    public void makeTurn(int x, int y) {
        Cell cell = new Cell(x, y);
        if (get(x, y) != null)
            throw new IllegalArgumentException("Эта ячейка уже занята, попробуйте другую!");
        if (x < 0 || x >= size) throw new IllegalArgumentException("x должен быть не меньше 0 и меньше " + size);
        if (y < 0 || y >= size) throw new IllegalArgumentException("у должен быть не меньше 0 и меньше " + size);
        moves.put(cell, turn);
        whoIsWinner();
        turn = turn.opposite();
    }

    public void clearCell(int x, int y) {
        if (x < 0 || x >= size) throw new IllegalArgumentException("x должен быть не меньше 0 и не больше " + size);
        if (y < 0 || y >= size) throw new IllegalArgumentException("у должен быть не меньше 0 и не больше " + size);
        Cell cell = new Cell(x, y);
        moves.put(cell, null);
    }

    private Move move = Move.O;

    private void whoIsWinner() {
        move = move.opposite();
        int win = 0;
        //ищу победителя в строчках
        for (int x = 0; x < size; x++) {
            int count = 0;
            for (int y = 0; y < size; y++) {
                Cell cell = new Cell(x, y);
                if (move.equals(get(x, y))) count++;
            }
            if (count == size) win++;
        }
        // ищу победителя в столбцах
        for (int y = 0; y < size; y++) {
            int count = 0;
            for (int x = 0; x < size; x++) {
                Cell cell = new Cell(x, y);
                if (move.equals(get(x, y))) count++;
            }
            if (count == size) win++;
        }
        //ищу победителя в главной диагонали
        int count = 0;
        for (int x = 0; x < size; x++) {
            int y = x;
            Cell cell = new Cell(x, y);
            if (move.equals(get(x, y))) count++;
        }
        if (count == size) win++;
        //ищу победителя в побочнй диагонали
        count = 0;
        for (int x = 0; x < size; x++) {
            int y = size - 1 - x;
            Cell cell = new Cell(x, y);
            if (move.equals(get(x, y))) count++;
        }
        if (count == size) win++;
        if (win > 0 && move.equals(Move.X)) winner = player1;
        if (win > 0 && move.equals(Move.O)) winner = player2;

    }

    @Override
    public String toString() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                return get(x, y) + " ";
            }
            return "/n";
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof ClassForGame) {
            ClassForGame xxoo = (ClassForGame ) obj;
            if (size == xxoo.size && size == xxoo.size) {
                for (int x = 0; x < size; x++) {
                    for (int y = 0; y < size; y++) {
                        return get(x, y) == xxoo.get(x, y);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 17 * result + size;
        return result;
    }

}
