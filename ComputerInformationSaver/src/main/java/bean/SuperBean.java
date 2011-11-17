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

	// deleted-flag (0 = active, 1 = deleted)
	private Short deleted;

	// technical ID
	private Integer id;

	// version of the data
	private Integer version;

	/**
	 * Constructor
	 */
	public SuperBean() {
		this.deleted = 0;
		this.version = 1;
	}

	/**
	 * Returns the deleted.
	 * 
	 * @return Returns the deleted.
	 */
	public Short getDeleted() {
		return this.deleted;
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
	 * Sets the deleted field with given deleted.
	 * 
	 * @param deleted
	 *            The deleted to set.
	 */
	public void setDeleted(final Short deleted) {
		this.deleted = deleted;
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

	/**
	 * TODO tmy (17.11.2011): Insert javadoc for method bean.bean.SuperBean.toString.
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
		builder.append("SuperBean [deleted=");
		builder.append(this.deleted);
		builder.append(", id=");
		builder.append(this.id);
		builder.append(", version=");
		builder.append(this.version);
		builder.append("]");
		return builder.toString();
	}

}
