package constants;

import java.util.HashMap;
import java.util.Map;

/**
 * creating a constant map with field (key) - table (value) mapping
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

public class SearchAttributes {

	// Map for the field table mapping
	public static final Map<String, String> FIELD_TABLE_MAP = new HashMap<String, String>();

	/**
	 * Constructor
	 */
	public SearchAttributes() {

		// table COMPUTER
		SearchAttributes.FIELD_TABLE_MAP.put("COM_ID", "COMPUTER");
		SearchAttributes.FIELD_TABLE_MAP.put("COM_NAME", "COMPUTER");

		// table NETWORK_INTERFACE_CARD
		SearchAttributes.FIELD_TABLE_MAP.put("NIC_ID", "NETWORK_INTERFACE_CARD");
		SearchAttributes.FIELD_TABLE_MAP.put("NIC_MAC_ADDRESS", "NETWORK_INTERFACE_CARD");
		SearchAttributes.FIELD_TABLE_MAP.put("NIC_IP_ADDRESS", "NETWORK_INTERFACE_CARD");
		SearchAttributes.FIELD_TABLE_MAP.put("NIC_SUBNET_MASK", "NETWORK_INTERFACE_CARD");
		SearchAttributes.FIELD_TABLE_MAP.put("NIC_DNS", "NETWORK_INTERFACE_CARD");
		SearchAttributes.FIELD_TABLE_MAP.put("NIC_GATEWAY", "NETWORK_INTERFACE_CARD");
		SearchAttributes.FIELD_TABLE_MAP.put("NIC_DOMAIN", "NETWORK_INTERFACE_CARD");

		// table OPERATING_SYSTEM
		SearchAttributes.FIELD_TABLE_MAP.put("OSY_ID", "OPERATING_SYSTEM");
		SearchAttributes.FIELD_TABLE_MAP.put("OSY_NAME", "OPERATING_SYSTEM");
		SearchAttributes.FIELD_TABLE_MAP.put("OSY_DESCRIPTION", "OPERATING_SYSTEM");

		// table SOFTWARE
		SearchAttributes.FIELD_TABLE_MAP.put("SOF_ID", "SOFTWARE");
		SearchAttributes.FIELD_TABLE_MAP.put("SOF_NAME", "SOFTWARE");
		SearchAttributes.FIELD_TABLE_MAP.put("SOF_DESCRIPTION", "SOFTWARE");
		SearchAttributes.FIELD_TABLE_MAP.put("SOF_VERSION_NUMBER", "SOFTWARE");

	}

}
