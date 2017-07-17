package xstampp.stpatcgenerator.model.astpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import xstampp.astpa.model.DataModelController;
import xstampp.astpa.model.causalfactor.interfaces.ICausalComponent;
import xstampp.astpa.model.controlaction.interfaces.IHAZXControlAction;
import xstampp.astpa.model.controlstructure.components.Component;
import xstampp.astpa.model.controlstructure.interfaces.IRectangleComponent;

public class STPADataModelController extends DataModelController {
	DataModelController dataModel =new DataModelController();

    public DataModelController getdataModel() {
        return dataModel;
    }

    public void setDataModelController(DataModelController datamodel) {
        this.dataModel = datamodel;
       
    }

    public STPADataModelController() {
    }

    /**
     * fetches the current available value components from the data model
     *
     */
    
    
    
    
    
    public List<ProcessModelValue> fetchProcessComponentsAsList() {

        List<ProcessModelValue> valuesList = new ArrayList<>();
//        List<ICausalComponent> templist =  dataModel.getControlStructureEditorController().getCausalComponents();
        List<ICausalComponent> templist =  dataModel.getCausalComponents();
        for (int i = 0, n = templist.size(); i < n; i++) {

            Component parentComponent = (Component) templist.get(i);
            if (parentComponent.getComponentType().name().equals("CONTROLLER")) {

                // get the process models
                for (IRectangleComponent tempPM : parentComponent.getChildren()) {

                    // get the variables
                    for (IRectangleComponent tempPMV : tempPM.getChildren()) {
                        // get the values and add the new object to the processmodel list
                        for (IRectangleComponent tempPMVV : tempPMV.getChildren()) {

                            ProcessModelValue pmValueObject = new ProcessModelValue();

                            pmValueObject.setController(parentComponent.getText());
                            pmValueObject.setPM(tempPM.getText());
                            pmValueObject.setPMV(tempPMV.getText());
                            pmValueObject.setValueText(tempPMVV.getText());
                            pmValueObject.setId(tempPMVV.getId());
                            System.out.println("DEBUG: set valueId: " + tempPMVV.getId() + " for " + tempPMVV.getText());
                            pmValueObject.setVariableID(tempPMV.getId());
                            pmValueObject.setComments(tempPMVV.getComment());
                            valuesList.add(pmValueObject);

                        }
                    }

                }
            }
        }
        return valuesList;
    }

    public List<String> fetchControllersComponents() {
        List<String> controllers = new ArrayList<String>();
//        List<ICausalComponent> templist = dataModel.getControlStructureEditorController().getCausalComponents();
        List<ICausalComponent> templist = dataModel.getCausalComponents();
        for (int i = 0, n = templist.size(); i < n; i++) {

            Component parentComponent = (Component) templist.get(i);
            if (parentComponent.getComponentType().name().equals("CONTROLLER")) {
                controllers.add(parentComponent.getText());

            }

        }

        return controllers;
    }

    public List<String> fetchControlActionComponents() {
        List<String> controlactions = new ArrayList<String>();

//        List<ControlAction> tempControlactions = new ArrayList<ControlAction>();
//
//        tempControlactions = dataModel.getControlStructureEditorController().getAllControlActionsU();
//
//        for (ControlAction ac : tempControlactions) {
//            if (ac != null) {
//                controlactions.add(ac.getTitle());
//            }
//        }
        List<IHAZXControlAction> tempControlactions = new ArrayList<IHAZXControlAction>();

        tempControlactions = dataModel.getAllControlActionsU();

        for (IHAZXControlAction ac : tempControlactions) {
            if (ac != null) {
                controlactions.add(ac.getTitle());
            }
        }
        tempControlactions = dataModel.getAllControlActionsU();
        
        return controlactions;
    }
    
    public Map<String, UUID> fetchControlActionComponentsWithID() {
    	Map<String, UUID> result = new HashMap<String, UUID>();
    	List<IHAZXControlAction> tempControlactions = new ArrayList<IHAZXControlAction>();

        tempControlactions = dataModel.getAllControlActionsU();
        for (IHAZXControlAction ac : tempControlactions) {
            if (ac != null) {
            	result.put(ac.getTitle(), ac.getId());
            }
        }
        tempControlactions = dataModel.getAllControlActionsU();
        
        return result;
    }

