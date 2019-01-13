package CanvasNT;
class Blank extends Canvas implements FrameListener{

    final int resx = 37;
    final int resy = 20;

    Blank(){
        super(37,20);
    }

    public static void main(String[] args) {
        // Create method, start
        Blank m = new Blank();
        m.setup();
    }

    void setup(){
        //first frame background
        background("#");

        // begin drawing on canvasnt
        begin(30, this);
    }

    @Override
    public void draw(Canvas canv){
        //clear the frame.
        background("$");
        //draw a rect at x: 5, y:5, with a width of 4 and height of 2.
        rect(5,5,4,2);

    }


}
