package byow.lab12;
import edu.neu.ccs.util.Hex;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import javax.swing.text.Style;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /**
     * hexSize is the size of the hexagon.
     * Top hexagon has start position x = worldSize / 2 - hexSize / 2, y = 0
     *
     */

    private static final Random RANDOM = new Random(1000000);
    private int worldSize;
    public TETile[][] world;
    private Hexagon firstHex;

    private class Hexagon {
        public int size;
        public TETile tileType;
        public int pozX;
        public int pozY;
        public Hexagon left;
        public Hexagon right;
        public Hexagon down;

        /**
         * Given a size we generate a hexagon with the following characteristics
         * The hexagon consists of 2 * size number of lines. First line consists
         * of size nr of tiles. Subsequent lines increase the number of tiles
         * with 2 for each level until <= size * 2, then it starts over in
         * decreasing manner until nr of tiles in the line == size.
         */
        Hexagon(int size, TETile tileType, int pozX, int pozY) {
            // size is the side length
            this.size = size;
            this.tileType = tileType;
            // those are x, y coordinates of the first tile in the upper side.
            this.pozX = pozX;
            this.pozY = pozY;

            // Generate upper half of the hexagon.
            for (int i = 0; i < size; i++) {
                for (int x = pozX - i; x < pozX + size + i; x++) {
                    world[x][pozY - i] = tileType;
                }
            }
            // Generate lower half of the hexagon.
            for (int i = size - 1; i >= 0; i--) {
                for (int x = pozX - i; x < pozX + size + i; x++) {
                    world[x][pozY + 1 - size * 2 + i] = tileType;
                }
            }
        }

        public void hexagonLeft() {
            left = new Hexagon(size, randomTile(), pozX - size * 2 + 1, pozY - size);
        }
        public void hexagonRight() {
            right = new Hexagon(size, randomTile(), pozX + size * 2 - 1, pozY - size);
        }
        public void hexagonDown() {
            down = new Hexagon(size, randomTile(), pozX, pozY - size * 2);
        }
    }

    public HexWorld(int hexSize) {
        worldSize = hexSize * 2 * 5;
        world = new TETile[worldSize][worldSize];
        firstHex = new Hexagon(hexSize, randomTile(),
                (worldSize / 2) - (hexSize / 2),
                worldSize - 1);
        firstHex.hexagonRight();
        firstHex.hexagonDown();
        firstHex.hexagonLeft();
        firstHex.left.hexagonLeft();
        firstHex.left.left.hexagonDown();
        firstHex.left.left.down.hexagonDown();
        firstHex.left.hexagonDown();
        firstHex.left.down.hexagonDown();
        firstHex.left.down.down.hexagonDown();
        firstHex.down.hexagonDown();
        firstHex.down.down.hexagonDown();
        firstHex.right.hexagonRight();
        firstHex.right.right.hexagonDown();
        firstHex.right.right.down.hexagonDown();
        firstHex.right.hexagonDown();
        firstHex.right.down.hexagonDown();
        firstHex.right.down.down.hexagonDown();
        firstHex.down.down.down.hexagonDown();
        fillWorld(world);
    }

    private static void fillWorld(TETile[][] tiles) {
        for (int x = 0; x < tiles[0].length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                if (tiles[x][y] == null) {
                    tiles[x][y] = Tileset.NOTHING;
                }
            }
        }
    }

    private static TETile randomTile() {
        int tileNum  = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.WATER;
            case 3: return Tileset.FLOWER;
            case 4: return Tileset.SAND;
            case 5: return Tileset.MOUNTAIN;
            default: return Tileset.TREE;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        HexWorld h = new HexWorld(6);
        ter.initialize(h.worldSize, h.worldSize);
        ter.renderFrame(h.world);
    }
}
