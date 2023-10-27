/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.moviesapp;

import hr.algebra.dal.RepositoryFactory;
import hr.algebra.dal.sql.SqlDirectorRepo;
import hr.algebra.model.Person;
import hr.algebra.moviesapp.model.PersonTableModel;
import hr.algebra.utilities.MessageUtils;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Luka
 */
public class ViewDirectorsPanel extends javax.swing.JPanel {

    /**
     * Creates new form EditDirectorsPanel
     */
    private SqlDirectorRepo directorRepo;
    private Person selectedDirector;
    private List<Person> directors;
    private PersonTableModel directorTableModel = new PersonTableModel();
    public ViewDirectorsPanel() {
        initComponents();
        init();
        getRepo();
        try {
            fetchDirectors();
        } catch (Exception ex) {
            Logger.getLogger(ViewDirectorsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfSurname = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDirectors = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lbNameError = new javax.swing.JLabel();
        lbSurnameError = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel2.setText("Surname");

        jLabel3.setText("DIRECTORS:");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        tblDirectors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDirectors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDirectorsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDirectors);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(java.awt.Color.red);
        btnDelete.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnDelete.setText("X");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lbNameError.setForeground(java.awt.Color.red);
        lbNameError.setText("X");

        lbSurnameError.setForeground(java.awt.Color.red);
        lbSurnameError.setText("X");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSurnameError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd)
                            .addComponent(btnEdit)
                            .addComponent(btnDelete)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbSurnameError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (!formValid()) {
            return;
        }
        if (selectedDirector != null) {
            new Thread(() -> {
                try {
                    selectedDirector.setName(tfName.getText().trim());
                    selectedDirector.setSurname(tfSurname.getText().trim());
                    directorRepo.updateOne(selectedDirector.getId(), selectedDirector);
                    fetchDirectors();
                } catch (Exception ex) {
                    Logger.getLogger(ViewDirectorsPanel.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }).start();
        }
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void tblDirectorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDirectorsMouseClicked
        clearForm();
        new Thread(() ->
        {
            int selectedRow = tblDirectors.getSelectedRow();
            int rowIndex = tblDirectors.convertRowIndexToModel(selectedRow);
            int selectedDirectorId = (int) directorTableModel.getValueAt(rowIndex, 0);

            try {
                Optional<Person> optPerson = directorRepo.selectOne(selectedDirectorId);

                if (optPerson.isPresent()) {
                    selectedDirector = optPerson.get();
                    fillForm(selectedDirector);
                }
            } catch (Exception ex) {
                Logger.getLogger(ViewDirectorsPanel.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Unable to show article!");
            }
        }).start();
    }//GEN-LAST:event_tblDirectorsMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        tblDirectors.clearSelection();
        selectedDirector = null;
        tfName.setText("");
        tfSurname.setText("");
    }//GEN-LAST:event_formMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!formValid()) {
            return;
        }
        

            Person person = new Person(
                    tfName.getText().trim(),
                    tfSurname.getText().trim()
            );
            
            new Thread(()
                    -> {
                try {
                    directorRepo.createOne(person);
                    fetchDirectors();
                } catch (Exception ex) {
                    Logger.getLogger(ViewDirectorsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
            clearForm();
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedDirector != null) {
            new Thread(()
                    -> {
                try {
                    directorRepo.deleteOne(selectedDirector.getId());
                    fetchDirectors();
                } catch (Exception ex) {
                    Logger.getLogger(ViewDirectorsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();

        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNameError;
    private javax.swing.JLabel lbSurnameError;
    private javax.swing.JTable tblDirectors;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfSurname;
    // End of variables declaration//GEN-END:variables

    private void init() {
        lbNameError.setVisible(false);
        lbSurnameError.setVisible(false);
    }

    private void getRepo() {
        directorRepo = (SqlDirectorRepo) RepositoryFactory.getRepository(RepositoryFactory.RepositoryType.DIRECTOR_REPO);
    }

    private void fetchDirectors() {
        try {
            tblDirectors.removeAll();
            directors = directorRepo.selectMany().stream().distinct().collect(Collectors.toList());
            directorTableModel.setPersons(directors);
            initTable();
        } catch (Exception ex) {
            Logger.getLogger(ViewDirectorsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTable() {
        tblDirectors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDirectors.setAutoCreateRowSorter(true);
        tblDirectors.setRowHeight(25);
        tblDirectors.setModel(directorTableModel);
        tblDirectors.setAutoCreateRowSorter(true);
    }

    private void clearForm() {
        lbNameError.setVisible(false);
        lbSurnameError.setVisible(false);
        tfName.setText("");
        tfSurname.setText("");
    }

    private void fillForm(Person selectedDirector) {
        tfName.setText(this.selectedDirector.getName());
        tfSurname.setText(selectedDirector.getSurname());
    }

    private boolean formValid() {
        lbNameError.setVisible(false);
        lbSurnameError.setVisible(false);
        if (!tfName.getText().isBlank() && !tfSurname.getText().isBlank()) {
            return true;
        }
        if (tfName.getText().isBlank()) {
            lbNameError.setVisible(true);
        }
        if (tfSurname.getText().isBlank()) {
            lbSurnameError.setVisible(true);
        }
        return false;
    }
}