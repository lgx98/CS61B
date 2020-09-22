package byog.lab5;

import byog.Core.RandomUtils;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TileEffect;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        addHexagonTesselation(world,new Position(0,0),5);
        //System.out.print(TETile.toString(world));
        ter.renderFrame(world);
    }

    public static class Position {
        public int x;
        public int y;

        public Position(int xPos, int yPos) {
            this.x = xPos;
            this.y = yPos;
        }

        public Position translate(int xBias, int yBias) {
            return new Position(this.x + xBias, this.y + yBias);
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int size, TETile t, TileEffect TE) {
        for (int i = 0; i < size; i++) {
            addVertLine(world, p.translate(size - i - 1, i), size + i * 2, t, TE);
        }
        for (int i = 0; i < size; i++) {
            addVertLine(world, p.translate(i, size + i), size * 3 - 2 * i - 2, t, TE);
        }
    }

    public static void addVertLine(TETile[][] world, Position p, int len, TETile t, TileEffect TE) {
        for (int xPos = p.x; len > 0; len--, xPos++) {
            world[xPos][p.y] = TE.applyEffect(t);
        }
    }

    /**
     * not elegant at all...
     * could be done by defining latticeGenerator interface
     * maybe something like... tesselation(hexagon(vertLine(tiles)))?
     * anyway, this code works well
     */
    public static void addHexagonTesselation(TETile[][] world, Position pos, int size) {
        Random rand = new Random(0);
        Position startPos = pos.translate(0, size * 2);
        for (int i = 0; i < 5; i++) {
            Position colPos = startPos;
            for (int j = 0; j < 5 - Math.abs(i - 2); j++) {//*
                TETile t = null;
                TileEffect TE = null;
                switch (RandomUtils.uniform(rand, 0, 5)) {
                    case 0:
                        t = Tileset.GRASS;
                        TE = new TETile.noEffect();
                        break;
                    case 1:
                        t = Tileset.TREE;
                        TE = new TETile.noEffect();
                        break;
                    case 2:
                        t = Tileset.MOUNTAIN;
                        TE = new TETile.colorNoise(64);
                        break;
                    case 3:
                        t = Tileset.SAND;
                        TE = new TETile.colorNoise(64);
                        break;
                    case 4:
                        t = Tileset.FLOWER;
                        TE = new TETile.colorNoise(64);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + RandomUtils.uniform(rand, 0, 5));
                }
                addHexagon(world, colPos, size, t, TE);//*/
                //System.out.println(colPos.x+" "+colPos.y);
                colPos = colPos.translate(0, 2 * size);
            }
            //System.out.println("~~~~~~~~~~");
            startPos = startPos.translate(2 * size - 1, i < 2 ? -size : size);
        }
    }
}
