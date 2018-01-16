package be.howest.ti.hsa.gui;

import javax.swing.DefaultListModel;
import org.supa.expensive.service.item.provider.ItemProvider;

/**
 *
 * @author madewael
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Overview
     */
    private ItemProvider ip;
    public Main(int code) {
        
        ip = new ItemProvider(code);
        
        initComponents();
        updateList();
    }
    
    private DefaultListModel<String> items = new DefaultListModel<>();
    
    private DefaultListModel getItems(){
        return items;
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtFilter = new javax.swing.JTextField();
        btnFilter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnFilter.setText("Filter");
        btnFilter.addActionListener(evt -> btnFilterActionPerformed(evt));

        jList1.setModel(getItems());
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFilter))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {
        updateList();
    }

    private javax.swing.JButton btnFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtFilter;

    private void updateList() {
        String filter = txtFilter.getText();
        items.removeAllElements();
        
        for(String s : (filter.length()<=0)?ip.getAll():ip.get(filter)){
            items.addElement(s);
        }
        
        
        
    }
    
    
}
