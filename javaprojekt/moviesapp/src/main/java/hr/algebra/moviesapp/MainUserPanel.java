/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.moviesapp;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.dal.sql.SqlMovieRepo;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.model.Genre;
import hr.algebra.model.MovieLibrary;
import hr.algebra.moviesapp.model.GenreTransferable;
import hr.algebra.moviesapp.model.PersonTransferable;
import hr.algebra.moviesapp.model.MovieTableModel;
import hr.algebra.utilities.FileUtils;
import hr.algebra.utilities.IconUtils;
import hr.algebra.utilities.JAXBUtils;
import hr.algebra.utilities.MessageUtils;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;
import javax.swing.text.JTextComponent;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luka
 */
public class MainUserPanel extends javax.swing.JPanel {
    
    private static final String PATH = "src/main/resources/movies.xml";
    private List<Movie> movies;
    private MovieTableModel movieTableModel = new MovieTableModel();
    public static DefaultListModel<Genre> allGenreListModel = new DefaultListModel<>();
    public static DefaultListModel<Genre> genreListModel = new DefaultListModel<>();
    public static DefaultListModel<Person> allActorListModel = new DefaultListModel<>();
    public static DefaultListModel<Person> actorListModel = new DefaultListModel<>();
    public static DefaultListModel<Person> allDirectorListModel = new DefaultListModel<>();
    public static DefaultListModel<Person> directorListModel = new DefaultListModel<>();
    private Movie selectedMovie;
    
