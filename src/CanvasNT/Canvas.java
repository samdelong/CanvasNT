package CanvasNT;

class Canvas {

    int width = 20;
    int height = 20;
    String[] pixels;
    String color = " ";
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

        for(int i = x; i < w; i++){
            for(int j = y; j < h; j++){
                pixels[j*w+i] = color;
            }
        }

    }

    void color(String color){
        this.color = color + " ";
    }

    public void point(int x, int y) {
        if (x < width && y < height && x > -1 && y > -1) {
            pixels[y * width + x] = color;
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
        System.out.print("\033[H\033[2J");
    }

    public void stroke(){}

    public void begin(int framerate, FrameListener listener) {

        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    listener.draw(Canvas.this);
                    clear();

                    String buffer = "\n";
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