package impl;

import helper.ValidationHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import service.IDatabaseOperations;
import service.ILogicController;
import bean.NetworkInterfaceCard;
import bean.SearchResult;
import bean.SuperBean;

/**
 * Logic implementation
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

public class LogicImpl implements ILogicController {

	// locale for the resourceBundle
	private static Locale de_DE = new Locale("de", "DE");

	// messages
	private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages.messages_de_DE", LogicImpl.de_DE);

	// dataService
	private final IDatabaseOperations dataRemote;

	/**
	 * Constructor
	 */
	public LogicImpl() {
		this.dataRemote = new SQLiteImpl();
	}

	/** {@inheritDoc} */
	public List<String> deleteComponent(final SuperBean component) {
		final List<String> messages = new LinkedList<String>();

		// just delete this component, no validations are needed
		this.dataRemote.deleteData(component);
		messages.add(LogicImpl.MESSAGES.getString("success"));

		return messages;
	}

	/** {@inheritDoc} */
	public List<String> insertNewComponent(final SuperBean component, final Integer computerId) {
		final List<String> messages = new LinkedList<String>();
		Boolean isValidationCorrect = true;

		// validation for networkInterfaceCard, the other components don't need a validation
		if (component instanceof NetworkInterfaceCard) {
			// in case of a networkInterfaceCard validate the ipAddresses and the macAddress

			if ((!((NetworkInterfaceCard) component).getIpAddress().equals(""))
					&& !ValidationHelper.ipAddressValidation(((NetworkInterfaceCard) component).getIpAddress())) {
				messages.add(LogicImpl.MESSAGES.getString("nic.ipAddressError"));
				isValidationCorrect = false;
			}
			if (!(((NetworkInterfaceCard) component).getSubnetMask().equals(""))
					&& !ValidationHelper.ipAddressValidation(((NetworkInterfaceCard) component).getSubnetMask())) {
				messages.add(LogicImpl.MESSAGES.getString("nic.subnetMaskError"));
				isValidationCorrect = false;
			}
			if (!(((NetworkInterfaceCard) component).getGateway().equals(""))
					&& !ValidationHelper.ipAddressValidation(((NetworkInterfaceCard) component).getGateway())) {
				messages.add(LogicImpl.MESSAGES.getString("nic.gatewayError"));
				isValidationCorrect = false;
			}
			if (!(((NetworkInterfaceCard) component).getDns().equals(""))
					&& !ValidationHelper.ipAddressValidation(((NetworkInterfaceCard) component).getDns())) {
				messages.add(LogicImpl.MESSAGES.getString("nic.dnsError"));
				isValidationCorrect = false;
			}
		}

		if (isValidationCorrect) {
			this.dataRemote.insertNewData(component, computerId);
			messages.add(LogicImpl.MESSAGES.getString("success"));
		} else {
			messages.add(LogicImpl.MESSAGES.getString("failure"));
		}

		return messages;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	public List<SuperBean> searchComponentsByAttributes(final Map<String, String> searchAttributes,
			final String searchType) {
		// at this point don't know of what type the list is
		List<? extends SuperBean> searchResults = new LinkedList<SuperBean>();

		if (searchType.equals("computer")) { // computer search
			searchResults = this.dataRemote.searchComputerByAttributes(searchAttributes);
		} else if (searchType.equals("nic")) { // networkInterfaceCard search
			searchResults = this.dataRemote.searchNetworkInterfaceCardByAttributes(searchAttributes);
		} else if (searchType.equals("operatingSystem")) { // operatingSystem search
			searchResults = this.dataRemote.searchOperatingSystemByAttributes(searchAttributes);
		} else if (searchType.equals("software")) { // software search
			searchResults = this.dataRemote.searchSoftwareByAttributes(searchAttributes);
		}

		// output a warning when the list of results is empty.
		if (searchResults.size() == 0) {
			System.out.println(LogicImpl.MESSAGES.getString("empty"));
		}
		return (List<SuperBean>) searchResults;
	}

	/** {@inheritDoc} */
	public List<SearchResult> searchDataByAttributes(final Map<String, String> searchAttributes) {
		// just search with attributes
		final List<SearchResult> searchResults = this.dataRemote.searchDataByAttributes(searchAttributes);

		// output a warning when the list of results is empty.
		if (searchResults.size() == 0) {
			System.out.println(LogicImpl.MESSAGES.getString("empty"));
		}

		return searchResults;
	}

	/** {@inheritDoc} */
	public List<String> updateComponent(final SuperBean component) {
		final List<String> messages = new LinkedList<String>();
		Boolean isValidationCorrect = true;

		// validation for networkInterfaceCard, the other components don't need a validation
		if (component instanceof NetworkInterfaceCard) {
			// in case of a networkInterfaceCard validate the ipAddresses and the macAddress

			if (!(((NetworkInterfaceCard) component).getIpAddress().equals(""))
					&& !ValidationHelper.ipAddressValidation(((NetworkInterfaceCard) component).getIpAddress())) {
				messages.add(LogicImpl.MESSAGES.getString("nic.ipAddressError"));
				isValidationCorrect = false;
			}
			if (!(((NetworkInterfaceCard) component).getSubnetMask().equals(""))
					&& !ValidationHelper.ipAddressValidation(((NetworkInterfaceCard) component).getSubnetMask())) {
				messages.add(LogicImpl.MESSAGES.getString("nic.subnetMaskError"));
				isValidationCorrect = false;
			}
			if (!(((NetworkInterfaceCard) component).getGateway().equals(""))
					&& !ValidationHelper.ipAddressValidation(((NetworkInterfaceCard) component).getGateway())) {
				messages.add(LogicImpl.MESSAGES.getString("nic.gatewayError"));
				isValidationCorrect = false;
			}
			if (!(((NetworkInterfaceCard) component).getDns().equals(""))
					&& !ValidationHelper.ipAddressValidation(((NetworkInterfaceCard) component).getDns())) {
				messages.add(LogicImpl.MESSAGES.getString("nic.dnsError"));
				isValidationCorrect = false;
			}
		}

		if (isValidationCorrect) {
			this.dataRemote.updateData(component);
			messages.add(LogicImpl.MESSAGES.getString("success"));
		} else {
			messages.add(LogicImpl.MESSAGES.getString("failure"));
		}

		return messages;
	}

}
