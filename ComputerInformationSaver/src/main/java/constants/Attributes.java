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

public class Attributes {

	// Map for the table-field description (COMPUTER)
	public static Map<String, String> COMPUTER_FIELD_DESCRIPTION_MAP = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			this.put("COM_ID", "Computer ID: ");
			this.put("COM_NAME", "Computer name: ");
		}
	};

	// Map for the field table mapping
	public static Map<String, String> FIELD_TABLE_MAP = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			// fill the map with the mapping
			// table COMPUTER
			this.put("COM_ID", "COMPUTER");
			this.put("COM_NAME", "COMPUTER");
			// table NETWORK_INTERFACE_CARD
			this.put("NIC_ID", "NETWORK_INTERFACE_CARD");
			this.put("NIC_COM_ID", "NETWORK_INTERFACE_CARD");
			this.put("NIC_MAC_ADDRESS", "NETWORK_INTERFACE_CARD");
			this.put("NIC_IP_ADDRESS", "NETWORK_INTERFACE_CARD");
			this.put("NIC_SUBNET_MASK", "NETWORK_INTERFACE_CARD");
			this.put("NIC_DNS", "NETWORK_INTERFACE_CARD");
			this.put("NIC_GATEWAY", "NETWORK_INTERFACE_CARD");
			this.put("NIC_DOMAIN", "NETWORK_INTERFACE_CARD");
			// table OPERATING_SYSTEM
			this.put("OSY_ID", "OPERATING_SYSTEM");
			this.put("OSY_NAME", "OPERATING_SYSTEM");
			this.put("OSY_DESCRIPTION", "OPERATING_SYSTEM");
			// table SOFTWARE
			this.put("SOF_ID", "SOFTWARE");
			this.put("SOF_NAME", "SOFTWARE");
			this.put("SOF_DESCRIPTION", "SOFTWARE");
			this.put("SOF_VERSION_NUMBER", "SOFTWARE");
		}
	};

	// Map for the table-field description (NETWORK_INTERFACE_CARD)
	public static Map<String, String> NIC_FIELD_DESCRIPTION_MAP = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			this.put("NIC_ID", "Network interface card ID: ");
			this.put("NIC_COM_ID", "Network interface card computer ID: ");
			this.put("NIC_MAC_ADDRESS", "Network interface card MAC: ");
			this.put("NIC_IP_ADDRESS", "Network interface card IP: ");
			this.put("NIC_SUBNET_MASK", "Network interface card subnet mask: ");
			this.put("NIC_DNS", "Network interface card dns: ");
			this.put("NIC_GATEWAY", "Network interface card gateway: ");
			this.put("NIC_DOMAIN", "Network interface card domain: ");
		}
	};

	// Map for the table-field description (OPERATING_SYSTEM)
	public static Map<String, String> OPERATING_SYSTEM_FIELD_DESCRIPTION_MAP = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			this.put("OSY_ID", "Operating system ID: ");
			this.put("OSY_NAME", "Operating system name: ");
			this.put("OSY_DESCRIPTION", "Operating system description: ");
		}
	};

	// Map for the table-field description (SOFTWARE)
	public static Map<String, String> SOFTWARE_FIELD_DESCRIPTION_MAP = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			this.put("SOF_ID", "Software ID: ");
			this.put("SOF_NAME", "Software name: ");
			this.put("SOF_DESCRIPTION", "Software description: ");
			this.put("SOF_VERSION_NUMBER", "Software version number: ");
		}
	};

}
