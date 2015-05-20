	package pds.r3.charfouni.dao;
			
	import java.util.List;
			
	import pds.r3.charfouni.requests.SourceRequest;
	import pds.r3.charfouni.requests.StagingRequest;
	import pds.r3.charfouni.beans.ServiceBean;
			
	public class ServiceDAO {
		public static void main(String[] args) {
			ServiceDAO dao=new ServiceDAO();
			dao.getServices();
		}
				
	public List<List<String>> getServices() {
		
			StagingRequest bddrq = new StagingRequest();
			String request = "select * from dim_service ";
			List<List<String>> lst = bddrq.executeThisSelectQuery(request);
			;
			return lst;
				}
	
	
	
	       public boolean insertServices(ServiceBean Service) {
			SourceRequest sourceRequest = new SourceRequest();
			boolean retour;

			String request1 =
			"INSERT INTO dim_service (health_territory,hospital,service,speciality )"
			+ " VALUES ("+Service.getHealth_territory()+",'"+Service.getHospital()+"',"+Service.getService()+"',"+Service.getSpeciality()+");";
			retour = sourceRequest.executeThisUpdateQuery(request1);
			return retour;
			
				}
			
				
			}