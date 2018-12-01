# CanvasNT
Create ASCII Animations on the command line using Java

# Usage

```java

  // Create a canvas object
  
  Canvas c = new Canvas(40, 20);
  
  // After begin, the draw method will be called every frame
  
  c.begin(framerate, new FrameListener(){
  
    @Override
    public void draw(){
    
      // do stuff...
      background("#");
      color("&");
      point(5, 10);
      point(c.width/2, c.height/2);
    
    }
  
  });

```

```java

  // You can also extend your class, and implement the frameListener...
  
  class Main extends Canvas implements FrameListener{
  
    Main(){
      // set width and height
      super(40, 20);
      
      background("#");
      begin(30, this);
      
    }
    
    @Override
    public void draw(Canvas canv){
        background("#");
        color(" ");
        point(x, y);
    }
    
    @Override
    public void onError(InterruptedException e){
        e.printStackTrace();
    }
  
  }

```
