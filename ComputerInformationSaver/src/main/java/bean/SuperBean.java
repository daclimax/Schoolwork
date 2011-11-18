package bean;

/**
 * contains common attributes for the beans
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

public class SuperBean {

	// technical ID
	private Integer id;

	// version of the data
	private Integer version;

	/**
	 * Constructor
	 */
	public SuperBean() {
		this.version = 1;
	}

	/**
	 * Returns the id.
	 * 
	 * @return Returns the id.
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Returns the version.
	 * 
	 * @return Returns the version.
	 */
	public Integer getVersion() {
		return this.version;
	}

	/**
	 * Sets the id field with given id.
	 * 
	 * @param id
	 *            The id to set.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Sets the version field with given version.
	 * 
	 * @param version
	 *            The version to set.
	 */
	public void setVersion(final Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("SuperBean [id=");
		builder.append(this.id);
		builder.append(", version=");
		builder.append(this.version);
		builder.append("]");
		return builder.toString();
	}

}
