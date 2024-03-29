package bean;

/**
 * computer bean
 * <p>
 * </p>
 * 
 * 
 * <br>
 * &nbsp;
 * 
 * @author ETHALON: tmy
 * 
 */

public class Computer extends SuperBean {

	// name
	String name;

	/**
	 * Constructor
	 */
	public Computer() {
		super();
	}

	/**
	 * Returns the computerName.
	 * 
	 * @return Returns the computerName.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the computerName field with given computerName.
	 * 
	 * @param computerName
	 *            The computerName to set.
	 */
	public void setName(final String computerName) {
		this.name = computerName;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("\nComputer Name: " + this.name);
		builder.append(super.toString());
		return builder.toString();
	}

}
