package Ant;

public class Vector 
{
	double x;
	double y;
	
	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector v)
	{
		this.x += v.x;
		this.y += v.y;
	}
	
	public void sub(Vector v)
	{
		this.x -= v.x;
		this.y -= v.y;
	}
	
	public void div(double c)
	{
		this.x /= c;
		this.y /= c;
	}
	
	public void mult(double c)
	{
		this.x *= c;
		this.y *= c;
	}
	
	public void normalize()
	{
		double mag = Math.sqrt(x*x + y*y);
		this.x /= mag;
		this.y /= mag;
	}
	
	public Vector clone()
	{
		return new Vector(this.x, this.y);
	}

	public Vector Avg(Vector vector) {
		
		return new Vector( (this.x + vector.x)/2 , (this.y + vector.y)/2  );
	}

}
