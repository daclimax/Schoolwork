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

}
