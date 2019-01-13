package CanvasNT;
class Main extends Canvas implements FrameListener{

    float xvel = 1;
    float yvel = 1;
    float x = 0;
    float y = 0;
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
        background("$");
        stroke("+");
        fill("~");
        rect(0,0, width, height);
        stroke("@");
        for(Bounce bo : b){
            bo.update();
        }
        // line(1,1,10,20);
        // line(width, height, 0, 0);
        // point(x, y);

    }


class Bounce{

    float x;
    float y;
    float xvel = 1;
    float yvel = 1;;

    Bounce(){
        xvel = random(-2f,2f);
        yvel = random(-2f,2f);
        x = random(width);
        y = random(height);
    }

    public void update(){
         if(x >= width || x < 0){
            xvel *= -1;
        }if(y >= height || y < 0){
            yvel *= -1;
        }
        x+=xvel;
        y+=yvel;
        point(x, y);
    }

}

}

// Example, items bouncing around the screen
