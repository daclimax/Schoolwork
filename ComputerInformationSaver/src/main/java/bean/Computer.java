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

	/**
	 * TODO tmy (17.11.2011): Insert javadoc for method bean.bean.Computer.toString.
	 * <p>
	 * </p>
	 * 
	 * @return
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Computer [computerName=");
		builder.append(this.computerName);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
