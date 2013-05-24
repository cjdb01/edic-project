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
	
	/**
	 * Checks if a vector v is perpendicular to this
	 * @param rhs The aforementioned vector v
	 * @return true if perpendicular ; false otherwise
	 */
	public boolean isPerpendicularTo(final Vector3D rhs)
	{
		return (dotProduct(this, rhs) == 0.0);
	}
	
	/**
	 * Checks if a vector v is parallel to this
	 * @param rhs The aforementioned vector v
	 * @return true if vector is parallel ; false otherwise
	 */
	public boolean isParallelTo(final Vector3D rhs)
	{
		if (x % rhs.x != 0 && rhs.x % x != 0)
		{
			return false;
		}
		else if (y % rhs.y != 0 && rhs.y % y != 0)
		{
			return false;
		}
		else if (z % rhs.z != 0 && rhs.z % z != 0)
		{
			return false;
		}
		
		return true;
	}

	/**
	 * Equivalent to C++ "+=" operator
	 * Compound assignment; adds a vector v to this.
	 * @param rhs The vector to be added.
	 * @return this.
	 */
	public Vector3D add(final Vector3D rhs)
	{
		x += rhs.x;
		y += rhs.y;
		z += rhs.z;
		
		return this;
	}
	
	// Equivalent to C++ "-=" operator
	public Vector3D subtract(final Vector3D vector)
	{
		add(negative(vector));
		
		return this;
	}

	// Equivalent to C++ "*=" operator
	public Vector3D multiply(final int scalar)
	{
		x *= scalar;
		y *= scalar;
		z *= scalar;
		
		return this;
	}
	
	// Equivalent to C++ "*=" operator
	public Vector3D multiply(final Vector3D vector)
	{
		x *= vector.x;
		y *= vector.y;
		z *= vector.z;
		
		return this;
	}
	
	// Equivalent to C++ '=' operator
	public Vector3D assign(final Vector3D vector)
	{
		x = vector.x;
		y = vector.y;
		z = vector.z;
		
		return this;
	}
	
	// Equivalent to C++ "==" operator
	public boolean equals(final Vector3D vector)
	{
		if (x != vector.x)
		{
			return false;
		}
		
		if (y != vector.y)
		{
			return false;
		}
		
		if (z != vector.z)
		{
			return false;
		}
		
		return true;
	}
	
	// Equivalent to C++ unary '-' operator
	public static Vector3D negative(final Vector3D vector)
	{
		return new Vector3D(-vector.x, -vector.y, -vector.z);
	}
	
	/**
	 * Unit vector i
	 * @return A vector synonymous to the mathematical vector i
	 * i.e (1, 0, 0).
	 */
	public static Vector3D i()
	{
		return new Vector3D(1, 0, 0);
	}
	
	/**
	 * Unit vector j
	 * @return A vector synonymous to the mathematical vector j
	 * i.e. (0, 1, 0).
	 */
	public static Vector3D j()
	{
		return new Vector3D(0, 1, 0);
	}
	
	/**
	 * Unit vector k
	 * @return A vector synonymous to the mathematical vector k
	 * i.e. (0, 0, 1).
	 */
	public static Vector3D k()
	{
		return new Vector3D(0, 0, 1);
	}
	
	public static Vector3D unitVector(final Vector3D vector)
	{
		Vector3D v = new Vector3D(vector);
		v.multiply(1/v.size());
		return v;
	}
	
	public static int dotProduct(final Vector3D lhs, final Vector3D rhs)
	{
		return ((lhs.x * rhs.x) + (lhs.y * rhs.y) + (lhs.z * rhs.z));
	}
	
	public static Vector3D crossProduct(final Vector3D lhs, final Vector3D rhs)
	{
		Vector3D result = new Vector3D(0, 0, 0);
		
		result.x = (lhs.y * rhs.z) + (lhs.z * rhs.y);
		result.y = (lhs.x * rhs.z) + (lhs.z * rhs.x);
		result.z = (lhs.x * rhs.y) + (lhs.y * rhs.x);
		
		return result;
	}

	// TODO check this
	// this now properly returns a double instead of an int, but may not be correct
	public static double angleBetween(final Vector3D lhs, final Vector3D rhs)
	{
		return Math.acos(dotProduct(lhs, rhs) / (lhs.size() * rhs.size()));
	}
	
	// Equivalent to C++ '+' operator
	public static Vector3D sum(final Vector3D lhs, final Vector3D rhs)
	{
		Vector3D vector = new Vector3D(lhs);
		vector.add(rhs);
		return vector;
	}
	
	// Equivalent to C++ '-' operator
	public static Vector3D difference(final Vector3D lhs, final Vector3D rhs)
	{
		return sum(lhs, negative(rhs));
	}
	
	// Equivalent to C++ '*' operator
	public static Vector3D product(final Vector3D lhs, final Vector3D rhs)
	{
		Vector3D vector = new Vector3D(lhs);
		vector.multiply(rhs);
		return vector;
	}
	
	public static double distance(final Vector3D u, final Vector3D v)
	{
		double d = Math.pow(u.getX() - v.getX(), 2) + Math.pow(u.getY() - v.getY(), 2) + Math.pow(u.getZ() - v.getZ(), 2);
		return Math.sqrt(d);
	}
}