package bean;

/**
 * NetworkInterfaceCard bean
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

public class NetworkInterfaceCard extends SuperBean {

	// references COMPUTER.COM_ID
	private Integer computerId;

	// dns server ip address
	private String dns;

	// domain name
	private String domain;

	// gateway ip address
	private String gateway;

	// ip address
	private String ipAddress;

	// mac address
	private String macAddress;

	// subnet mask
	private String subnetMask;

	/**
	 * Constructor
	 */
	public NetworkInterfaceCard() {
		super();
	}

	/**
	 * Returns the computerId.
	 * 
	 * @return Returns the computerId.
	 */
	public Integer getComputerId() {
		return this.computerId;
	}

	/**
	 * Returns the dns.
	 * 
	 * @return Returns the dns.
	 */
	public String getDns() {
		return this.dns;
	}

	/**
	 * Returns the domain.
	 * 
	 * @return Returns the domain.
	 */
	public String getDomain() {
		return this.domain;
	}

	/**
	 * Returns the gateway.
	 * 
	 * @return Returns the gateway.
	 */
	public String getGateway() {
		return this.gateway;
	}

	/**
	 * Returns the ipAddress.
	 * 
	 * @return Returns the ipAddress.
	 */
	public String getIpAddress() {
		return this.ipAddress;
	}

	/**
	 * Returns the macAddress.
	 * 
	 * @return Returns the macAddress.
	 */
	public String getMacAddress() {
		return this.macAddress;
	}

	/**
	 * Returns the subnetMask.
	 * 
	 * @return Returns the subnetMask.
	 */
	public String getSubnetMask() {
		return this.subnetMask;
	}

	/**
	 * Sets the computerId field with given computerId.
	 * 
	 * @param computerId
	 *            The computerId to set.
	 */
	public void setComputerId(final Integer computerId) {
		this.computerId = computerId;
	}

	/**
	 * Sets the dns field with given dns.
	 * 
	 * @param dns
	 *            The dns to set.
	 */
	public void setDns(final String dns) {
		this.dns = dns;
	}

	/**
	 * Sets the domain field with given domain.
	 * 
	 * @param domain
	 *            The domain to set.
	 */
	public void setDomain(final String domain) {
		this.domain = domain;
	}

	/**
	 * Sets the gateway field with given gateway.
	 * 
	 * @param gateway
	 *            The gateway to set.
	 */
	public void setGateway(final String gateway) {
		this.gateway = gateway;
	}

	/**
	 * Sets the ipAddress field with given ipAddress.
	 * 
	 * @param ipAddress
	 *            The ipAddress to set.
	 */
	public void setIpAddress(final String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Sets the macAddress field with given macAddress.
	 * 
	 * @param macAddress
	 *            The macAddress to set.
	 */
	public void setMacAddress(final String macAddress) {
		this.macAddress = macAddress;
	}

	/**
	 * Sets the subnetMask field with given subnetMask.
	 * 
	 * @param subnetMask
	 *            The subnetMask to set.
	 */
	public void setSubnetMask(final String subnetMask) {
		this.subnetMask = subnetMask;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("\nMAC: " + this.macAddress);
		builder.append("\nComputer ID: " + this.computerId);
		builder.append("\nIP: " + this.ipAddress);
		builder.append("\nSubnetz Maske: " + this.subnetMask);
		builder.append("\nDNS: " + this.dns);
		builder.append("\nGateway: " + this.gateway);
		builder.append("\nDomäne: " + this.domain);
		builder.append(super.toString());
		return builder.toString();
	}

}
