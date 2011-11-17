package service;

import java.util.List;
import java.util.Map;

import bean.Computer;
import bean.NetworkInterfaceCard;
import bean.OperatingSystem;
import bean.SearchResult;
import bean.Software;
import bean.SuperBean;

/**
 * interface for the operations (select, update, insert, delete)
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

public interface IDatabaseOperations {

	/**
	 * 
	 * deletes data by given bean
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param superBean
	 */
	public void deleteData(SuperBean superBean);

	/**
	 * 
	 * inserts new data by given bean and computerId (to map it)
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param superBean
	 * @param computerId
	 */
	public void insertNewData(SuperBean superBean, Integer computerId);

	/**
	 * 
	 * searches the table COMPUTER by given searchAttributes (fieldname - key, searchValue - value)
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param searchAttributes
	 * @return List of SearchResult
	 */
	public List<Computer> searchComputerByAttributes(Map<String, String> searchAttributes);

	/**
	 * 
	 * searches the db by given searchAttributes (fieldname - key, searchValue - value)
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param searchAttributes
	 * @return List of SearchResult
	 */
	public List<SearchResult> searchDataByAttributes(Map<String, String> searchAttributes);

	/**
	 * 
	 * searches the table NETWORK_CARD_INTERFACE by given searchAttributes (fieldname - key, searchValue - value)
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param searchAttributes
	 * @return List of SearchResult
	 */
	public List<NetworkInterfaceCard> searchNetworkInterfaceCardByAttributes(Map<String, String> searchAttributes);

	/**
	 * 
	 * searches the table OPERATING_SYSTEM by given searchAttributes (fieldname - key, searchValue - value)
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param searchAttributes
	 * @return List of SearchResult
	 */
	public List<OperatingSystem> searchOperatingSystemByAttributes(Map<String, String> searchAttributes);

	/**
	 * 
	 * searches the table SOFTWARE by given searchAttributes (fieldname - key, searchValue - value)
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param searchAttributes
	 * @return List of SearchResult
	 */
	public List<Software> searchSoftwareByAttributes(Map<String, String> searchAttributes);

	/**
	 * 
	 * updates data by given bean
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param superBean
	 * @param computerId
	 */
	public void updateData(SuperBean superBean);
}
