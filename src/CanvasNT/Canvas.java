package CanvasNT;
public class Canvas {

    int width = 20;
    int height = 20;
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

    void putPoint(int x, int y, String col){
      if (x < width && y < height && x > -1 && y > -1) {
          pixels[y * width + x] = col;
      }

    }

    void rect(int x, int y, int w, int h){
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
        for(int j = y; j < h; j++){
            for(int i = x; i < w; i++){
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
    void line(int x1, int y1, int x2, int y2)

    {
        if(x2 < x1){
            int t = x2;
            x2 = x1;
            x1 = t;
        }
        if(y2 < y1){
            int t = y2;
            y2 = y1;
            y1 = t;
        }
        int m_new = 2 * (y2 - y1);
        int slope_error_new = m_new - (x2 - x1);

        for (int x = x1, y = y1; x <= x2; x++)
        {
            point(x, y);
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

    public void point(int x, int y) {
        if (x < width && y < height && x > -1 && y > -1) {
            pixels[y * width + x] = stroke;
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
        } catch (InterruptedException e) {
            listener.onError(e);
            thread.stop();
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

                            buffer += (pixels[y * width + x]) + " ";
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