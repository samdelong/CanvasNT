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

        background("#");

        begin(30, this);
    }

    @Override
    public void draw(Canvas canv){
        background("#");
        color(" ");
        point(x, y);
        if(x >= width || x < 0){
            xvel *= -1;
        }if(y >= height || y < 0){
            yvel *= -1;
        }
        x+=xvel;
        y+=yvel;


    }
    @Override
    public void onError(InterruptedException e){
        e.printStackTrace();
    }
}
