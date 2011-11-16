package bean;

/**
 * software bean
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

public class Software extends SuperBean {

	// description
	private String description;

	// name
	private String name;

	// version of the software (it's a String because of some developers who call their versions something like
	// 'premium' or s.th. else
	private String versionNumber;

	/**
	 * Constructor
	 */
	public Software() {
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
	 * Returns the versionNumber.
	 * 
	 * @return Returns the versionNumber.
	 */
	public String getVersionNumber() {
		return this.versionNumber;
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
	 * Sets the versionNumber field with given versionNumber.
	 * 
	 * @param versionNumber
	 *            The versionNumber to set.
	 */
	public void setVersionNumber(final String versionNumber) {
		this.versionNumber = versionNumber;
	}
}
