package impl;

import helper.SQLiteJDBCHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import service.IDatabaseOperations;
import bean.Computer;
import bean.NetworkInterfaceCard;
import bean.OperatingSystem;
import bean.SearchResult;
import bean.Software;
import bean.SuperBean;
import constants.Attributes;

/**
 * executes the queries select, update, insert and delete for a SQLite database
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

public class SQLiteImpl implements IDatabaseOperations {

	// the like sign for SQL
	private static final String JOKER_SIGN_FOR_SQL = "%";

	// the connection to the database
	private Connection connection;

	// the statement to execute queries
	private Statement statement;

	/**
	 * 
	 * checks whether the searchAttribute contains a "*" for the like-search
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param searchAttribute
	 * @return returns " = "
	 */
	private static String getLikeOrEqualSign(final String searchAttribute) {
		String likeOrEqualSign = " = ";

		if (searchAttribute.contains("*")) {
			likeOrEqualSign = " LIKE ";
		}

		return likeOrEqualSign;
	}

	/**
	 * 
	 * because sql uses for the like-search a "%", this method replaces the "*" with a "%"
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param searchAttribute
	 * @return
	 */
	private static String getLikeSignForSQL(String searchValue) {
		searchValue = searchValue.replaceAll("\\*", SQLiteImpl.JOKER_SIGN_FOR_SQL);

		return searchValue;
	}

	/**
	 * maps the result set into the SearchResult-bean
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private static List<SearchResult> mapResultSetToResultBean(final ResultSet resultSet) throws SQLException {
		final List<SearchResult> searchResults = new LinkedList<SearchResult>();

		while (resultSet.next()) {
			final SearchResult searchResult = new SearchResult();

			searchResult.setComputerId(resultSet.getInt("COM_ID"));
			searchResult.setComputerName(resultSet.getString("COM_NAME"));
			searchResult.setNicMacAddress(resultSet.getString("NIC_MAC_ADDRESS"));
			searchResult.setNicIpAddress(resultSet.getString("NIC_IP_ADDRESS"));
			searchResult.setNicSubnetMask(resultSet.getString("NIC_SUBNET_MASK"));
			searchResult.setNicDns(resultSet.getString("NIC_DNS"));
			searchResult.setNicGateway(resultSet.getString("NIC_GATEWAY"));
			searchResult.setNicDomain(resultSet.getString("NIC_DOMAIN"));
			searchResult.setOsName(resultSet.getString("OSY_NAME"));
			searchResult.setOsDescription(resultSet.getString("OSY_DESCRIPTION"));
			searchResult.setSoftwareName(resultSet.getString("SOF_NAME"));
			searchResult.setSoftwareDescription(resultSet.getString("SOF_DESCRIPTION"));
			searchResult.setSoftwareVersionNumber(resultSet.getString("SOF_VERSION_NUMBER"));

			searchResults.add(searchResult);
		}

		return searchResults;
	}

	/**
	 * {@inheritDoc}
	 */
	public Boolean deleteData(final SuperBean superBean) {

		Boolean isDeleteSuccessful = false;

		// check of what bean-type the handed bean is (to handle different beans in different ways)
		if (superBean instanceof Computer) {
			isDeleteSuccessful = this.insertNewData(superBean);
		} else if (superBean instanceof NetworkInterfaceCard) {
			isDeleteSuccessful = this.insertNewData(superBean);
		} else if (superBean instanceof OperatingSystem) {
			isDeleteSuccessful = this.insertNewData(superBean);
		} else if (superBean instanceof Software) {
			isDeleteSuccessful = this.insertNewData(superBean);
		}
		return isDeleteSuccessful;
	}

	/**
	 * {@inheritDoc}
	 */
	public Boolean insertNewData(final SuperBean superBean) {

		Boolean isInsertSuccessful = false;

		// check of what bean-type the handed bean is (to handle different beans in different ways)
		if (superBean instanceof Computer) {
			isInsertSuccessful = this.insertNewData(superBean);
		} else if (superBean instanceof NetworkInterfaceCard) {
			isInsertSuccessful = this.insertNewData(superBean);
		} else if (superBean instanceof OperatingSystem) {
			isInsertSuccessful = this.insertNewData(superBean);
		} else if (superBean instanceof Software) {
			isInsertSuccessful = this.insertNewData(superBean);
		}
		return isInsertSuccessful;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SearchResult> searchDataByAttributes(final Map<String, String> searchAttributes) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// standard query for the search
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT com_id, ");
		queryString.append("       com_name, ");
		queryString.append("       nic_mac_address, ");
		queryString.append("       nic_ip_address, ");
		queryString.append("       nic_subnet_mask, ");
		queryString.append("       nic_dns, ");
		queryString.append("       nic_gateway, ");
		queryString.append("       nic_domain, ");
		queryString.append("       osy_name, ");
		queryString.append("       osy_description, ");
		queryString.append("       sof_name, ");
		queryString.append("       sof_description, ");
		queryString.append("       sof_version_number ");
		queryString.append("FROM   computer ");
		queryString.append("       INNER JOIN network_interface_card ");
		queryString.append("         ON com_id = nic_com_id ");
		queryString.append("       INNER JOIN mapping_computer_operating_system ");
		queryString.append("         ON com_id = mco_com_id ");
		queryString.append("       INNER JOIN operating_system ");
		queryString.append("         ON mco_osy_id = osy_id ");
		queryString.append("       INNER JOIN mapping_computer_software ");
		queryString.append("         ON com_id = mcs_com_id ");
		queryString.append("       INNER JOIN software ");
		queryString.append("         ON mcs_sof_id = sof_id ");

		// build the where-part of the query
		queryString.append("WHERE COMPUTER.COM_DELETED = 0");
		queryString.append(" AND NETWORK_INTERFACE_CARD.NIC_DELETED = 0");
		queryString.append(" AND OPERATING_SYSTEM.OSY_DELETED = 0");
		queryString.append(" AND SOFTWARE.SOF_DELETED = 0");

		if (searchAttributes.size() > 0) {
			// grab the fieldName by given attributes
			for (final String fieldName : Attributes.FIELD_TABLE_MAP.keySet()) {

				if (searchAttributes.containsKey(fieldName)) {
					queryString.append(" AND " + fieldName
							+ SQLiteImpl.getLikeOrEqualSign(searchAttributes.get(fieldName)) + "'"
							+ SQLiteImpl.getLikeSignForSQL(searchAttributes.get(fieldName)) + "'");
				}
			}
		}
		queryString.append("");

		List<SearchResult> results = null;

		try {
			// TODO: comment it.
			final ResultSet resultSet = this.statement.executeQuery(queryString.toString());
			results = SQLiteImpl.mapResultSetToResultBean(resultSet);
			resultSet.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		SQLiteJDBCHelper.closeConnection(this.connection);

		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	public Boolean updateData(final SuperBean superBean) {

		Boolean isUpdateSuccessful = false;

		// check of what bean-type the handed bean is (to handle different beans in different ways)
		if (superBean instanceof Computer) {
			isUpdateSuccessful = this.updateData(superBean);
		} else if (superBean instanceof NetworkInterfaceCard) {
			isUpdateSuccessful = this.updateData(superBean);
		} else if (superBean instanceof OperatingSystem) {
			isUpdateSuccessful = this.updateData(superBean);
		} else if (superBean instanceof Software) {
			isUpdateSuccessful = this.updateData(superBean);
		}
		return isUpdateSuccessful;
	}

	/**
	 * deletes data in the computer entity
	 */
	private Boolean deleteData(final Computer computer) {
		// TODO: implement delete
		return null;
	}

	/**
	 * deletes data in the network_interface_card entity
	 */
	private Boolean deleteData(final NetworkInterfaceCard networkInterfaceCard) {
		// TODO: implement delete
		return null;
	}

	/**
	 * deletes data in the operating_system entity
	 */
	private Boolean deleteData(final OperatingSystem operatingSystem) {
		// TODO: implement delete
		return null;
	}

	/**
	 * deletes data in the software entity
	 */
	private Boolean deleteData(final Software software) {
		// TODO: implement delete
		return null;
	}

	/**
	 * inserts new data in the computer entity
	 */
	private Boolean insertNewData(final Computer computer) {
		// TODO: implement insert
		return null;
	}

	/**
	 * inserts new data in the network_interface_card entity
	 */
	private Boolean insertNewData(final NetworkInterfaceCard networkInterfaceCard) {
		// TODO: implement insert
		return null;
	}

	/**
	 * inserts new data in the operating_system entity
	 */
	private Boolean insertNewData(final OperatingSystem operatingSystem) {
		// TODO: implement insert
		return null;
	}

	/**
	 * inserts new data in the software entity
	 */
	private Boolean insertNewData(final Software software) {
		// TODO: implement insert
		return null;
	}

	/**
	 * updates data in the computer entity
	 */
	private Boolean updateData(final Computer computer) {
		// TODO: implement update
		return null;
	}

	/**
	 * updates data in the network_interface_card entity
	 */
	private Boolean updateData(final NetworkInterfaceCard networkInterfaceCard) {
		// TODO: implement update
		return null;
	}

	/**
	 * updates data in the operating_system entity
	 */
	private Boolean updateData(final OperatingSystem operatingSystem) {
		// TODO: implement update
		return null;
	}

	/**
	 * updates data in the software entity
	 */
	private Boolean updateData(final Software software) {
		// TODO: implement update
		return null;
	}

}
