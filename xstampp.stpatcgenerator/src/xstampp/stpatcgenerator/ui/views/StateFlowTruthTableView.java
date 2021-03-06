package xstampp.stpatcgenerator.ui.views;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import xstampp.stpatcgenerator.controller.STPATCGModelController;
import xstampp.stpatcgenerator.model.stateflow.chart.generateStatesTree.Tree;
import xstampp.stpatcgenerator.model.stateflow.chart.parse.StateTransition;

/**
 * This class defines the view of safe behavioral model truth table.
 * @author Ting Luk-He
 *
 */
public class StateFlowTruthTableView extends ViewPart {
	public final static String ID = "xstampp.stpatcgenerator.view.stateFlowTruthTable";

//	private TableViewer viewer;
	Composite parentFrame;
	private JPanel panel;
	private Frame frame;
	
	private Tree tree = STPATCGModelController.getConfWizard().getTree();
	String[] columnNames = {"ID", "Source", "Conditions", "ID", "Destination"};
	static DefaultTableModel tableModel;
	
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
//		GridLayout layout = new GridLayout(2, false);
//	    parent.setLayout(layout);
//	    createViewer(parent);
	    
		parentFrame = new Composite(parent, SWT.EMBEDDED);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		frame = SWT_AWT.new_Frame(parentFrame);
		frame.setLayout(new BorderLayout());
		JTable table = new JTable();
		tableModel = new DefaultTableModel(0,0){
			@Override
			public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
		tableModel.setColumnIdentifiers(columnNames);
		table.setModel(tableModel);
		setRowsForTable();
		
		STPATCGModelController.setStateFlowTruthTableModel(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		panel.add(scrollPane);
		panel.setVisible(true);
		frame.add(panel);
	}
	
	private void setRowsForTable() {
		if(tree != null && tableModel != null) {
			List<StateTransition> truthTable = tree.generateTruthTable(tree
					.getRoot());
			for(StateTransition t : truthTable) {
				tableModel.addRow(new Object[]{t.getSSID(), tree.getNameOfstate(t.getSource()), t.getCondition(), t.getDestination(), tree.getNameOfstate(t.getDestination())});
				
			}
		} else {
			System.out.println("Tree is null");
		}
	}
	
	

//	private void createViewer(Composite parent) {
//		// TODO Auto-generated method stub
//		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
//		        | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
//		createColumns(parent, viewer);
//		    final Table table = viewer.getTable();
//		    table.setHeaderVisible(true);
//		    table.setLinesVisible(true);
//
//		    viewer.setContentProvider(new ArrayContentProvider());
//		    // get the content for the viewer, setInput will call getElements in the
//		    // contentProvider
//		    viewer.setInput(TruthTableProvider.INSTANCE.getStateTransitions());
//		    // make the selection available to other views
//		    getSite().setSelectionProvider(viewer);
//	}

//	public TableViewer getViewer() {
//	    return viewer;
//	}
	
//	private void createColumns(Composite parent, TableViewer viewer) {
//		// TODO Auto-generated method stub
//		String[] titles = { "ID", "Source", "Conditions", "ID", "Destination"};
//		int[] bounds = { 100, 100, 300, 100, 100 };
//		
//		// first column is for the source id
//	    TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
//	    col.setLabelProvider(new ColumnLabelProvider() {
//	      @Override
//	      public String getText(Object element) {
//	    	  TruthTableStateTransition p = (TruthTableStateTransition) element;
//	        return p.getSourceId();
//	      }
//	    });
//	    
//	    // second column is for the source state
//	    col = createTableViewerColumn(titles[1], bounds[1], 1);
//	    col.setLabelProvider(new ColumnLabelProvider() {
//	      @Override
//	      public String getText(Object element) {
//	    	  TruthTableStateTransition p = (TruthTableStateTransition) element;
//	        return p.getSourceState();
//	      }
//	    });
//	    
//	    // third column is for the conditions
//	    col = createTableViewerColumn(titles[2], bounds[2], 2);
//	    col.setLabelProvider(new ColumnLabelProvider() {
//	      @Override
//	      public String getText(Object element) {
//	    	  TruthTableStateTransition p = (TruthTableStateTransition) element;
//	        return p.getCondition();
//	      }
//	    });
//	    
//	    // fourth column is for the destination id
//	    col = createTableViewerColumn(titles[3], bounds[3], 3);
//	    col.setLabelProvider(new ColumnLabelProvider() {
//	      @Override
//	      public String getText(Object element) {
//	    	  TruthTableStateTransition p = (TruthTableStateTransition) element;
//	        return p.getDestinationId();
//	      }
//	    });
//	    
//	    // last column is for the destination state
//	    col = createTableViewerColumn(titles[4], bounds[4], 4);
//	    col.setLabelProvider(new ColumnLabelProvider() {
//	      @Override
//	      public String getText(Object element) {
//	    	  TruthTableStateTransition p = (TruthTableStateTransition) element;
//	        return p.getDestinationState();
//	      }
//	    });
//	    
//	}

//	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
//	    final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
//	        SWT.NONE);
//	    final TableColumn column = viewerColumn.getColumn();
//	    column.setText(title);
//	    column.setWidth(bound);
//	    column.setResizable(true);
//	    column.setMoveable(true);
//	    return viewerColumn;
//	  }

	public static DefaultTableModel getTableModel() {
		return tableModel;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
//		viewer.getControl().setFocus();
	}
	
	
}
