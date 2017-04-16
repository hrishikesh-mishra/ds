package com.hrishikeshmishra.practices.matrix;

/**
 * Problem:
 * Langton's Ant
 * ;
 * An ant is sitting on an infinite grid of white and black squares. It initially faces right. At each step, it does
 * the following :-
 * - At a white square,
 * - - flip the color of the square,
 * - - turn 90 degree right (clockwise) and
 * - - move forward one unit
 * - At a black square,
 * - - flip the color the square,
 * - - turn 90 degree left (counter-clockwise) and
 * - - move forward one unit
 * ;
 * Implement simulator for first K moves that the ant makes and print the final board as a grid.
 * Note: - You are not provided data structure to represent the grid.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/langtons-ant-problem/
 */
public class LangtonsAnt {

    public static class Position {
        private int row;
        private int column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public enum Orientation {
        LEFT, RIGHT, UP, DOWN;

        private Orientation getTurn(boolean clockwise) {
            if (this == LEFT) {
                return clockwise ? UP : DOWN;
            } else if (this == UP) {
                return clockwise ? RIGHT : LEFT;
            } else if (this == RIGHT) {
                return clockwise ? DOWN : UP;
            } else {
                return clockwise ? LEFT : RIGHT;
            }
        }

        @Override
        public String toString() {
            if (this == LEFT) {
                return "\u2190";
            } else if (this == UP) {
                return "\u2191";
            } else if (this == RIGHT) {
                return "\u2192";
            } else {
                return "\u2193";
            }
        }
    }

    public static class Ant {
        private Position position;
        private Orientation orientation;

        public Ant(Position position, Orientation orientation) {
            this.position = position;
            this.orientation = orientation;
        }

        public Ant() {
            this.position = new Position(0, 0);
            this.orientation = Orientation.RIGHT;
        }

        public void turn(boolean clockwise) {
            orientation = orientation.getTurn(clockwise);
        }

        public void move() {
            switch (orientation) {
                case UP:
                    position.row--;
                    break;

                case DOWN:
                    position.row++;
                    break;

                case LEFT:
                    position.column--;
                    break;

                case RIGHT:
                    position.column++;
            }
        }

        public void adjustPosition(int shiftRow, int shiftCol) {
            position.row += shiftRow;
            position.column += shiftCol;
        }
    }

    public static class Grid {
        private boolean[][] grid;
        private Ant ant;

        public Grid() {
            grid = new boolean[1][1];
            ant = new Ant();
        }

        public void printKMoves(int k) {
            for (int i = 0; i < k; i++) {
                move();
            }

            System.out.println(this);
        }

        public void move() {
            ant.turn(grid[ant.position.row][ant.position.column]);
            flip(ant.position);
            ant.move();
            ensureFit(ant.position);
        }

        private void ensureFit(Position position) {
            int shiftRow = 0;
            int shiftCol = 0;

            int numberOfRows = grid.length;
            if (position.row < 0) {
                shiftRow = numberOfRows;
                numberOfRows *= 2;
            } else if (position.row >= grid.length) {
                numberOfRows *= 2;
            }

            int numberOfColumn = grid[0].length;
            if (position.column < 0) {
                shiftCol = numberOfColumn;
                numberOfColumn *= 2;
            } else if (position.column >= grid[0].length) {
                numberOfColumn *= 2;
            }


            if (numberOfRows != grid.length ||
                    numberOfColumn != grid[0].length) {
                boolean[][] newGrid = new boolean[numberOfRows][numberOfColumn];
                copyWithShift(grid, newGrid, shiftRow, shiftCol);
                ant.adjustPosition(shiftRow, shiftCol);
                grid = newGrid;
            }
        }

        private void copyWithShift(boolean[][] oldGrid, boolean[][] newGrid, int shiftRow, int shiftCol) {

            for (int r = 0; r < oldGrid.length; r++) {
                for (int c = 0; c < oldGrid[0].length; c++) {
                    newGrid[r + shiftRow][c + shiftCol] = oldGrid[r][c];
                }
            }
        }

        private void flip(Position position) {
            grid[position.row][position.column] = !grid[position.row][position.column];
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    if (r == ant.position.row && c == ant.position.column) {
                        sb.append(ant.orientation);
                    } else if (grid[r][c]) {
                        sb.append("X");
                    } else {
                        sb.append("_");
                    }
                }

                sb.append("\n");
            }

            sb.append("Ant :" + ant.orientation + ". \n");
            return sb.toString();
        }

    }
}


class LangtonsAntTest {
    public static void main(String[] args) {
        LangtonsAnt.Grid grid = new LangtonsAnt.Grid();
        grid.printKMoves(10);
    }
}