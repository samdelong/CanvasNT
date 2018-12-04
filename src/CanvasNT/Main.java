package CanvasNT;
class Main extends Canvas implements FrameListener{

    int xvel = 2;
    int yvel = 1;
    int x = 0;
    int y = 0;
    Bounce[] b;
    Main(){
        super(37,20);
        b = new Bounce[5];

        for(int i = 0; i < b.length; i ++){
            b[i] = new Bounce();
        }
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
        background("!");
        stroke("W");
        fill("C");
        for(Bounce bo : b){
            bo.update();
        }
        // line(1,1,10,20);
        // line(width, height, 0, 0);
        //point(x, y);
        if(x >= width - 4 || x < 0){
            xvel *= -1;
        }if(y >= height - 4 || y < 0){
            yvel *= -1;
        }
        x+=xvel;
        y+=yvel;
        rect(x,y,2,2);

    }
    @Override
    public void onError(InterruptedException e){
        e.printStackTrace();
    }

    class Bounce{

    int x;
    int y;
    int xvel = 1;
    int yvel = 1;;

    Bounce(){
        x = random(width);
        y = random(height);
    }

    public void update(){
         if(x >= width - 4 || x < 0){
            xvel *= -1;
        }if(y >= height - 4 || y < 0){
            yvel *= -1;
        }
        x+=xvel;
        y+=yvel;
        point(x, y);
    }

}

}

// Example, items bouncing around the screen
