package edu.ncsu.csc216.service_wolf.model.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * This class is responsible for writing a list of ServiceGroup 
 records to a file with their respective incidents written out. 
 It has one method that is solely responsible for this task. It formats
 the output in a way where the Service group names begin with a '#', 
 then the incidents with a '*' in the front and the log messages
 with a '-' in the front
 * @author abhin
 *
 */
public class ServiceGroupWriter {

	
	/**
	 * writes a list of ServiceGroups to a file called fileName
	 * @param fileName is the file where the Service Group records are written to 
	 * @param list of ServiceGroup Records to be documented
	 * @throws IOException when records can't be saved to a file
	 */
	public static void writeServiceGroupsToFile(String fileName, ArrayList<ServiceGroup>  list) {
		PrintStream fileWriter;
		try {
			 fileWriter = new PrintStream(new File(fileName));

		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
    	for (ServiceGroup a : list) {
    		fileWriter.println("# " + a.getServiceGroupName());
    		for(int i = 0; i < a.getIncidents().size(); i++) {
    			Incident curr = a.getIncidents().get(i);
    			fileWriter.print(curr);
    			//fileWriter.print(curr.getIncidentLogMessages());
    	
    		}	
    	}
    	fileWriter.close();
	}
}

