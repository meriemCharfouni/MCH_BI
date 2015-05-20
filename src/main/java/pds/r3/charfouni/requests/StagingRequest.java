package pds.r3.charfouni.requests;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



import pds.r3.charfouni.BDDConnections.*;

public class StagingRequest {

	
	
	public static boolean executeThisUpdateQuery(String request) {
		Staging mock = new Staging();
		try {

			mock.executeThisUpdateQuery(request);
		} catch (Exceptions e) {

			return false;
		}
		return true;
	}
	
	public List<List<String>> executeThisSelectQuery(String request) {
		
		Staging stg = new Staging();
		ResultSet resultset = null;
		List<List<String>> result = new ArrayList<List<String>>();
		try {

			resultset = stg.getConnection().createStatement().executeQuery(request);
			
			while (resultset.next()) {
				List<String> row = new LinkedList<String>(); 
				ResultSetMetaData metadata = resultset.getMetaData();
				int col = metadata.getColumnCount();
				int i = 1;
				while (i <= col) { 
					row.add(resultset.getString(i++));
				}
				result.add(row); 
				
			}
			
		} catch (Exception e) {

		}
		return result;
	}
}
