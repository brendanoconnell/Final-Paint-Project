import java.applet.Applet;
import java.awt.*;

public class PaintProgram extends Applet{
	
	Rectangle red, blue, green, yellow, orange, purple, brown;
	Rectangle boundaries;
	int state;
	Color Color;
	int color;
	int startX;
	int startY;
	int tempX;
	int tempY;
	int endX;
	int endY;
	boolean dragging;
	boolean isFill;

	
	
	
	public void init(){
		boundaries = new Rectangle(0, 125, 750,875);
		red = new Rectangle(50, 50, 50, 50);
		blue = new Rectangle(150, 50, 50, 50);
		green = new Rectangle(250, 50, 50, 50);
		yellow = new Rectangle(350, 50, 50, 50);
		orange = new Rectangle(450, 50, 50, 50);
		purple = new Rectangle(550, 50, 50, 50);
		brown = new Rectangle(650, 50, 50, 50);
		Color = Expo.black;
		startX = 0;
		startY = 0;
		tempX = 0;
		tempY = 0;
		endX = 0;
		endY = 0;
		dragging = true;
		color = 0;
		isFill = false;
		
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean mouseDown(Event e, int x, int y) {
			
			if(boundaries.inside(x,y)){
			startX = x;
			startY = y;
			endX = startX;
			endY = startY;
			}
			if(red.inside(x, y)){
				state = 1;
			}
			else if(blue.inside(x, y)){
				state = 2;
			}
			else if(green.inside(x, y)){
				state = 3;}
			else if(yellow.inside(x, y)){
				state = 4;}
			else if(orange.inside(x, y)){
				state = 5;
				color++;
				if (color > 7){
					color = 0;
				}
			}
			else if(purple.inside(x, y)){
				state = 6;
			}
			else if(brown.inside(x, y)){
				isFill = !isFill;
			}
			
			
			
		return true;
		}
		
			
			
	

	@SuppressWarnings("deprecation")
	public boolean mouseDrag(Event e,int x,int y) {
		if(boundaries.inside(x,y)){
		dragging = true;
		tempX = x;
		tempY = y;
		repaint();
		}
		return true;
				}

	
	
	
	
	public boolean mouseUp(Event e, int x, int y) {
		if(boundaries.inside(x,y)){
		endX = x;
		endY = y;
		repaint();
		}
		return true;
		}

	
	
	public void paint(Graphics g){
		//Expo.setFont(g, "Algerian", Font.PLAIN, 72);
		//Expo.drawString(g, "Paint 0.5", 300, 350);
		//Expo.delay(2000);
		//Expo.setBackground(g, Expo.white);
		Expo.setColor(g, Expo.black);
		Expo.setFont(g, "Arial", Font.PLAIN, 12);
		Expo.drawString(g, "Red = Freehand draw", 800, 100);
		Expo.drawString(g, "Blue = Pencil draw", 800, 150);
		Expo.drawString(g, "Green = Rectangle Tool", 800, 200);
		Expo.drawString(g, "Yellow = Circle Tool", 800, 250);
		Expo.drawString(g, "Orange =  Change Color", 800, 300);
		Expo.drawString(g, "Purple = Fill Tool", 800, 350);
		Expo.setColor(g, Expo.black);
		Expo.drawRectangle(g, 0, 125, 750, 1000);
		Expo.setColor(g, Expo.red);
		Expo.fillRectangle(g, 50, 50, 100, 100);//free draw
		Expo.setColor(g, Expo.blue);
		Expo.fillRectangle(g, 150, 50, 200, 100);//line tool
		Expo.setColor(g, Expo.green);
		Expo.fillRectangle(g, 250, 50, 300, 100);//rectangle tool
		Expo.setColor(g, Expo.yellow);
		Expo.fillRectangle(g, 350, 50, 400, 100);// circle tool
		Expo.setColor(g, Expo.orange);
		Expo.fillRectangle(g, 450, 50, 500, 100);// change color
		Expo.setColor(g, Expo.purple);
		Expo.fillRectangle(g, 550, 50, 600, 100);// 
		//Expo.setColor(g, Expo.brown);
		//Expo.fillRectangle(g, 650, 50, 700, 100);
		
		switch(state){
		case 1:
			Expo.setColor(g, Color);
			Expo.drawPoint(g, tempX, tempY);
			break;
		case 2:
			Expo.setColor(g, Color);
			Expo.drawLine(g, startX, startY, endX, endY);
			if(dragging )
			{
				
				Expo.setColor(g, Expo.black);
				Expo.drawLine(g, startX, startY, tempX, tempY);
				Expo.delay(150);
				Expo.setColor(g, Expo.white);
				Expo.drawLine(g, startX, startY, tempX, tempY);
				Expo.setColor(g, Expo.black);
				dragging = false;
			}
			break;
		case 3:
			Expo.setColor(g, Color);
			if (isFill == true)
			Expo.fillRectangle(g, startX, startY, endX, endY);
			else if (isFill = false)
			Expo.setColor(g, Color);
			Expo.drawRectangle(g, startX, startY, endX, endY);
			if(dragging )
			{
				
				Expo.setColor(g, Expo.black);
				Expo.drawRectangle(g, startX, startY, tempX, tempY);
				Expo.delay(150);
				Expo.setColor(g, Expo.white);
				Expo.drawRectangle(g, startX, startY, tempX, tempY);
				Expo.setColor(g, Expo.black);
				dragging = false;
			}
			break;
		
		case 4:
			int radius= getDistance(startX, startY, endX, endY);
			int tempradius= getDistance(startX, startY, tempX, tempY);
			Expo.setColor(g, Color);
			if (isFill == true)
			Expo.fillCircle(g, startX, startY, radius);	
			else if (isFill == false)
			Expo.drawCircle(g, startX, startY, radius);
			if(dragging)
			{
				
				Expo.setColor(g, Expo.black);
				Expo.drawCircle(g, startX, startY, tempradius);
				Expo.delay(150);
				Expo.setColor(g, Expo.white);
				Expo.drawCircle(g, startX, startY, tempradius);
				Expo.setColor(g, Expo.black);
				dragging = false;
			}
		case 5:
			switch(color){
			case 0:
				Color = Expo.black;
				break;
			case 1:
				Color = Expo.red;
			case 2:
				Color = Expo.blue;
				break;
			case 3:
				Color = Expo.yellow;
				break;
			case 4:
				Color = Expo.green;
				break;
			case 5:
				Color = Expo.orange;
				break;
			case 6:
				Color = Expo.purple;
				break;
			case 7:
				Color = Expo.brown;
			}
			
			break;
				
		case 6:
			Expo.setColor(g, Expo.white);
			Expo.drawRectangle(g, 0, 0, 1000, 1000);
			Expo.setColor(g, Expo.black);
			Expo.setFont(g, "Arial", Font.PLAIN, 12);
			Expo.drawString(g, "Red = Freehand draw", 800, 100);
			Expo.drawString(g, "Blue = Pencil draw", 800, 150);
			Expo.drawString(g, "Green = Rectangle Tool", 800, 200);
			Expo.drawString(g, "Yellow = Circle Tool", 800, 250);
			Expo.drawString(g, "Orange =  Change Color", 800, 300);
			Expo.drawString(g, "Purple = Fill Tool", 800, 350);
			Expo.setColor(g, Expo.black);
			Expo.drawRectangle(g, 0, 125, 750, 1000);
			Expo.setColor(g, Expo.red);
			Expo.fillRectangle(g, 50, 50, 100, 100);//free draw
			Expo.setColor(g, Expo.blue);
			Expo.fillRectangle(g, 150, 50, 200, 100);//line tool
			Expo.setColor(g, Expo.green);
			Expo.fillRectangle(g, 250, 50, 300, 100);//rectangle tool
			Expo.setColor(g, Expo.yellow);
			Expo.fillRectangle(g, 350, 50, 400, 100);// circle tool
			Expo.setColor(g, Expo.orange);
			Expo.fillRectangle(g, 450, 50, 500, 100);// change color
			Expo.setColor(g, Expo.purple);
			Expo.fillRectangle(g, 550, 50, 600, 100);// 
		break;
		}
			
		
		
	
		
	}
		
	public int getDistance(int x1, int y1, int x2, int y2)
	{
		double distance = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
		return (int) distance;
	}

		
		
		public void update(Graphics g){
				paint(g);
		}
			
			

	
}

