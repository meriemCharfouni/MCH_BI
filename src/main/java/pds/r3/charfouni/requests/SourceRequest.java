package pds.r3.charfouni.requests;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pds.r3.charfouni.BDDConnections.*;


public class SourceRequest {

	public static boolean executeThisUpdateQuery(String request) {
		
		Source src = new Source();
		
		try {
			
			src.executeThisUpdateQuery(request);
			
		} catch (Exceptions e) {
;
			return false;
		}
		return true;
	}
	
	public List<List<String>> executeThisSelectQuery(String request) {
		Source src = new Source();
		ResultSet resultset = null;
		List<List<String>> result = new ArrayList<List<String>>();
		try {

			resultset = src.executeThisSelectQuery(request);
			ResultSetMetaData metadata = resultset.getMetaData();
			int col = metadata.getColumnCount();

			while (resultset.next()) {
				List<String> row = new ArrayList<String>(col); 
				int i = 1;
				while (i <= col) { 
					row.add(resultset.getString(i++));
				}
				result.add(row);
				}
			
			
			
			
				 
		} catch (Exceptions e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
