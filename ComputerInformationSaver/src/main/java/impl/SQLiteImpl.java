package impl;

import helper.ResultSetBeanMapper;
import helper.SQLiteJDBCHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
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
	 * {@inheritDoc}
	 */
	public void deleteData(final SuperBean superBean) {

		// check of what bean-type the handed bean is (to handle different beans in different ways)
		if (superBean instanceof Computer) {
			this.deleteComputer((Computer) superBean);
		} else if (superBean instanceof NetworkInterfaceCard) {
			this.deleteNetworkInterfaceCard((NetworkInterfaceCard) superBean);
		} else if (superBean instanceof OperatingSystem) {
			this.deleteOperatingSystem((OperatingSystem) superBean);
		} else if (superBean instanceof Software) {
			this.deleteSoftware((Software) superBean);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void insertNewData(final SuperBean superBean, final Integer computerId) {

		// check of what bean-type the handed bean is (to handle different beans in different ways)
		if (superBean instanceof Computer) {
			this.insertNewComputer((Computer) superBean);
		} else if (superBean instanceof NetworkInterfaceCard) {
			this.insertNewNetworkInterfaceCard((NetworkInterfaceCard) superBean);
		} else if (superBean instanceof OperatingSystem) {
			this.insertNewOperatingSystem((OperatingSystem) superBean, computerId);
		} else if (superBean instanceof Software) {
			this.insertNewSoftware((Software) superBean, computerId);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Computer> searchComputerByAttributes(final Map<String, String> searchAttributes) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// standard query for the computer search
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT com_id, ");
		queryString.append("       com_name ");
		queryString.append("FROM   computer ");
		// build the where-part of the query
		queryString.append("WHERE  computer.com_deleted = 0 ");

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
		queryString.append("; ");

		List<Computer> results = null;

		try {
			final ResultSet resultSet = this.statement.executeQuery(queryString.toString());
			results = ResultSetBeanMapper.mapResultSetToComputerBean(resultSet);
			resultSet.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
		return results;
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
		queryString.append("       nic_id, ");
		queryString.append("       nic_mac_address, ");
		queryString.append("       nic_ip_address, ");
		queryString.append("       nic_subnet_mask, ");
		queryString.append("       nic_dns, ");
		queryString.append("       nic_gateway, ");
		queryString.append("       nic_domain, ");
		queryString.append("       osy_id, ");
		queryString.append("       osy_name, ");
		queryString.append("       osy_description, ");
		queryString.append("       sof_id, ");
		queryString.append("       sof_name, ");
		queryString.append("       sof_description, ");
		queryString.append("       sof_version_number ");
		queryString.append("FROM   computer ");
		queryString.append("       LEFT JOIN network_interface_card ");
		queryString.append("         ON com_id = nic_com_id ");
		queryString.append("       LEFT JOIN mapping_computer_operating_system ");
		queryString.append("         ON com_id = mco_com_id ");
		queryString.append("       LEFT JOIN operating_system ");
		queryString.append("         ON mco_osy_id = osy_id ");
		queryString.append("       LEFT JOIN mapping_computer_software ");
		queryString.append("         ON com_id = mcs_com_id ");
		queryString.append("       LEFT JOIN software ");
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
		queryString.append("; ");

		List<SearchResult> results = null;

		try {
			final ResultSet resultSet = this.statement.executeQuery(queryString.toString());
			results = ResultSetBeanMapper.mapResultSetToResultBean(resultSet);
			resultSet.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<NetworkInterfaceCard> searchNetworkInterfaceCardByAttributes(final Map<String, String> searchAttributes) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// standard query for the computer search
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT nic_id, ");
		queryString.append("       nic_com_id, ");
		queryString.append("       nic_mac_address, ");
		queryString.append("       nic_ip_address, ");
		queryString.append("       nic_subnet_mask, ");
		queryString.append("       nic_dns, ");
		queryString.append("       nic_gateway, ");
		queryString.append("       nic_domain ");
		queryString.append("FROM   network_interface_card ");

		// build the where-part of the query
		queryString.append("WHERE  network_interface_card.nic_deleted = 0 ");

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
		queryString.append("; ");

		List<NetworkInterfaceCard> results = null;

		try {
			final ResultSet resultSet = this.statement.executeQuery(queryString.toString());
			results = ResultSetBeanMapper.mapResultSetToNetworkInterfaceCardBean(resultSet);
			resultSet.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OperatingSystem> searchOperatingSystemByAttributes(final Map<String, String> searchAttributes) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// standard query for the computer search
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT osy_id, ");
		queryString.append("       osy_name, ");
		queryString.append("       osy_description ");
		queryString.append("FROM   operating_system ");

		// build the where-part of the query
		queryString.append("WHERE  operating_system.osy_deleted = 0 ");

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
		queryString.append("; ");

		List<OperatingSystem> results = null;

		try {
			final ResultSet resultSet = this.statement.executeQuery(queryString.toString());
			results = ResultSetBeanMapper.mapResultSetToOperatingSystemBean(resultSet);
			resultSet.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Software> searchSoftwareByAttributes(final Map<String, String> searchAttributes) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// standard query for the computer search
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT sof_id, ");
		queryString.append("       sof_name, ");
		queryString.append("       sof_description ");
		queryString.append("FROM   software ");

		// build the where-part of the query
		queryString.append("WHERE  software.sof_deleted = 0 ");

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
		queryString.append("; ");

		List<Software> results = null;

		try {
			final ResultSet resultSet = this.statement.executeQuery(queryString.toString());
			results = ResultSetBeanMapper.mapResultSetToSoftwareBean(resultSet);
			resultSet.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateData(final SuperBean superBean) {

		// check of what bean-type the handed bean is (to handle different beans in different ways)
		if (superBean instanceof Computer) {
			this.updateComputer((Computer) superBean);
		} else if (superBean instanceof NetworkInterfaceCard) {
			this.updateNetworkInterfaceCard((NetworkInterfaceCard) superBean);
		} else if (superBean instanceof OperatingSystem) {
			this.updateOperatingSystem((OperatingSystem) superBean);
		} else if (superBean instanceof Software) {
			this.updateSoftware((Software) superBean);
		}
	}

	/**
	 * deletes data in the computer entity
	 */
	private void deleteComputer(final Computer computer) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// set the deleted flag = 1 on computer table
		final StringBuffer queryString = new StringBuffer();
		queryString.append("UPDATE computer ");
		queryString.append("SET    com_deleted = 1");
		queryString.append("WHERE  com_id = " + computer.getId() + "; ");

		// set the deleted flag = 1 on mapping_computer_operating_system table
		final StringBuilder queryStringMappingOperatingSystem = new StringBuilder();
		queryStringMappingOperatingSystem.append("UPDATE mapping_computer_operating_system ");
		queryStringMappingOperatingSystem.append("SET    mco_deleted = 1");
		queryStringMappingOperatingSystem.append("WHERE  mco_com_id = " + computer.getId() + "; ");

		// set the deleted flag = 1 on mapping_computer_software table
		final StringBuilder queryStringMappingSoftware = new StringBuilder();
		queryStringMappingSoftware.append("UPDATE mapping_computer_software ");
		queryStringMappingSoftware.append("SET    mcs_deleted = 1");
		queryStringMappingSoftware.append("WHERE  mcs_com_id = " + computer.getId() + "; ");

		try {
			this.statement.executeUpdate(queryString.toString());
			this.statement.executeUpdate(queryStringMappingOperatingSystem.toString());
			this.statement.executeUpdate(queryStringMappingSoftware.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * deletes data in the network_interface_card entity
	 */
	private void deleteNetworkInterfaceCard(final NetworkInterfaceCard networkInterfaceCard) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// set the deleted flag = 1 on network_interface_card table
		final StringBuilder queryString = new StringBuilder();
		queryString.append("UPDATE network_interface_card ");
		queryString.append("SET    nic_deleted = 1");
		queryString.append("WHERE  nic_id = " + networkInterfaceCard.getId() + "; ");

		try {
			this.statement.executeUpdate(queryString.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * deletes data in the operating_system entity
	 */
	private void deleteOperatingSystem(final OperatingSystem operatingSystem) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// set the deleted flag = 1 on operating_system table
		final StringBuilder queryString = new StringBuilder();
		queryString.append("UPDATE operating_system ");
		queryString.append("SET    osy_deleted = 1");
		queryString.append("WHERE  osy_id = " + operatingSystem.getId() + "; ");

		// set the deleted flag = 1 on mapping_computer_operating_system table
		final StringBuilder queryStringMappingOperatingSystem = new StringBuilder();
		queryStringMappingOperatingSystem.append("UPDATE mapping_computer_operating_system ");
		queryStringMappingOperatingSystem.append("SET    mco_deleted = 1");
		queryStringMappingOperatingSystem.append("WHERE  mco_osy_id = " + operatingSystem.getId() + "; ");

		try {
			this.statement.executeUpdate(queryString.toString());
			this.statement.executeUpdate(queryStringMappingOperatingSystem.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * deletes data in the software entity
	 */
	private void deleteSoftware(final Software software) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// set the deleted flag = 1 on software table
		final StringBuilder queryString = new StringBuilder();
		queryString.append("UPDATE software ");
		queryString.append("SET    sof_deleted = 1");
		queryString.append("WHERE  sof_id = " + software.getId() + "; ");

		// set the deleted flag = 1 on mapping_computer_software table
		final StringBuilder queryStringMappingSoftware = new StringBuilder();
		queryStringMappingSoftware.append("UPDATE mapping_computer_software ");
		queryStringMappingSoftware.append("SET    mcs_deleted = 1");
		queryStringMappingSoftware.append("WHERE  mcs_sof_id = " + software.getId() + "; ");

		try {
			this.statement.executeUpdate(queryString.toString());
			this.statement.executeUpdate(queryStringMappingSoftware.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * inserts new data in the computer entity
	 */
	private void insertNewComputer(final Computer computer) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// just insert the new computer (there's nothing distinct)
		final StringBuffer queryString = new StringBuffer();
		queryString.append("INSERT INTO computer ");
		queryString.append("            (com_id, ");
		queryString.append("             com_name) ");
		queryString.append("VALUES      (NULL, ");
		queryString.append("             '" + computer.getName() + "'); ");

		try {
			this.statement.executeUpdate(queryString.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * inserts new data in the network_interface_card entity
	 */
	private void insertNewNetworkInterfaceCard(NetworkInterfaceCard networkInterfaceCard) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// check whether this network interface card is already in the db (NIC_MAC_ADDRESS should be distinctly), if so
		// do not insert, just update the old entry
		// create HashMap to search for this network interface card
		final HashMap<String, String> searchAttributes = new HashMap<String, String>();
		searchAttributes.put("NIC_MAC_ADDRESS", networkInterfaceCard.getMacAddress());

		// in this list should only be one entry (because of this implemented workaround)
		List<NetworkInterfaceCard> results = this.searchNetworkInterfaceCardByAttributes(searchAttributes);

		final StringBuffer queryString = new StringBuffer();
		// if there's an entry in this list, the network interface card is already in db, just update the old entry
		// with given computerId (and eventually the other data)
		if (results.size() == 0) {
			queryString.append("INSERT INTO network_interface_card ");
			queryString.append("            (nic_id, ");
			queryString.append("             nic_com_id, ");
			queryString.append("             nic_mac_address, ");
			queryString.append("             nic_ip_address, ");
			queryString.append("             nic_subnet_mask, ");
			queryString.append("             nic_dns, ");
			queryString.append("             nic_gateway, ");
			queryString.append("             nic_domain) ");
			queryString.append("VALUES      (NULL, ");
			queryString.append("             '" + networkInterfaceCard.getComputerId() + "', ");
			queryString.append("             '" + networkInterfaceCard.getMacAddress() + "', ");
			queryString.append("             '" + networkInterfaceCard.getIpAddress() + "', ");
			queryString.append("             '" + networkInterfaceCard.getSubnetMask() + "', ");
			queryString.append("             '" + networkInterfaceCard.getDns() + "', ");
			queryString.append("             '" + networkInterfaceCard.getGateway() + "', ");
			queryString.append("             '" + networkInterfaceCard.getDomain() + "'); ");

			// get the just inserted software (need the id for the mapping table)
			results = this.searchNetworkInterfaceCardByAttributes(searchAttributes);
		}

		for (final NetworkInterfaceCard nic : results) {
			networkInterfaceCard = nic;
		}

		try {
			this.statement.executeUpdate(queryString.toString());
			this.updateNetworkInterfaceCard(networkInterfaceCard);
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * inserts new data in the operating_system entity
	 */
	private void insertNewOperatingSystem(OperatingSystem operatingSystem, final Integer computerId) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// check whether this operating system is already in the db, if so do not insert, just map it to the com_id
		// create HashMap to search for this operating system
		final HashMap<String, String> searchAttributes = new HashMap<String, String>();
		searchAttributes.put("OSY_NAME", operatingSystem.getName());

		// in this list should only be one entry (because of this implemented workaround)
		List<OperatingSystem> results = this.searchOperatingSystemByAttributes(searchAttributes);

		final StringBuffer queryString = new StringBuffer();
		// if there's an entry in this list, the operating system is already in db, just fill the mapping
		// with given computerId and operatingSystemId
		if (results.size() == 0) {
			queryString.append("INSERT INTO operating_system ");
			queryString.append("            (osy_id, ");
			queryString.append("             osy_name, ");
			queryString.append("             osy_description) ");
			queryString.append("VALUES      (NULL, ");
			queryString.append("             '" + operatingSystem.getName() + "', ");
			queryString.append("             '" + operatingSystem.getDescription() + "'); ");

			// get the just inserted software (need the id for the mapping table)
			results = this.searchOperatingSystemByAttributes(searchAttributes);
		}

		for (final OperatingSystem os : results) {
			operatingSystem = os;
		}

		// fill the mapping table between software <-> computer
		final StringBuffer queryStringForMapping = new StringBuffer();
		queryStringForMapping.append("INSERT INTO mapping_computer_operating_system ");
		queryStringForMapping.append("            (mco_id, ");
		queryStringForMapping.append("             mco_com_id, ");
		queryStringForMapping.append("             mco_osy_id) ");
		queryStringForMapping.append("VALUES      (NULL, ");
		queryStringForMapping.append("             " + computerId + ", ");
		queryStringForMapping.append("             " + operatingSystem.getId() + "); ");

		try {
			this.statement.executeUpdate(queryString.toString());
			this.statement.executeUpdate(queryStringForMapping.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * inserts new data in the software entity
	 */
	private void insertNewSoftware(Software software, final Integer computerId) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// check whether this software is already in the db, if so do not insert, just map it to the com_id
		// create HashMap to search for this software
		final HashMap<String, String> searchAttributes = new HashMap<String, String>();
		searchAttributes.put("SOF_NAME", software.getName());
		searchAttributes.put("SOF_VERSION_NUMBER", software.getVersionNumber());

		// in this list should only be one entry (because of this implemented workaround)
		List<Software> results = this.searchSoftwareByAttributes(searchAttributes);

		final StringBuffer queryString = new StringBuffer();
		// if there's an entry in this list, the software is already in db, just fill the mapping
		// with given computerId and softwareId
		if (results.size() == 0) {
			queryString.append("INSERT INTO software ");
			queryString.append("            (sof_id, ");
			queryString.append("             sof_name, ");
			queryString.append("             sof_description, ");
			queryString.append("             sof_version_number) ");
			queryString.append("VALUES      (NULL, ");
			queryString.append("             '" + software.getName() + "', ");
			queryString.append("             '" + software.getDescription() + "', ");
			queryString.append("             '" + software.getVersionNumber() + "'); ");

			// get the just inserted software (need the id for the mapping table)
			results = this.searchSoftwareByAttributes(searchAttributes);
		}

		for (final Software sw : results) {
			software = sw;
		}

		// fill the mapping table between software <-> computer
		final StringBuffer queryStringForMapping = new StringBuffer();
		queryStringForMapping.append("INSERT INTO mapping_computer_software ");
		queryStringForMapping.append("            (mcs_id, ");
		queryStringForMapping.append("             mcs_com_id, ");
		queryStringForMapping.append("             mcs_sof_id) ");
		queryStringForMapping.append("VALUES      (NULL, ");
		queryStringForMapping.append("             " + computerId + ", ");
		queryStringForMapping.append("             " + software.getId() + "); ");

		try {
			this.statement.executeUpdate(queryString.toString());
			this.statement.executeUpdate(queryStringForMapping.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * updates data in the computer entity
	 */
	private void updateComputer(final Computer computer) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// the standard query for update Computer
		final StringBuffer queryString = new StringBuffer();
		queryString.append("UPDATE computer ");
		queryString.append("SET    com_version = (SELECT com_version ");
		queryString.append("                      FROM   computer ");
		queryString.append("                      WHERE  com_id = " + computer.getId() + ") + 1, ");
		queryString.append("       com_name = '" + computer.getName() + "' ");
		queryString.append("WHERE  com_id = " + computer.getId() + "; ");

		try {
			this.statement.executeUpdate(queryString.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * updates data in the network_interface_card entity
	 */
	private void updateNetworkInterfaceCard(final NetworkInterfaceCard networkInterfaceCard) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// the standard query for update NetworkInterfaceCard
		final StringBuffer queryString = new StringBuffer();
		queryString.append("UPDATE network_interface_card ");
		queryString.append("SET    nic_version = (SELECT nic_version ");
		queryString.append("                      FROM   network_interface_card ");
		queryString.append("                      WHERE  nic_id = " + networkInterfaceCard.getId() + ") + 1, ");
		queryString.append("       nic_com_id = '" + networkInterfaceCard.getComputerId() + "', ");
		queryString.append("       nic_mac_address = '" + networkInterfaceCard.getMacAddress() + "', ");
		queryString.append("       nic_ip_address = '" + networkInterfaceCard.getIpAddress() + "', ");
		queryString.append("       nic_subnet_mask = '" + networkInterfaceCard.getSubnetMask() + "', ");
		queryString.append("       nic_dns = '" + networkInterfaceCard.getDns() + "', ");
		queryString.append("       nic_gateway = '" + networkInterfaceCard.getGateway() + "', ");
		queryString.append("       nic_domain = '" + networkInterfaceCard.getDomain() + "' ");
		queryString.append("WHERE  nic_id = " + networkInterfaceCard.getId() + "; ");

		try {
			this.statement.executeUpdate(queryString.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * updates data in the operating_system entity
	 */
	private void updateOperatingSystem(final OperatingSystem operatingSystem) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// the standard query for update OperatingSystem
		final StringBuffer queryString = new StringBuffer();
		queryString.append("UPDATE operating_system ");
		queryString.append("SET    osy_version = (SELECT osy_version ");
		queryString.append("                      FROM   operating_system ");
		queryString.append("                      WHERE  osy_id = " + operatingSystem.getId() + ") + 1, ");
		queryString.append("       osy_name = '" + operatingSystem.getName() + "', ");
		queryString.append("       osy_description = '" + operatingSystem.getDescription() + "' ");
		queryString.append("WHERE  osy_id = " + operatingSystem.getId() + "; ");

		try {
			this.statement.executeUpdate(queryString.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}

	/**
	 * updates data in the software entity
	 */
	private void updateSoftware(final Software software) {
		this.connection = SQLiteJDBCHelper.initConnection(this.connection);
		this.statement = SQLiteJDBCHelper.initStatement(this.statement, this.connection);

		// the standard query for update Software
		final StringBuffer queryString = new StringBuffer();
		queryString.append("UPDATE software ");
		queryString.append("SET    sof_version = (SELECT sof_version ");
		queryString.append("                      FROM   software ");
		queryString.append("                      WHERE  sof_id = " + software.getId() + ") + 1, ");
		queryString.append("       sof_name = '" + software.getName() + "', ");
		queryString.append("       sof_description = '" + software.getDescription() + "', ");
		queryString.append("       sof_version_number = '" + software.getVersionNumber() + "' ");
		queryString.append("WHERE  sof_id = " + software.getId() + "; ");

		try {
			this.statement.executeUpdate(queryString.toString());
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		this.statement = SQLiteJDBCHelper.closeStatement(this.statement);
		this.connection = SQLiteJDBCHelper.closeConnection(this.connection);
	}
}
