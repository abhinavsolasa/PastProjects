package edu.ncsu.csc216.service_wolf.model.manager;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.io.ServiceGroupWriter;
import edu.ncsu.csc216.service_wolf.model.io.ServiceGroupsReader;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * df
 * @author abhinav
 *
 */
public class ServiceWolfManager {

	/** a list of ServiceGroups  **/
	private ArrayList<ServiceGroup> serviceGroups;
	
	/** Represents an instance of the class itself         **/
	private static ServiceWolfManager instance;
	
	
	/**  the active servicegroup that is appeared on the system **/
	private ServiceGroup currentServiceGroup;
	
	/**
	 * sets up the manager and loads in the ServiceGroups from a file
	 * @throws FileNotFoundException if fileName can't be found
	 */
	private ServiceWolfManager() {
		serviceGroups = new ArrayList<ServiceGroup>();
		currentServiceGroup = null;
//		String fileName = "test-files/grouprecords.txt";
//		loadFromFile(fileName);
	}
	
	/**
	 * the singleton algorithm?
	 * @return the instance of a ServiceWolfManager
	 */
	public static ServiceWolfManager getInstance() {
		if (instance == null) {
			instance = new ServiceWolfManager();
		}
		return instance;	
	}
	
	/**
	 * saves the ServiceGroups to a file
	 * @param fileName is file where the records are written to 
	 */
	public void saveToFile(String fileName) {
		if(currentServiceGroup == null || currentServiceGroup.getIncidents().size() == 0) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		ServiceGroupWriter.writeServiceGroupsToFile(fileName, serviceGroups);
	}
	
	/**
	 * loads in ServiceGroup records from a file
	 * @param fileName is location where records are located 
	 * @throws FileNotFoundException if file cannot be found
	 */
	public void loadFromFile(String fileName) {
		ArrayList<ServiceGroup> newGroups = new ArrayList<ServiceGroup>();
		newGroups = ServiceGroupsReader.readServiceGroupsFile(fileName);
		for(int i = 0; i < newGroups.size(); i++) {
			addServiceGroupToListByName(newGroups.get(i));
		}
			currentServiceGroup = newGroups.get(0);
			currentServiceGroup.setIncidentCounter();
	}
	
	/**
	 * accesses all the Incidents of active service group 
	 as an array
	 * @return a 2d array of all the Incidents in group
	 */
	public String[][] getIncidentsAsArray(){
		String [][] incidents = null;
		if(currentServiceGroup != null) {
			incidents = new String [currentServiceGroup.getIncidents().size()][4];
			for(int i = 0; i < currentServiceGroup.getIncidents().size(); i++) {
				incidents[i][0] = String.valueOf(currentServiceGroup.getIncidents().get(i).getId());
				incidents[i][1] = currentServiceGroup.getIncidents().get(i).getState();
				incidents[i][2] = currentServiceGroup.getIncidents().get(i).getTitle();
				incidents[i][3] = currentServiceGroup.getIncidents().get(i).getStatusDetails();
			}
		}
		return incidents;
	}
	
	/**
	 * accesses an Incident in the ServiceGroup with
	 specified id
	 * @param id of Incident to be accessed
	 * @return the Incident 
	 */
	public Incident getIncidentById(int id) {
		if(currentServiceGroup != null) {
//			for(int i = 0; i < currentServiceGroup.getIncidents().size(); i++) {
//				Incident current = currentServiceGroup.getIncidents().get(i);
//				if (current.getId() == id) {
//					return current;
//				}
//			}
			return currentServiceGroup.getIncidentById(id);
		}
		return null;
	}
	
	/**
	 * execution of a command on an Incident with specified id
	 * @param id of Incident to be modified
	 * @param c is the command 
	 */
	public void executeCommand(int id, Command c) {
		if(currentServiceGroup != null) {
			currentServiceGroup.executeCommand(id, c);

		}
	}
	
	/**
	 * deletes an incident with specified id
	 * @param id of Incident to be deleted 
	 */
	public void deleteIncidentById(int id) {
		if (currentServiceGroup != null) {
			currentServiceGroup.deleteIncidentById(id);
		}		
	
	}
	
	/**
	 * add an Incident to the Active serviceGroup
	 * @param message is the message that has to be added to the incidentLog of new Incident
	 * @param title of the incident to be added 
	 * @param caller is the caller of the incident
	 */
	public void addIncidentToServiceGroup(String title, String caller, String message) {
		Incident incident = new Incident(title, caller, message);
		System.out.println("Actual ID: " + incident.getId());
		currentServiceGroup.addIncident(incident);
		System.out.println("Current ServiceGroup Incidents size: " + currentServiceGroup.getIncidents().size());
	}
	
	/**
	 * changes to a different active ServiceGroup
	 * @param serviceGroupName is the name of group that is about to be active
	 */
	public void loadServiceGroup(String serviceGroupName) {
		if(serviceGroupName ==  null || "".equals(serviceGroupName)) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		boolean inList = false;
		if(serviceGroups.size() != 0) {
			for(int i = 0; i < serviceGroups.size(); i++) {
				if (serviceGroupName.equals(serviceGroups.get(i).getServiceGroupName())){
					currentServiceGroup = serviceGroups.get(i);
					inList = true;
					break;
				}
			}
			if(!inList) {
				throw new IllegalArgumentException("Invalid service group name.");
			}
//			if(currentServiceGroup.getIncidents().size() == 0) {
//				Incident.setCounter(1);
//			}
//			else {
//				currentServiceGroup.setIncidentCounter();
//			}
			currentServiceGroup.setIncidentCounter();
		}
	}
	
