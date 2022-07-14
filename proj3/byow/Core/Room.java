package byow.Core;

import byow.Core.RandomUtils;
import byow.TileEngine.Tileset;
import byow.TileEngine.TETile;

import java.util.Random;

public class Room {
    public Random random;
    private int maxSize;
    public int[][] shape;

    Room(int seed, int pozX, int pozY, int maxSize) {
        random = new Random(seed);
        this.maxSize = maxSize;
    }

    private int[][] carver() {
        int roomSize = RandomUtils.uniform(random, 4, maxSize);
        int[][] room = new int[roomSize][roomSize];
    }
}
