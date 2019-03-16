# CanvasNT
Create ASCII Animations on the command line using Java

FOR A REGULARLY UPDATED VERSION: https://repl.it/@samdelong/Canvasnt

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

# Doc

There are a few methods you can use to create graphics

```java

// Clears screen with character
void background(String character);

// Draw line from (x, y) to (x1, y1), determined by stroke()
void line(int x, int y, int x1, int y1);

// Draw point, determined by stroke()
void point(int x, int y);

// Draw rect, inside determined by fill(), outline determined by stroke()
void rect(int x, int y, int width, int height);

// Set fill and stroke values
void fill(String character);
void stroke(Strin character);

// Sets stroke / fill to background
void noStroke();
void noFill();




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
### Running the example in the src/CanvasNT directory should give you something like this:
<a href="https://gyazo.com/7af117e6aa6effb0417e353de9f0ba7d"><img src="https://i.gyazo.com/7af117e6aa6effb0417e353de9f0ba7d.gif" alt="Image from Gyazo" width="630"/></a>

