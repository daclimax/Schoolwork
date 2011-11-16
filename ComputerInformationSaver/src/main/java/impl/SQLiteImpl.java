package impl;

import helper.SQLiteConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import service.IDatabaseOperations;
import bean.Computer;
import bean.NetworkInterfaceCard;
import bean.OperatingSystem;
import bean.Software;
import bean.SuperBean;

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

	// the connection to the database
	private Connection connection;

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
	public ResultSet searchDataByAttributes(final Map<String, String> searchAttributes) {
		SQLiteConnectionHelper.initConnection(this.connection);

		return null;
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
