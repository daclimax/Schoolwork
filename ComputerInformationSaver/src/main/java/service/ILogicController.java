package service;

import java.util.List;
import java.util.Map;

import bean.SearchResult;
import bean.SuperBean;

/**
 * interface for the logic controller (search, update, validate, etc.)
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

public interface ILogicController {

	/**
	 * 
	 * deletes component with given component, especially the id of this component.
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param component
	 * @return messages
	 */
	public List<String> deleteComponent(SuperBean component);

	/**
	 * 
	 * inserts new component in the database by given component and given computerId. If the new component is a
	 * computer, the computerId is null and not used.
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param component
	 * @param computerId
	 * @return messages
	 */
	public List<String> insertNewComponent(SuperBean component, Integer computerId);

	/**
	 * 
	 * searches components for given searchAttributes and searchType
	 * 
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param searchAttributes
	 * @param searchType
	 * @return List<SuperBean>
	 */
	public List<SuperBean> searchComponentsByAttributes(Map<String, String> searchAttributes, String searchType);

	/**
	 * 
	 * searches data for given searchAttributes
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param searchAttributes
	 * @return List<SearchResult>
	 */
	public List<SearchResult> searchDataByAttributes(Map<String, String> searchAttributes);

	/**
	 * 
	 * updates component with given component.
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param component
	 * @return messages
	 */
	public List<String> updateComponent(SuperBean component);
}