    private SqlMovieRepo movieRepo;
    private Repository actorRepo;
    private Repository directorRepo;
    private Repository genreRepo;
    private List<JTextComponent> validationFields;
    private List<JLabel> errorLabels;
    /**
     * Creates new form MainUserWindow
     */
    public MainUserPanel() {
        initComponents();
        init();
        getRepos();
        try {
            fetchMovies();
        } catch (Exception ex) {
            Logger.getLogger(MainUserPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pbImage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfOriginalTitle = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfDuration = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfYear = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfDescription = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMovies = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        lsAllActors = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lsActors = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lsAllDirectors = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        lsDirectors = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        lsAllGenres = new javax.swing.JList<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lsGenres = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        lbTitleError = new javax.swing.JLabel();
        lbDescriptionError = new javax.swing.JLabel();
        lbOriginalTitleError = new javax.swing.JLabel();
        lbDurationError = new javax.swing.JLabel();
        lbYearError = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        tfImagePath = new javax.swing.JTextField();
        btnChoose = new javax.swing.JButton();
        btnExportAsXML = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        pbImage.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("ALL ACTORS");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("DESCRIPTION:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("ORIGINAL TITLE:");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("DURATION:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("YEAR:");

        tfDescription.setColumns(20);
        tfDescription.setLineWrap(true);
        tfDescription.setRows(5);
        tfDescription.setWrapStyleWord(true);
        tfDescription.setMaximumSize(new java.awt.Dimension(160, 75));
        tfDescription.setMinimumSize(new java.awt.Dimension(160, 75));
        tfDescription.setName(""); // NOI18N
        jScrollPane1.setViewportView(tfDescription);

        tblMovies.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMoviesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMovies);

        jScrollPane3.setViewportView(lsAllActors);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setText("TITLE:");

        jScrollPane4.setViewportView(lsActors);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setText("ACTORS");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setText("DIRECTORS");

        jScrollPane5.setViewportView(lsAllDirectors);

        jScrollPane6.setViewportView(lsDirectors);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setText("ALL DIRECTORS");

        jScrollPane7.setViewportView(lsAllGenres);

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setText("GENRES");

        jScrollPane8.setViewportView(lsGenres);

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setText("ALL GENRES");

        lbTitleError.setForeground(java.awt.Color.red);
        lbTitleError.setText("X");

        lbDescriptionError.setForeground(java.awt.Color.red);
        lbDescriptionError.setText("X");

        lbOriginalTitleError.setForeground(java.awt.Color.red);
        lbOriginalTitleError.setText("X");

        lbDurationError.setForeground(java.awt.Color.red);
        lbDurationError.setText("X");

        lbYearError.setForeground(java.awt.Color.red);
        lbYearError.setText("X");

        btnAdd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setBackground(java.awt.Color.red);
        btnDelete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        tfImagePath.setEditable(false);

        btnChoose.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnChoose.setText("CHOOSE IMAGE");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        btnExportAsXML.setBackground(new java.awt.Color(0, 204, 255));
        btnExportAsXML.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnExportAsXML.setForeground(new java.awt.Color(0, 0, 0));
        btnExportAsXML.setText("XML");
        btnExportAsXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportAsXMLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnChoose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(tfTitle)
                            .addComponent(tfOriginalTitle)
                            .addComponent(tfDuration)
                            .addComponent(tfYear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitleError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDescriptionError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbOriginalTitleError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDurationError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbYearError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnExportAsXML, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTitleError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane5)
                                    .addComponent(jScrollPane6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30))
                                    .addComponent(jScrollPane1)
                                    .addComponent(jScrollPane3)
                                    .addComponent(jScrollPane4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfOriginalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbOriginalTitleError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(tfDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(lbDurationError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(lbYearError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(66, 66, 66)
                                                .addComponent(btnExportAsXML, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5))))
                            .addComponent(lbDescriptionError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        File file = FileUtils.uploadFile("Images", "jpg", "jpeg", "png");
        if (file == null) {
            return;
        }
        tfImagePath.setText(file.getAbsolutePath());
        setIcon(pbImage, file);
    }//GEN-LAST:event_btnChooseActionPerformed

    private void tblMoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMoviesMouseClicked
        clearForm();
        new Thread(() ->
        {
            int selectedRow = tblMovies.getSelectedRow();
            int rowIndex = tblMovies.convertRowIndexToModel(selectedRow);
            int selectedMovieId = (int) movieTableModel.getValueAt(rowIndex, 0);

            try {
                loadAll_A_D_G();
                Optional<Movie> optMovie = movieRepo.selectOne(selectedMovieId);

                if (optMovie.isPresent()) {
                    selectedMovie = optMovie.get();
                    Set<Person> optActors = movieRepo.getActorsForMovie(selectedMovie);
                    Set<Person> optDirectors = movieRepo.getDirectorsForMovie(selectedMovie);
                    Set<Genre> optGenres = movieRepo.getGenresForMovie(selectedMovie);
                    
                    actorListModel.clear();
                    directorListModel.clear();
                    genreListModel.clear();

                    optActors.forEach(actorListModel::addElement);
                    lsActors.setModel(actorListModel);

                    optDirectors.forEach(directorListModel::addElement);
                    lsDirectors.setModel(directorListModel);

                    optGenres.forEach(genreListModel::addElement);
                    lsGenres.setModel(genreListModel);
                    
                    selectedMovie.setActors(optActors);
                    selectedMovie.setDirectors(optDirectors);
                    selectedMovie.setGenres(optGenres);
                    fillForm(selectedMovie);
                }
            } catch (Exception ex) {
                Logger.getLogger(MainUserPanel.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Unable to show article!");
            }
        }).start();

    }//GEN-LAST:event_tblMoviesMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!formValid()) {
            return;
        }
        
        try {
            Movie movie = new Movie(
                    tfTitle.getText().trim(),
                    tfDescription.getText().trim(),
                    tfOriginalTitle.getText().trim(),
                    Integer.parseInt(tfDuration.getText().trim()),
                    Integer.parseInt(tfYear.getText().trim()),
                    tfImagePath.getText()
            );
            Set<Person> actors = new HashSet<>();
            for (int i = 0; i < actorListModel.size(); i++) {
                actors.add(actorListModel.get(i));
            }
            movie.setActors(actors);
            Set<Person> directors = new HashSet<>();
            for (int i = 0; i < directorListModel.size(); i++) {
                directors.add(directorListModel.get(i));
            }
            movie.setDirectors(directors);
            Set<Genre> genres = new HashSet<>();
            for (int i = 0; i < genreListModel.size(); i++) {
                genres.add(genreListModel.get(i));
            }
            movie.setGenres(genres);
            
            new Thread(() -> 
            {
                try {
                    movieRepo.createOne(movie);
                    for (Person person : movie.getActors()) {
                        if (person != null) {
                            movieRepo.addActorToMovie(movie, person);
                        }
                    }
                    for (Person person : movie.getDirectors()) {
                        if (person != null) {
                            movieRepo.addDirectorToMovie(movie, person);
                        }
                    }
                    for (Genre genre : movie.getGenres()) {
                        if (genre != null) {
                            movieRepo.addGenreToMovie(movie, genre);
                        }
                    }
                    fetchMovies();
                } catch (Exception ex) {
                    Logger.getLogger(MainUserPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();

            
            clearForm();
        } catch (Exception exception) {
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        tblMovies.clearSelection();
        clearForm();
        actorListModel.clear();
        directorListModel.clear();
        genreListModel.clear();
    }//GEN-LAST:event_formMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (!formValid()) {
            return;
        }
        
        if (selectedMovie != null) {
            new Thread(() -> {
                 try {
                    selectedMovie.setTitle(tfTitle.getText().trim());
                    selectedMovie.setDescription(tfDescription.getText().trim());
                    selectedMovie.setOriginalTitle(tfOriginalTitle.getText().trim());
                    selectedMovie.setDurationMinutes(Integer.parseInt(tfDuration.getText().trim()));
                    selectedMovie.setYear(Integer.parseInt(tfYear.getText().trim()));
                    selectedMovie.setImageUrl(tfImagePath.getText().trim());
                    Set<Person> actors = new HashSet<>();
                    for (int i = 0; i < actorListModel.size(); i++) {
                        actors.add(actorListModel.get(i));
                    }
                    selectedMovie.setActors(actors);
                    Set<Person> directors = new HashSet<>();
                    for (int i = 0; i < directorListModel.size(); i++) {
                        directors.add(directorListModel.get(i));
                    }
                    selectedMovie.setDirectors(directors);
                    Set<Genre> genres = new HashSet<>();
                    for (int i = 0; i < genreListModel.size(); i++) {
                        genres.add(genreListModel.get(i));
                    }
                    selectedMovie.setGenres(genres);
                    
                    movieRepo.updateOne(selectedMovie.getId(), selectedMovie);
                                        for (Person person : selectedMovie.getActors()) {
                        if (person != null) {
                            movieRepo.addActorToMovie(selectedMovie, person);
                        }
                    }
                    for (Person person : selectedMovie.getDirectors()) {
                        if (person != null) {
                            movieRepo.addDirectorToMovie(selectedMovie, person);
                        }
                    }
                    for (Genre genre : selectedMovie.getGenres()) {
                        if (genre != null) {
                            movieRepo.addGenreToMovie(selectedMovie, genre);
                        }
                    }
                    fetchMovies();
                } catch (Exception ex) {
                    Logger.getLogger(ViewActorsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }               
            }).start();            
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedMovie != null) {
            new Thread(()
                    -> {
                try {
                    movieRepo.deleteOne(selectedMovie.getId());
                    if (selectedMovie.getImageUrl()!= null) {
                        Files.deleteIfExists(Paths.get(selectedMovie.getImageUrl()));
                    }
                    fetchMovies();
                } catch (Exception ex) {
                    Logger.getLogger(ViewDirectorsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnExportAsXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportAsXMLActionPerformed
        
        new Thread(() -> {
            try {
            List<Movie> xmlMovies = movieRepo.selectMany().stream().distinct().collect(Collectors.toList());
            for (Movie movie : xmlMovies) {
                movie.setActors(movieRepo.getActorsForMovie(movie));
                movie.setDirectors(movieRepo.getDirectorsForMovie(movie));
                movie.setGenres(movieRepo.getGenresForMovie(movie));
            }

            JAXBUtils.save(new MovieLibrary(xmlMovies), PATH);
            MessageUtils.showInformationMessage("Info", "Movies saved");
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("Error", "Unable to save movies");
            Logger.getLogger(MainUserPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        }).start();
        
        
    }//GEN-LAST:event_btnExportAsXMLActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExportAsXML;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lbDescriptionError;
    private javax.swing.JLabel lbDurationError;
    private javax.swing.JLabel lbOriginalTitleError;
    private javax.swing.JLabel lbTitleError;
    private javax.swing.JLabel lbYearError;
    public static javax.swing.JList<Person> lsActors;
    public static javax.swing.JList<Person> lsAllActors;
    public static javax.swing.JList<Person> lsAllDirectors;
    public static javax.swing.JList<Genre> lsAllGenres;
    public static javax.swing.JList<Person> lsDirectors;
    public static javax.swing.JList<Genre> lsGenres;
    private javax.swing.JLabel pbImage;
    private javax.swing.JTable tblMovies;
    private javax.swing.JTextArea tfDescription;
    private javax.swing.JTextField tfDuration;
    private javax.swing.JTextField tfImagePath;
    private javax.swing.JTextField tfOriginalTitle;
    private javax.swing.JTextField tfTitle;
    private javax.swing.JTextField tfYear;
    // End of variables declaration//GEN-END:variables

    private void initValidation() {
        validationFields = Arrays.asList(tfTitle, tfDescription, tfOriginalTitle, tfDuration, tfYear);
        errorLabels = Arrays.asList(lbTitleError, lbDescriptionError, lbOriginalTitleError, lbDurationError, lbYearError);
    
        lbTitleError.setVisible(false);
        lbDescriptionError.setVisible(false);
        lbOriginalTitleError.setVisible(false);
        lbDurationError.setVisible(false);
        lbYearError.setVisible(false);
        
    }

    private void init() {
        initValidation();
        initDragNDrop();
    }
    
    private void initDragNDrop() {
        lsAllActors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsAllActors.setDragEnabled(true);
        lsAllActors.setTransferHandler(new ActorExportTransferHandler());
        
        lsAllDirectors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsAllDirectors.setDragEnabled(true);
        lsAllDirectors.setTransferHandler(new DirectorExportTransferHandler());
        
        lsAllGenres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsAllGenres.setDragEnabled(true);
        lsAllGenres.setTransferHandler(new GenreExportTransferHandler());
        
        lsActors.setDropMode(DropMode.ON);
        lsActors.setTransferHandler(new ActorImportTransferHandler());
        
        lsDirectors.setDropMode(DropMode.ON);
        lsDirectors.setTransferHandler(new DirectorImportTransferHandler());
        
        lsGenres.setDropMode(DropMode.ON);
        lsGenres.setTransferHandler(new GenreImportTransferHandler());
        
    }    
    
    

    private void fetchMovies() {
        try {
            tblMovies.removeAll();
            movies = movieRepo.selectMany().stream().distinct().collect(Collectors.toList());
            for (Movie movie : movies) {
                movie.setActors(movieRepo.getActorsForMovie(movie));
                movie.setDirectors(movieRepo.getDirectorsForMovie(movie));
                movie.setGenres(movieRepo.getGenresForMovie(movie));
            }
            movieTableModel.setMovies(movies);
            initTable();
        } catch (Exception exception) {
            
        }
    }

    private void getRepos() {
        movieRepo = (SqlMovieRepo) RepositoryFactory.getRepository(RepositoryFactory.RepositoryType.MOVIE_REPO);
        actorRepo = RepositoryFactory.getRepository(RepositoryFactory.RepositoryType.ACTOR_REPO);
        directorRepo = RepositoryFactory.getRepository(RepositoryFactory.RepositoryType.DIRECTOR_REPO);
        genreRepo = RepositoryFactory.getRepository(RepositoryFactory.RepositoryType.GENRE_REPO);


    }

    private void initTable() {
        tblMovies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblMovies.setAutoCreateRowSorter(true);
        tblMovies.setRowHeight(25);
        tblMovies.setModel(movieTableModel);
        tblMovies.setAutoCreateRowSorter(true);
    }

    private void setIcon(JLabel pbImage, File file) {
        try {
            pbImage.setIcon(IconUtils.createIcon(file, pbImage.getWidth(), pbImage.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(MainUserPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to set icon!");
        }
    }

    private void clearForm() {
        hideErrors();
        validationFields.forEach(e -> e.setText(""));
        selectedMovie = null;
        pbImage.setIcon(null);
        tfImagePath.setText("");
    }

    private void fillForm(Movie selectedMovie) {
        if (selectedMovie.getImageUrl()!= null && Files.exists(Paths.get(selectedMovie.getImageUrl()))) {
            try {
                tfImagePath.setText(selectedMovie.getImageUrl());
                setIcon(pbImage, new File(selectedMovie.getImageUrl()));
            } catch (Exception e) {
                tfImagePath.setText("");
                pbImage.setIcon(null);
            }
        }
        tfTitle.setText(selectedMovie.getTitle());
        tfDescription.setText(selectedMovie.getDescription());
        tfOriginalTitle.setText(selectedMovie.getOriginalTitle());
        tfDuration.setText(Integer.toString(selectedMovie.getDurationMinutes()));
        tfYear.setText(Integer.toString(selectedMovie.getYear()));
    }

    private void hideErrors() {
        errorLabels.forEach(e -> e.setVisible(false));
    }

    private boolean formValid() {
        hideErrors();
        boolean ok = true;

        for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setVisible(validationFields.get(i).getText().trim().isEmpty());
        }
        try {
            Integer.parseInt(tfDuration.getText());
        } catch (Exception e) {
            lbDurationError.setVisible(true);
            ok = false;
        }
        try {
            Integer.parseInt(tfYear.getText());
        } catch (Exception e) {
            lbYearError.setVisible(true);
            ok = false;
        }
        return ok;
    }

    private void loadAll_A_D_G() {
        try {
            List<Person> allActors = (List<Person>) actorRepo.selectMany().stream().distinct().collect(Collectors.toList());
            List<Person> allDirectors = (List<Person>) directorRepo.selectMany().stream().distinct().collect(Collectors.toList());
            List<Genre> allGenres = (List<Genre>) genreRepo.selectMany().stream().distinct().collect(Collectors.toList());
            
            
            
            allActorListModel.clear();
            allDirectorListModel.clear();
            allGenreListModel.clear();
            
            allActors.forEach(allActorListModel::addElement);
            lsAllActors.setModel(allActorListModel);
            
            allDirectors.forEach(allDirectorListModel::addElement);
            lsAllDirectors.setModel(allDirectorListModel);
            
            allGenres.forEach(allGenreListModel::addElement);
            lsAllGenres.setModel(allGenreListModel);
            
        } catch (Exception ex) {
            Logger.getLogger(MainUserPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class ActorExportTransferHandler extends TransferHandler {

        public ActorExportTransferHandler() {
        }
        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
            return new PersonTransferable(lsAllActors.getSelectedValue());
        }
    }

    private static class ActorImportTransferHandler extends TransferHandler {

        public ActorImportTransferHandler() {
        }
        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(PersonTransferable.PERSON_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Person add = (Person) transferable.getTransferData(PersonTransferable.PERSON_FLAVOR);
                actorListModel.add(NONE, add);
                lsActors.setModel(actorListModel);
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(MainUserPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

    }

    private static class DirectorExportTransferHandler extends TransferHandler {

        public DirectorExportTransferHandler() {
        }
        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
            return new PersonTransferable(lsAllDirectors.getSelectedValue());
        }
    }

    private static class DirectorImportTransferHandler extends TransferHandler {

        public DirectorImportTransferHandler() {
        }
        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(PersonTransferable.PERSON_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Person add = (Person) transferable.getTransferData(PersonTransferable.PERSON_FLAVOR);
                directorListModel.add(NONE, add);
                lsDirectors.setModel(directorListModel);
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(MainUserPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    private static class GenreExportTransferHandler extends TransferHandler {

        public GenreExportTransferHandler() {
        }
        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
            return new GenreTransferable(lsAllGenres.getSelectedValue());
        }
    }

    private static class GenreImportTransferHandler extends TransferHandler {

        public GenreImportTransferHandler() {
        }
        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(GenreTransferable.GENRE_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Genre add = (Genre) transferable.getTransferData(GenreTransferable.GENRE_FLAVOR);
                genreListModel.add(NONE, add);
                lsGenres.setModel(genreListModel);
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(MainUserPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }
}
