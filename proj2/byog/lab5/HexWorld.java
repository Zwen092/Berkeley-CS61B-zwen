package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private static int count = 0;


    /**
     * computes the width of row i for a size s hexagon
     * @param s the size of the hex
     * @param i the row number where i = 0 is the bottom row
     * @return
     */
    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return s + 2 * effectiveI;
    }

    /**
     * assuming the bottom row has an x-coordinate 0
     * @param s size
     * @param i row number, i = 0 means the bottom
     * @return
     */
    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    /**
     * Adds a row of the same tile
     * @param world
     * @param p
     * @param width
     * @param t
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi++) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32,32, 32, RANDOM);
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }
        //hexagons have 2*s rows, this code iterates up from the bottom
        //which we call row 0
        for (int yi = 0; yi < 2 * s; yi++) {
            int thisRowY = p.y + yi;
            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);
            int rowWidth = hexRowWidth(s, yi);
            addRow(world, rowStartP, rowWidth, t);
        }
    }

    /*

     */
    private static void changePositionUp(Position p) {
        p.y += 6;
    }
    private static void changePosition(Position p, int n) {
        p.x += 5;
        if (count > 2) {
            p.y = p.y - n * 6 + 3;
        } else {
            p.y = p.y - n * 6 - 3;
        }
    }

    public static void drawRandomVerticalHexes(TETile[][] world, int n, Position p, TETile[] tile) {
        for (int i = 0; i < n; i++) {
            addHexagon(world, p, 3, tile[i]);
            changePositionUp(p);
        }
        count++;
        changePosition(p, n);


    }
    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position start = new Position(16,6);
        TETile[] tile = new TETile[]{Tileset.FLOWER, Tileset.GRASS, Tileset.MOUNTAIN};
        drawRandomVerticalHexes(world, 3, start, tile);

        tile = new TETile[]{Tileset.GRASS, Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.GRASS};
        drawRandomVerticalHexes(world, 4, start, tile);

        tile = new TETile[]{Tileset.FLOOR, Tileset.WATER, Tileset.WALL, Tileset.GRASS, Tileset.FLOOR};
        drawRandomVerticalHexes(world, 5, start, tile);

        tile = new TETile[]{Tileset.SAND, Tileset.MOUNTAIN, Tileset.TREE, Tileset.FLOWER};
        drawRandomVerticalHexes(world, 4, start, tile);

        tile = new TETile[]{Tileset.WALL, Tileset.WATER, Tileset.GRASS};
        drawRandomVerticalHexes(world, 3, start, tile);


        ter.renderFrame(world);
    }

}
