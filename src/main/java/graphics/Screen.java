package graphics;

/**
 * Created by UC198883 on 17/02/2016.
 */
public class Screen {

    private int width;
    private int height;
    public int[] pixels;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void render() {
        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {

            }
        }
    }
}
