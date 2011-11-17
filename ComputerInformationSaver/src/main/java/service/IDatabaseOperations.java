package service;

import java.util.List;
import java.util.Map;

import bean.SearchResult;
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
	 * @return Boolean returns true when the delete was successful
	 */
	public Boolean deleteData(SuperBean superBean);

	/**
	 * 
	 * inserts new data by given bean
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param superBean
	 * @return Boolean returns true when the delete was successful
	 */
	public Boolean insertNewData(SuperBean superBean);

	/**
	 * 
	 * searches the db by given searchAttributes (fieldname - key, tablename - value)
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param searchAttributes
	 * @return Boolean returns true when the delete was successful
	 */
	public List<SearchResult> searchDataByAttributes(Map<String, String> searchAttributes);

	/**
	 * 
	 * updates data by given bean
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param superBean
	 * @return Boolean returns true when the delete was successful
	 */
	public Boolean updateData(SuperBean superBean);
}
