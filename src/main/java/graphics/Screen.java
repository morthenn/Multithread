package graphics;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by UC198883 on 17/02/2016.
 */
public class Screen {

    private int width;
    private int height;
    public int[] pixels;

    private HashMap<Integer, Integer> COLOR_MAP = new HashMap<Integer, Integer>(12) {
        {
            put(1, 0x522c52);
            put(2, 0x00984f);
            put(3, 0x4c05ff);
            put(4, 0x75af1d);
            put(5, 0xe83030);
            put(6, 0xe6e0ae);
            put(7, 0xe8ad45);
            put(8, 0xffa0a9);
            put(9, 0x837e6c);
            put(10, 0xffd557);
            put(0, 0xe83030);

        }
    };

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void render() {
        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {
                pixels[x + y * width] = COLOR_MAP.get((int) Math.round(Math.random() * 10));
            }
        }
    }
}
