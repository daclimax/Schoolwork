package helper;

/**
 * helper for validations (e.g. ip-address validation,..)
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

public class ValidationHelper {

	/**
	 * 
	 * validates an ipAddress
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param ipAddress
	 * @return Boolean isIpCorrect
	 */
	public static Boolean ipAddressValidation(final String ipAddress) {
		Boolean isIpCorrect = false;

		if (ipAddress
				.matches("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")) {
			isIpCorrect = true;
		}
		return isIpCorrect;
	}

	/**
	 * 
	 * validates a macAddress
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param macAddress
	 * @return Boolean isMacCorrect
	 */
	public static Boolean macAddressValidation(final String macAddress) {
		Boolean isMacCorrect = false;

		if (macAddress.matches("^([0-9a-fA-F]{2}[:-]){5}[0-9a-fA-F]{2}$")) {
			isMacCorrect = true;
		}

		return isMacCorrect;
	}
}
