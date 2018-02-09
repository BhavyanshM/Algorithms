package elements;

public class Obstacle{
	public int x;
	public int y;
	public int radius;
	public int velx;
	public int vely;
	
	public Obstacle(int x, int y, int r){
		this.x = x;
		this.y = y;
		this.radius = r;
	}
	
	public Obstacle(int x, int y, int r, int vx, int vy){
		this.x = x;
		this.y = y;
		this.radius = r;
		this.velx = vx;
		this.vely = vy;
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