	/**
	 * name of the active ServiceGroup
	 * @return the name of the ActiveServiceGroup
	 */
	public String getServiceGroupName() {
		if(currentServiceGroup == null) {
			return null;
		}
		else {
			return currentServiceGroup.getServiceGroupName();
		}
	
	}
	
	/**
	 * an array of the ServiceGroup names in the system
	 * @return a String array of ServiceGroup names
	 */
	public String[] getServiceGroupList() {
		String [] list = new String[serviceGroups.size()];
		for(int i = 0; i < serviceGroups.size(); i++) {
			list [i] = serviceGroups.get(i).getServiceGroupName();
		}
		return list;
	}
	
	/**
	 * Resets serviceGroup to an empty array list. 
	 The currentServiceGroup is set to null.
	 */
	public void clearServiceGroups() {
		serviceGroups.clear();
		currentServiceGroup = null;
	}
	
	/**
	 *  Updates the currentServiceGroup’s name to the given value.
	 * @param updateName new name of the ServiceGroup
	 */
	public void editServiceGroup(String updateName) {
		if(currentServiceGroup == null) {
			throw new IllegalArgumentException("No service group selected.");
		}
		if("".equals(updateName) || updateName == null) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		
		checkDuplicateServiceName(updateName);
		currentServiceGroup.setServiceGroupName(updateName);
		for(int i = 0; i < serviceGroups.size(); i++){
			if (serviceGroups.get(i) == currentServiceGroup){
				serviceGroups.remove(i);
				break;
			}
		}
		addServiceGroupToListByName(currentServiceGroup);
		loadServiceGroup(updateName);
	}
	
	private void addServiceGroupToListByName(ServiceGroup serviceGroup) {
		if("".equals(serviceGroup.getServiceGroupName())) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		if(serviceGroups.size() == 0) {
			serviceGroups.add(serviceGroup);
			currentServiceGroup = serviceGroup;
			currentServiceGroup.setIncidentCounter();
		}
		else {
			boolean inList = false;
			checkDuplicateServiceName(serviceGroup.getServiceGroupName());
			for(int i = 0; i < serviceGroups.size(); i++){
				ServiceGroup current = serviceGroups.get(i);
				 if(serviceGroup.getServiceGroupName().compareTo(current.getServiceGroupName()) < 0) {
					serviceGroups.add(i, serviceGroup);
					inList = true;
					break;
				}
			}
			if(!inList){
				serviceGroups.add(serviceGroup);
			}
		}
		loadServiceGroup(serviceGroup.getServiceGroupName());
	}
	
	/**
	 * Creates a new ServiceGroup with the given name and adds it to the serviceGroup list
	 * @param serviceGroupName is the name of serviceGroup about 
	 to be added to the manager
	 */
	public void addServiceGroup(String serviceGroupName) {
		if(serviceGroupName == null || "".equals(serviceGroupName)) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		ServiceGroup group = new ServiceGroup(serviceGroupName);
		if(serviceGroups.size() == 0) {
			serviceGroups.add(group);
			currentServiceGroup = group;
			currentServiceGroup.setIncidentCounter();
		}
		else {
			checkDuplicateServiceName(serviceGroupName);
			boolean inList = false;
			for(int i = 0; i < serviceGroups.size(); i++){
				ServiceGroup current = serviceGroups.get(i);
				if (serviceGroupName.compareTo(current.getServiceGroupName()) < 0) {
					serviceGroups.add(i, group);
					inList = true;
					break;
				}
			}
			if(!inList){
				serviceGroups.add(group);
			}
		}
		loadServiceGroup(serviceGroupName);
		
	}
	
	/**
	 * checks for a duplicate name in the list of ServiceGroups
	 * @param duplicate is the name that is being checked if is a duplicate
	 */
	private void checkDuplicateServiceName(String duplicate) {
		for(int i = 0; i < serviceGroups.size(); i++) {
			if(duplicate.equals(serviceGroups.get(i).getServiceGroupName())) {
				System.out.println("This is a duplicate Name!!!");
				throw new IllegalArgumentException("Invalid service group name.");
			}
		}	
	}
	
	/**
	 * deletes current ServiceGroup
	 */
	public void deleteServiceGroup() {
		if(currentServiceGroup == null) {
			System.out.println("Was Inside here! ");
			throw new IllegalArgumentException("No service group selected.");
		}
		for(int i = 0; i < serviceGroups.size(); i++) {
			if(currentServiceGroup == serviceGroups.get(i)) {
				serviceGroups.remove(i);
				break;
			}
		}
		if(serviceGroups.size() == 0) {
			currentServiceGroup = null;
		}
		else {
			currentServiceGroup = serviceGroups.get(0);
			currentServiceGroup.setIncidentCounter();
		}
	}
	
	protected void resetManager() {
		instance = null;
		currentServiceGroup = null;
		
	}
	
	
	
	
}