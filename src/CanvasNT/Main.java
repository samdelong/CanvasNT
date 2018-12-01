package CanvasNT;

class Main extends Canvas implements FrameListener{

    int xvel = 1;
    int yvel = 1;
    int x = 1;
    int y = 10;

    Main(){
        super(40,20);

    }

    public static void main(String[] args) {
        Main m = new Main();
        m.setup();


    }

    void setup(){

        background("# ");

        begin(30, this);
    }

    @Override
    public void onFrame(Canvas canv){
        background("# ");
        point(x, y, "  ");

        x+=xvel;
        y+=yvel;
        if(x > width || x < 0){
            xvel *= -1;
        }if(y > height || y < 0){
            yvel *= -1;
        }

    }
    @Override
    public void onError(InterruptedException e){
        e.printStackTrace();
    }
}
