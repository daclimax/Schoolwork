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
	String computerName;

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
	public String getComputerName() {
		return this.computerName;
	}

	/**
	 * Sets the computerName field with given computerName.
	 * 
	 * @param computerName
	 *            The computerName to set.
	 */
	public void setComputerName(final String computerName) {
		this.computerName = computerName;
	}

}
