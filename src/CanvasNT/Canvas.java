package CanvasNT;
import java.lang.Math;
public class Canvas {

    float width = 20;
    float height = 20;
    String[] pixels;
    String stroke = " ";
    String fill = " ";
    String bgc = "# ";

    public Canvas(int width, int height) {

        if (width < 0 || height < 0) {
            System.out.println("CanvasNT can't have negative width or height. width: " + width + ", height: " + height);
            CanvasException e = new CanvasException();
            e.printStackTrace();
            return;

        }
        pixels = new String[width * height];
        this.width = width;
        this.height = height;

    }



    float random(float min, float max){

        return min + ((float) Math.random()) * (max - min);
    }

    float random(float max){
        return ((float) Math.random()) * (max);
    }

    void putPoint(float x, float y, String col){
      if (x < width && y < height && x > -1 && y > -1) {
          pixels[(int) y * (int) width + (int) x] = col;
      }

    }

    void rect(float x, float y, float w, float h){
        if(x < 0){
            x = 0;
        }if(y < 0){
            y = 0;
        }
        if(w + x > width){
            w = width - x;
        }
        if(h + y > height){
            h = height-y;
        }

        w += x;
        h += y;
        for(float j = y; j < h; j++){
            for(float i = x; i < w; i++){
                if(j == y || j == h-1 || i == x || i == w-1){
                    putPoint(i, j, stroke);
                }
                else{
                  putPoint(i, j, fill);

                }
            }
        }
    }


    // Bresenham Line Algorithm - thanks to https://www.geeksforgeeks.org/bresenhams-line-generation-algorithm/
    void line(float x1, float y1, float x2, float y2)

    {
        if(x2 < x1){
            float t = x2;
            x2 = x1;
            x1 = t;
        }
        if(y2 < y1){
            float t = y2;
            y2 = y1;
            y1 = t;
        }
        float m_new = 2 * (y2 - y1);
        float slope_error_new = m_new - (x2 - x1);

        for (float x = x1, y = y1; x <= x2; x++)
        {
            putPoint(x, y, stroke);
            slope_error_new += m_new;

            if (slope_error_new >= 0)
            {
                y++;
                slope_error_new -= 2 * (x2 - x1);
            }
        }
    }


    void fill(String fill){
      this.fill = fill;
    }

    void noFill(){
        this.fill = bgc;
    }

    void stroke(String stroke){
        this.stroke = stroke;
    }

    void noStroke(){
        this.stroke = bgc;
    }

    public void point(float x, float y) {
        if (x < width && y < height && x > -1 && y > -1) {
            pixels[(int) y * (int) width + (int) x] = stroke;
        }
    }

    public void background(String character) {
        bgc = character;
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = bgc;
        }
    }

    public static void sleepThread(long millis, Thread thread, FrameListener listener) {
        try {
            thread.sleep(millis);
        } catch (InterruptedException ignored) {

        }
    }



    public static void clear() {
        //System.out.print("\[2J");
        System.out.print("\033[H\033[2J");
    }

    public void begin(int framerate, FrameListener listener) {

        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    listener.draw(Canvas.this);
                    clear();

                    String buffer = "\r\n";
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {

                            buffer += (pixels[y * (int) width + x]) + " ";
                        }
                        buffer += "\n";
                    }
                    buffer += "\n\n";
                    System.out.print(buffer);
                    sleepThread(1000 / framerate, this, listener);
                }
            }
        };
        t.start();

    }

}
