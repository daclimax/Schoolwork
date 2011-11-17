package bean;

/**
 * operatingSystem bean
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

public class OperatingSystem extends SuperBean {

	// description
	private String description;

	// name
	private String name;

	/**
	 * Constructor
	 */
	public OperatingSystem() {
		super();
	}

	/**
	 * Returns the description.
	 * 
	 * @return Returns the description.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Returns the name.
	 * 
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the description field with given description.
	 * 
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Sets the name field with given name.
	 * 
	 * @param name
	 *            The name to set.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * TODO tmy (17.11.2011): Insert javadoc for method bean.bean.OperatingSystem.toString.
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
		builder.append("OperatingSystem [description=");
		builder.append(this.description);
		builder.append(", name=");
		builder.append(this.name);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
