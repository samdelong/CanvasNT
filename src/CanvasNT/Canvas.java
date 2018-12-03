package CanvasNT;
public class Canvas {

    int width = 20;
    int height = 20;
    String[] pixels;
    String stroke = " ";
    String fill = " ";
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
                }else{
                  putPoint(i, j, fill);

                }
            }
        }
    }

    void fill(String fill){
      this.fill = fill + " ";
    }

    void stroke(String stroke){
        this.stroke = stroke + " ";
    }

    public void point(int x, int y) {
        if (x < width && y < height && x > -1 && y > -1) {
            pixels[y * width + x] = stroke;
        }
    }

    public void background(String character) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = character + " ";
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
        System.out.print("\033[H\033[2Jcool");
    }

    public void stroke(){}

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

                            buffer += (pixels[y * width + x]);
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