    /**
     * fetches the current available value components from the data model
     *
     */
    public List<ProcessModelValue> fetchProcessComponent() {

        List<ProcessModelValue> valuesList = new ArrayList<>();
//        List<ICausalComponent> templist = dataModel.getControlStructureEditorController().getCausalComponents();
        List<ICausalComponent> templist = dataModel.getCausalComponents();
        for (int i = 0, n = templist.size(); i < n; i++) {

            Component parentComponent = (Component) templist.get(i);
            if (parentComponent.getComponentType().name().equals("CONTROLLER")) {

                // get the process models
                for (IRectangleComponent tempPM : parentComponent.getChildren()) {

                    // get the variables
                    for (IRectangleComponent tempPMV : tempPM.getChildren()) {
                        // get the values and add the new object to the processmodel list
                        for (IRectangleComponent tempPMVV : tempPMV.getChildren()) {

                            ProcessModelValue pmValueObject = new ProcessModelValue();

                            pmValueObject.setController(parentComponent.getText());
                            pmValueObject.setPM(tempPM.getText());
                            pmValueObject.setPMV(tempPMV.getText());
                            pmValueObject.setValueText(tempPMVV.getText());
                            pmValueObject.setId(tempPMVV.getId());
                            pmValueObject.setVariableID(tempPMV.getId());
               
                            valuesList.add(pmValueObject);

                        }
                    }

                }
            }
        }
        return valuesList;
    }

    public List<Map<String, List<IRectangleComponent>>> fetchProcessComponentsAsMap() {

        Map<String, List<IRectangleComponent>> variables = new HashMap<String, List<IRectangleComponent>>();
        List<Map<String, List<IRectangleComponent>>> PMVariablesvalues = new ArrayList<Map<String, List<IRectangleComponent>>>();
        String PMV = null;
//        List<ICausalComponent> templist = dataModel.getControlStructureEditorController().getCausalComponents();
        List<ICausalComponent> templist = dataModel.getCausalComponents();
        for (int i = 0, n = templist.size(); i < n; i++) {

            Component parentComponent = (Component) templist.get(i);
            if (parentComponent.getComponentType().name().equals("CONTROLLER")) {

                for (IRectangleComponent tempPM : parentComponent.getChildren()) {
                    for (IRectangleComponent tempPMV : tempPM.getChildren()) {
                        PMV = tempPMV.getText();
                        System.out.println(PMV + tempPMV.getChildren().size());
                        variables.put(PMV, tempPMV.getChildren());

                    }
                    PMVariablesvalues.add(variables);

                    System.out.println(variables.size());

                }
            }
        }
        return PMVariablesvalues;
    }

    public List<String> fetchProcessModelvariables() {
        List<String> processmodelvaraibles = new ArrayList<String>();

//        List<ICausalComponent> templist = dataModel.getControlStructureEditorController().getCausalComponents();
        List<ICausalComponent> templist = dataModel.getCausalComponents();
        for (int i = 0, n = templist.size(); i < n; i++) {

            Component parentComponent = (Component) templist.get(i);
            if (parentComponent.getComponentType().name().equals("CONTROLLER")) {

                // get the process models
                for (IRectangleComponent tempPM : parentComponent.getChildren()) {

                    // get the variables
                    for (IRectangleComponent tempPMV : tempPM.getChildren()) {
                        // get the values and add the new object to the processmodel list

                        processmodelvaraibles.add(tempPMV.getText());
                    }
                }
            }
        }
        return processmodelvaraibles;
    }

    public Map<String, UUID> fetchProcessModelvariablesWithId(){
    	Map<String, UUID> result = new HashMap<String, UUID>();
    	List<ICausalComponent> templist = dataModel.getCausalComponents();
        for (int i = 0, n = templist.size(); i < n; i++) {

            Component parentComponent = (Component) templist.get(i);
            if (parentComponent.getComponentType().name().equals("CONTROLLER")) {

                // get the process models
                for (IRectangleComponent tempPM : parentComponent.getChildren()) {

                    // get the variables
                    for (IRectangleComponent tempPMV : tempPM.getChildren()) {
                        // get the values and add the new object to the processmodel list

                    	result.put(tempPMV.getText(), tempPMV.getId());
                    }
                }
            }
        }
        return result;
    }
}