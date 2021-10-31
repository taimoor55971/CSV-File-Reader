package com.swingApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class myframe extends JFrame implements ActionListener {
    JButton browseButton;
    JTextArea Path;
    JTable jTable1;
    JButton Visualize;
    JButton LoadButton;
    JButton Reset;
    JScrollPane scroll;
    JButton Exit;



    myframe(){

    this.setLayout(new FlowLayout());
        this.setSize(540,340);
        this.setResizable(false);
        this.setLocation(500,150);
        this.setTitle("CSV File Reader and Visualizer");





        JLabel browseLabel=new JLabel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        browseLabel.setText("File Path:");
        browseLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        browseButton=new JButton();
        browseButton.addActionListener(this);
        browseButton.setText("Browse");
        browseButton.setFont(new Font("Sans Serif", Font.PLAIN, 18));


        Path=new JTextArea();
        Path.setPreferredSize(new Dimension(250,40));
        Path.setEditable(false);


         jTable1 = new JTable();
         scroll=new JScrollPane(jTable1);
         scroll.setPreferredSize(new Dimension(500,200));





        Visualize=new JButton();
        Visualize.addActionListener(this);
        Visualize.setText("Visualize");
        Visualize.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        LoadButton=new JButton();
        LoadButton.addActionListener(this);
        LoadButton.setText("Load");
        LoadButton.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        Reset=new JButton();
        Reset.addActionListener(this);
        Reset.setText("Reset");
        Reset.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        Exit=new JButton();
        Exit.addActionListener(e -> System.exit(0));
        Exit.setText("Exit");
        Exit.setFont(new Font("Sans Serif", Font.PLAIN, 18));











        this.add(browseLabel);
        this.add(Path);
        this.add(browseButton);
        this.add(scroll);
        this.add(Visualize);
        this.add(LoadButton);
        this.add(Reset);
        this.add(Exit);




        this.setVisible(true);



    }
    public void actionPerformed(ActionEvent e) {

        /* Browse Button Listener */
        if(e.getSource()==browseButton){
            JFileChooser browser=new JFileChooser();
            browser.setCurrentDirectory(new File("."));
            int res=browser.showOpenDialog(null);



            if (res == JFileChooser.FILES_ONLY) {

                File file = new File(browser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
                Path.setText(file.getPath());
                Path.setFont(new Font("Sans Serif", Font.PLAIN, 18));



            }else {
                System.out.println("file not found");
            }


        }
        ///////////////////////Visualise Button///////////////////

        if(e.getSource()==Visualize){




        }


        ///////////////////Load Button Listener
        if(e.getSource()==LoadButton){
            String filePath = Path.getText();
            File file = new File(filePath);
            try {
                BufferedReader reader=new BufferedReader(new FileReader(file));
                String firstLine = reader.readLine().trim();
                String[] columnsName = firstLine.split(",");
                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
                model.setColumnIdentifiers(columnsName);
                Object[] tableLines = reader.lines().toArray();




                // extratct data from lines
                // set data to jtable model
                for (Object tableLine : tableLines) {
                    String line = tableLine.toString().trim();
                    String[] dataRow = line.split(",");
                    model.addRow(dataRow);


                }
                LoadButton.setEnabled(false);
                Reset.setEnabled(true);

            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }
        //////////////Reset Button Listener

        if(e.getSource()==Reset){
            Path.setText("");

            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();


            while(model.getRowCount()>0){
                for(int i=0;i<model.getRowCount();i++){
                    model.removeRow(i);
                }

            }
            LoadButton.setEnabled(true);
            Reset.setEnabled(false);


        }

    }


    }

