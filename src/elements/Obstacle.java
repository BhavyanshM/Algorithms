package elements;

public class Obstacle{
	public int x;
	public int y;
	public int radius;
	
	public Obstacle(int x, int y, int r){
		this.x = x;
		this.y = y;
		this.radius = r;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}		
}
