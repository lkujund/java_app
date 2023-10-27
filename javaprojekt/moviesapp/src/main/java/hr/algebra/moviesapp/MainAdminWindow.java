/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hr.algebra.moviesapp;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.dal.sql.SqlMovieRepo;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.moviesapp.parsers.Parser;
import java.awt.Color;
import java.awt.Dialog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Luka
 */
public class MainAdminWindow extends javax.swing.JFrame {

    private static final String DIRECTORY = "assets";
    private static List<Movie> movies = new ArrayList<>();
    private Parser parser = new Parser();
    
    //repos
    private SqlMovieRepo movieRepo;
    private Repository actorRepo;
    private Repository directorRepo;
    private Repository genreRepo;

    
    public MainAdminWindow() {
        initComponents();
        init();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLoad = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbLoading = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnLoad.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnLoad.setText("Učitaj podatke");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnClear.setText("Obriši sve podatke");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pozz gospon admin");

        lbLoading.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lbLoading.setForeground(java.awt.Color.orange);
        lbLoading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jMenuLogout.setText("Logout");
        jMenuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuLogoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuLogout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbLoading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lbLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        new Thread(() -> 
        {
            try {
                loadMoviesToDatabase();
            } catch (Exception ex) {
                Logger.getLogger(MainAdminWindow.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }).start();
        

    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        new Thread(() -> 
        {
            try {
                clearAllData();
            } catch (Exception ex) {
                Logger.getLogger(MainAdminWindow.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }).start();
    }//GEN-LAST:event_btnClearActionPerformed

    private void jMenuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuLogoutMouseClicked
        this.dispose();
        Login.login.setVisible(true);
    }//GEN-LAST:event_jMenuLogoutMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainAdminWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainAdminWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainAdminWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAdminWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainAdminWindow().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLoad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuLogout;
    private javax.swing.JLabel lbLoading;
    // End of variables declaration//GEN-END:variables

    private void loadMoviesToDatabase() throws Exception{
        lbLoading.setForeground(Color.orange);
        lbLoading.setText("Loading data...");
        movies = parser.parse();
        try {
            for (Movie movie : movies) {
                movieRepo.createOne(movie); 
                if (movie.getActors() != null) {
                    for (Person person : movie.getActors()) {
                        if (person != null) {
                            actorRepo.createOne(person);
                            movieRepo.addActorToMovie(movie, person);
                        }
                    }
                }
                if (movie.getDirectors() != null) {
                    for (Person person : movie.getDirectors()) {
                        if (person != null) {
                            directorRepo.createOne(person);
                            movieRepo.addDirectorToMovie(movie, person);
                        }
                    }
                }
                if (movie.getGenres() != null) {
                    for (Genre genre : movie.getGenres()) {
                        if (genre != null) {
                            genreRepo.createOne(genre);
                            movieRepo.addGenreToMovie(movie, genre);
                        }
                    }                    
                }
           
            }
            
            lbLoading.setForeground(Color.green);
            lbLoading.setText("Data loaded");
            
            btnLoad.setEnabled(false);
        } catch (Exception exception) {
            lbLoading.setForeground(Color.red);
            lbLoading.setText("Error inserting data!");
        }
    }

    private void init() {
        try {
            movieRepo = (SqlMovieRepo) RepositoryFactory.getRepository(RepositoryFactory.RepositoryType.MOVIE_REPO);
            actorRepo = RepositoryFactory.getRepository(RepositoryFactory.RepositoryType.ACTOR_REPO);
            directorRepo = RepositoryFactory.getRepository(RepositoryFactory.RepositoryType.DIRECTOR_REPO);
            genreRepo = RepositoryFactory.getRepository(RepositoryFactory.RepositoryType.GENRE_REPO);

        } catch (Exception e) {
            lbLoading.setText("Repository error!");
        }
    }

    private void clearAllData() throws Exception {
        lbLoading.setForeground(Color.orange);
        lbLoading.setText("Clearing data...");
        try {
            movieRepo.clearData();
            deleteAssets(new File(DIRECTORY));
            lbLoading.setForeground(Color.green);
            lbLoading.setText("Data cleared.");
            btnLoad.setEnabled(true);
        } catch (Exception ex) {
            lbLoading.setForeground(Color.red);
            lbLoading.setText("Error clearing data!");

        }
    }

    private void deleteAssets(File assets) {
            File[] files = assets.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteAssets(file);                
                }
            }
            assets.delete();
    }
}
