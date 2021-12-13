package edu.ncsu.csc216.service_wolf.model.service_group;

import java.util.ArrayList;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;


/**
 * This class represents a Service Group. Each ServiceGroup
 has its own list of incidents which it can remove or add. 
 Each group also has its own name which is pretty much the only 
 detail it has. A ServiceGroup can change its name anytime as long as
 it is not a duplicate name of another ServiceGroup. Most importantly,
 a ServiceGroup executes a bunch of commands on its incidents to modify
 the state of each incident
 * @author abhin
 *
 */
public class ServiceGroup {

	/** name of the service group **/
	private String serviceGroupName;
	
	/**
	 * list of the incidents part of the Group
	 */
	private ArrayList<Incident> incidents;
	
	/**
	 * constructs a ServiceGroup with the default name
	 * @param name of the ServiceGroup
	 */
	public ServiceGroup(String name) {
		setServiceGroupName(name);
		incidents = new ArrayList<Incident>();
	}
	
	/**
	 * sets the incident counter to a default value;
	 */
	public void setIncidentCounter() {
		if(incidents.size() == 0) {
			Incident.setCounter(1);
		}
		else {
			int max = incidents.get(incidents.size() - 1).getId();
			Incident.setCounter(max + 1);
		}
	}
	
	/**
	 * sets the name of the ServiceGroup to specified parameter
	 * @param name of the ServiceGroup 
	 */
	public void setServiceGroupName(String name) {
		if(name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		serviceGroupName = name;
	}
	/**
	 * accesses the name of the ServiceGroup
	 * @return serviceGroupName
	 */
	 public String getServiceGroupName() {
		return serviceGroupName;
	}

	 /**
	  * adds the corresponding incident parameter to the list
	  * @param incident is added to the ServiceGroup incident list
	  */
	public void addIncident(Incident incident) {
		if (incidents.size() == 0) {
			incidents.add(incident);
		}
		else {
			boolean inList = false;
			for(int i = 0; i < incidents.size(); i++) {
				if(incident.getId() == incidents.get(i).getId()) {
					throw new IllegalArgumentException("Incident cannot be created.");
				}	
				else {
					if(incident.getId() < incidents.get(i).getId()){
						incidents.add(i, incident);
						inList = true;
						break;
					}
				}
			}
			if(!inList) {
				incidents.add(incident);
			}
		}
	}
	
	/**
	 * list of Incidents that are part of the ServiceGroup
	 * @return the private ArrayList incidents
	 */
	public ArrayList<Incident> getIncidents(){
		return incidents;
	}
	
	/**
	 * accesses an incident in the ServiceGroup
	 by its id
	 * @param id of incident to be accessed
	 * @return an incident
	 */
	public Incident getIncidentById(int id) {
		for(int i = 0; i < incidents.size(); i++){
			if (incidents.get(i).getId() == id){
				return incidents.get(i);
			}
		}
		return null;
	}
	
	/**
	 * executes a command to an incident in the service group with specified 
	 id
	 * @param id of the incident to execute command
	 * @param c is command that is executed
	 */
	public void executeCommand(int id, Command c) {
		if(getIncidentById(id) != null) {
			getIncidentById(id).update(c);
		}
	}
	
	/**
	 * deletes an incident with specified id
	 * @param id is incident that is deleted
	 */
	public void deleteIncidentById(int id) {
		if(incidents.size() != 0) {
			if(incidents.size() == 1 && incidents.get(0).getId() == id) {
				incidents.remove(0);
				Incident.setCounter(0);
			}
			else {
				for(int i = 0; i < incidents.size(); i++){
					if (incidents.get(i).getId() == id){
						 incidents.remove(i);
						 break;
					}
				}
			}	
		}
	}
}
