package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Computer;
import bean.NetworkInterfaceCard;
import bean.OperatingSystem;
import bean.SearchResult;
import bean.Software;

/**
 * Helper class for the mapping resultSet -> Bean
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

public class ResultSetBeanMapper {

	/**
	 * maps the result set into the Computer-bean
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param resultSet
	 * @return list of Computer objects
	 * @throws SQLException
	 */
	public static List<Computer> mapResultSetToComputerBean(final ResultSet resultSet) throws SQLException {
		final List<Computer> searchResults = new LinkedList<Computer>();

		while (resultSet.next()) {
			final Computer computer = new Computer();

			computer.setId(resultSet.getInt("COM_ID"));
			computer.setName(resultSet.getString("COM_NAME"));
			searchResults.add(computer);
		}

		return searchResults;
	}

	/**
	 * maps the result set into the NetworkInterfaceCard-bean
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param resultSet
	 * @return list of NetworkInterfaceCard objects
	 * @throws SQLException
	 */
	public static List<NetworkInterfaceCard> mapResultSetToNetworkInterfaceCardBean(final ResultSet resultSet)
			throws SQLException {
		final List<NetworkInterfaceCard> searchResults = new LinkedList<NetworkInterfaceCard>();

		while (resultSet.next()) {
			final NetworkInterfaceCard networkInterfaceCard = new NetworkInterfaceCard();

			networkInterfaceCard.setId(resultSet.getInt("NIC_ID"));
			networkInterfaceCard.setComputerId(resultSet.getInt("NIC_COM_ID"));
			networkInterfaceCard.setMacAddress(resultSet.getString("NIC_MAC_ADDRESS"));
			networkInterfaceCard.setIpAddress(resultSet.getString("NIC_IP_ADDRESS"));
			networkInterfaceCard.setSubnetMask(resultSet.getString("NIC_SUBNET_MASK"));
			networkInterfaceCard.setDns(resultSet.getString("NIC_DNS"));
			networkInterfaceCard.setGateway(resultSet.getString("NIC_GATEWAY"));
			networkInterfaceCard.setDomain(resultSet.getString("NIC_DOMAIN"));
			searchResults.add(networkInterfaceCard);
		}

		return searchResults;
	}

	/**
	 * maps the result set into the OperatingSystem-bean
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param resultSet
	 * @return list of OperatingSystem objects
	 * @throws SQLException
	 */
	public static List<OperatingSystem> mapResultSetToOperatingSystemBean(final ResultSet resultSet)
			throws SQLException {
		final List<OperatingSystem> searchResults = new LinkedList<OperatingSystem>();

		while (resultSet.next()) {
			final OperatingSystem operatingSystem = new OperatingSystem();

			operatingSystem.setId(resultSet.getInt("OSY_ID"));
			operatingSystem.setName(resultSet.getString("OSY_NAME"));
			operatingSystem.setDescription(resultSet.getString("OSY_DESCRIPTION"));
			searchResults.add(operatingSystem);
		}

		return searchResults;
	}

	/**
	 * maps the result set into the SearchResult-bean
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param resultSet
	 * @return list of SearchResult objects
	 * @throws SQLException
	 */
	public static List<SearchResult> mapResultSetToResultBean(final ResultSet resultSet) throws SQLException {
		final List<SearchResult> searchResults = new LinkedList<SearchResult>();

		while (resultSet.next()) {
			final SearchResult searchResult = new SearchResult();

			searchResult.setComputerId(resultSet.getInt("COM_ID"));
			searchResult.setComputerName(resultSet.getString("COM_NAME"));
			searchResult.setNicId(resultSet.getInt("NIC_ID"));
			searchResult.setNicMacAddress(resultSet.getString("NIC_MAC_ADDRESS"));
			searchResult.setNicIpAddress(resultSet.getString("NIC_IP_ADDRESS"));
			searchResult.setNicSubnetMask(resultSet.getString("NIC_SUBNET_MASK"));
			searchResult.setNicDns(resultSet.getString("NIC_DNS"));
			searchResult.setNicGateway(resultSet.getString("NIC_GATEWAY"));
			searchResult.setNicDomain(resultSet.getString("NIC_DOMAIN"));
			searchResult.setOsId(resultSet.getInt("OSY_ID"));
			searchResult.setOsName(resultSet.getString("OSY_NAME"));
			searchResult.setOsDescription(resultSet.getString("OSY_DESCRIPTION"));
			searchResult.setSoftwareId(resultSet.getInt("SOF_ID"));
			searchResult.setSoftwareName(resultSet.getString("SOF_NAME"));
			searchResult.setSoftwareDescription(resultSet.getString("SOF_DESCRIPTION"));
			searchResult.setSoftwareVersionNumber(resultSet.getString("SOF_VERSION_NUMBER"));

			searchResults.add(searchResult);
		}

		return searchResults;
	}

	/**
	 * maps the result set into the OperatingSystem-bean
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param resultSet
	 * @return List of Software objects
	 * @throws SQLException
	 */
	public static List<Software> mapResultSetToSoftwareBean(final ResultSet resultSet) throws SQLException {
		final List<Software> searchResults = new LinkedList<Software>();

		while (resultSet.next()) {
			final Software software = new Software();

			software.setId(resultSet.getInt("SOF_ID"));
			software.setName(resultSet.getString("SOF_NAME"));
			software.setVersionNumber(resultSet.getString("SOF_VERSION_NUMBER"));
			software.setDescription(resultSet.getString("SOF_DESCRIPTION"));
			searchResults.add(software);
		}

		return searchResults;
	}
}
