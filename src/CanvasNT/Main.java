package CanvasNT;
class Main extends Canvas implements FrameListener{

    int xvel = 2;
    int yvel = 1;
    int x = 0;
    int y = 0;

    Main(){
        super(37,20);

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
        background("/");
        stroke("B");
        fill(" ");

       //point(x, y);
       if(x >= width - 4 || x < 0){
           xvel *= -1;
       }if(y >= height - 4 || y < 0){
           yvel *= -1;
       }
       x+=xvel;
       y+=yvel;
        rect(x,y,5,5);

    }
    @Override
    public void onError(InterruptedException e){
        e.printStackTrace();
    }
}