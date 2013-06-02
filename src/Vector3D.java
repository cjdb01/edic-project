import java.lang.Math;

/**
 * Vector3D.java
 * @author Christopher Di Bella
 * A 3-dimensional vector class
 * 
 * Code adapted from the C++ Pipe::Engine code base
 * Pipe::Engine copyright (c) Christopher Di Bella, 2011
 * 
 * Vector3D has been adapted from Pipe::Math::Vector3D
 * to fit a Java-based environment
 *
 * Code is documented where changes have been made
 */
public class Vector3D
{
	private int x;
	private int y;
	private int z;
	
	/**
	 * Vector3D constructor
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param z The z coordinate
	 */
	public Vector3D(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Equivalent to C++ copy constructor
	 * @param vector The vector to be copied
	 */
	public Vector3D(final Vector3D vector)
	{
		x = vector.x;
		y = vector.y;
		z = vector.z;
	}
	
	/**
	 * Gets the x-coordinate
	 * @return x
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Gets the y-coordinate
	 * @return y
	 */
	public int getY()
	{
		return y;
	}
	

	/**
	 * Gets the z-coordinate
	 * @return z
	 */
	public int getZ()
	{
		return z;
	}
	

	/**
	 * Sets the x-coordinate
	 * @param x The value this.x shall be set to
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	

	/**
	 * Sets the y-coordinate
	 * @param y The value this.y shall be set to
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	

	/**
	 * Sets the z-coordinate
	 * @param z The value this.z shall be set to
	 */
	public void setZ(int z)
	{
		this.z = z;
	}
	
	/**
	 * Gets the size of the vector
	 * @return sqrt(x^2 + y^2 + z^2) rounded down to the integer
	 */
	public int size()
	{
		return (int)Math.sqrt((x*x) + (y*y) + (z*z));
	}
}