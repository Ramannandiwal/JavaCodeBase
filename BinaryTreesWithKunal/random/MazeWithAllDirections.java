public class MazeWithAllDirections {
    public static void printBoolean2DArray(boolean[][] array, String path) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            System.out.println("Empty array");
            return;
        }

        int rows = array.length;
        int cols = array[0].length;

        // Print column headers
        System.out.println();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == rows - 1 && j == cols - 1) {
                    System.out.print(" E "); // End point
                } else if (array[i][j]) {
                    if (path.contains("" + i + j)) {
                        System.out.print(" * "); // Path traced
                    } else {
                        System.out.print(" X "); // Open path
                    }
                } else {
                    System.out.print(" | "); // Wall
                }
            }
            System.out.println();
        }
    }

    static int count = 0;

    public static void printallPart(boolean[][] maze, String p, int row, int col) {
        if (row == maze.length - 1 && col == maze[0].length - 1) {
            maze[row][col] = false;
            printBoolean2DArray(maze, p);
            System.out.println("Path: " + p);
            return;
        }
        if (!maze[row][col]) {
            return;
        }
        maze[row][col] = false;
        if (row < maze.length - 1) {
            printallPart(maze, p + "D", row + 1, col);
        }
        if (col < maze[0].length - 1) {
            printallPart(maze, p + "R", row, col + 1);
        }
        if (row > 0) {
            printallPart(maze, p + "U", row - 1, col);
        }
        if (col > 0) {
            printallPart(maze, p + "L", row, col - 1);
        }
        maze[row][col] = true;
    }

    public static void main(String[] args) {
        boolean[][] maze = {
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        printallPart(maze, "", 0, 0);
    }
}
