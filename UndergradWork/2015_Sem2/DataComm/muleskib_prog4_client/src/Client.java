import java.net.*;
import java.io.*;
import java.util.*;

/**
Represents an FTPClient. The form allows the user to connect to a server and
send and receive the files available.
@author Brianna Muleski
*/
public class Client extends javax.swing.JFrame 
{
    private final int CHUNK_SIZE = 1024;
    private final String CRLF = "\r\n";
    
    private Socket socket;
    private DataOutputStream out;
    private BufferedReader in;
    private Socket d_socket;
    private DataOutputStream d_out;
    private DataInputStream d_in;
    private Vector<String> server_files = new Vector();
    private Vector<String> client_files = new Vector();
    
    /**
    Creates new form Client, initializes the components and lists the local
    files available for transfer.
    */
    public Client() 
    {
        initComponents();
        ListLocalFiles();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hostLbl = new javax.swing.JLabel();
        portLbl = new javax.swing.JLabel();
        hostTxt = new javax.swing.JTextField();
        portTxt = new javax.swing.JTextField();
        connectBtn = new javax.swing.JButton();
        getBtn = new javax.swing.JButton();
        putBtn = new javax.swing.JButton();
        sfLbl = new javax.swing.JLabel();
        cfLbl = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        outputLbl = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        clientFiles = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        serverFiles = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Program 4 - FTP Client");

        hostLbl.setText("Host");

        portLbl.setText("Port");

        connectBtn.setText("CONNECT");
        connectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectBtnActionPerformed(evt);
            }
        });

        getBtn.setText("GET");
        getBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getBtnActionPerformed(evt);
            }
        });

        putBtn.setText("PUT");
        putBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                putBtnActionPerformed(evt);
            }
        });

        sfLbl.setText("Server Files");

        cfLbl.setText("Client Files");

        output.setColumns(20);
        output.setRows(5);
        jScrollPane3.setViewportView(output);
        output.getAccessibleContext().setAccessibleName("output");

        outputLbl.setText("Output Messages");

        clientFiles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(clientFiles);

        serverFiles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(serverFiles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hostLbl)
                        .addGap(12, 12, 12)
                        .addComponent(hostTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(portLbl)
                        .addGap(12, 12, 12)
                        .addComponent(portTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(connectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sfLbl)
                        .addGap(237, 237, 237)
                        .addComponent(cfLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(getBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(putBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(outputLbl)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(hostLbl))
                    .addComponent(hostTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(portLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(portTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(connectBtn))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sfLbl)
                    .addComponent(cfLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(putBtn)
                    .addComponent(getBtn))
                .addGap(28, 28, 28)
                .addComponent(outputLbl)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        hostLbl.getAccessibleContext().setAccessibleName("hostLbl");
        portLbl.getAccessibleContext().setAccessibleName("portLbl");
        hostTxt.getAccessibleContext().setAccessibleName("hostTxt");
        portTxt.getAccessibleContext().setAccessibleName("portTxt");
        connectBtn.getAccessibleContext().setAccessibleName("connectBtn");
        getBtn.getAccessibleContext().setAccessibleName("getBtn");
        putBtn.getAccessibleContext().setAccessibleName("putBtn");
        sfLbl.getAccessibleContext().setAccessibleName("sfLbl");
        cfLbl.getAccessibleContext().setAccessibleName("cfLbl");
        outputLbl.getAccessibleContext().setAccessibleName("outputLbl");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    Handles the action of pressing the connect button. Tries to connect to the
    IP address and port specified by the user. If connection fails, an error
    message is displayed.
    @param evt pressing the connect button 
    */
    private void connectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectBtnActionPerformed
        try
        {
            if(connectBtn.getText().equals("CONNECT"))
            {
                int port = Integer.parseInt(portTxt.getText());
                InetAddress ip = InetAddress.getByName(hostTxt.getText());
                socket = new Socket(ip, port);
                out = new DataOutputStream(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(
                                        socket.getInputStream()));
                ListRemoteFiles();
                output.append("Connected to Server\n");
                connectBtn.setText("DISCONNECT");
            }
            else
            {
                Disconnect();
            }
        }
        catch (NumberFormatException | IOException e)
        {
            output.append("Error: " + e + "\n");
        }
    }//GEN-LAST:event_connectBtnActionPerformed

    /**
    Handles the action of pressing the UT button. If a file is selected, then
    a request is sent to the server to send the selected file. Catches an
    IOException or FileNotFoundException if an error occurs when sending the 
    request or sending the file.
    @param evt pressing the PUT button 
    */
    private void putBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_putBtnActionPerformed
        int selection = clientFiles.getSelectedIndex();
        if(selection != -1)
        {
            try 
            {
                String file = client_files.elementAt(selection);
                out.writeBytes("PUT " + file + CRLF);
                SendFile(file);
            } 
            catch (FileNotFoundException ex) 
            {
                output.append("FileNotFoundException: " + ex.getMessage() 
                                + "\n");
            } 
            catch (IOException ex) 
            {
                output.append("IOException: " + ex.getMessage() + "\n");
            }
        }
    }//GEN-LAST:event_putBtnActionPerformed

    /**
    Handles the action of pressing the GET button. If a file is selected, then
    a request is sent to the server to get the selected file. Catches an
    IOException or FileNotFoundException if an error occurs when sending the 
    request or downloading the file.
    @param evt pressing the GET button 
    */
    private void getBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getBtnActionPerformed
        int selection = serverFiles.getSelectedIndex();
        if(selection != -1)
        {
            try 
            {
                String file = server_files.elementAt(selection);
                out.writeBytes("GET " + file + CRLF);
                GetFile(file);
            } 
            catch (FileNotFoundException ex) 
            {
                output.append("FileNotFoundException: " + ex.getMessage() 
                                + "\n");
            } 
            catch (IOException ex) 
            {
                output.append("IOException: " + ex.getMessage() + "\n");
            }
        }        
    }//GEN-LAST:event_getBtnActionPerformed

    /**
    Tries to disconnect from the sever, closes the I/O stream and socket.
    Catches an IOException if an error occurs when closing the connection.
    */
    private void Disconnect()
    {
        try
        {
            out.writeBytes("DISCONNECT" + CRLF);
            if(!socket.isClosed())
            {
               in.close();
               out.close();
               socket.close();
            }

            output.append("Disconnected\n");          
        }
        catch(IOException e)
        {
            output.append("IO Exception: " + e.getMessage() + "\n");
        }
        connectBtn.setText("CONNECT"); 
    } //end of Disconnect()
    
    /**
    Lists the remote files from the server available for transfer. Catches an
    IOException if an error occurs when reading the available files.
    */
    private void ListRemoteFiles()
    {
        try 
        {
            String input = in.readLine();
            StringTokenizer st = new StringTokenizer(input);
            while (st.hasMoreTokens())
            {
                String token = st.nextToken();
                server_files.add(token);
            }
            serverFiles.setListData(server_files);
        } 
        catch (IOException ex) 
        {
            output.append("IO Exception: " + ex.getMessage() + "\n");
        }
    } //end of ListRemoteFiles()
    
    /**
    Lists the local files available for transfer.
    */
    private void ListLocalFiles()
    {
        File dir = new File("Files");
        File [] files = dir.listFiles();
        for(int i = 0; i < files.length; i++)
        {
            if(files[i].isFile())
            {
                client_files.add(files[i].getName());
            }
        }
        
        clientFiles.setListData(client_files);
    } //end of ListLocalFiles()
    
    /**
    Sends the requested file via the data connection, if it exists. Opens a
    data connection, and if the connection is opened successfully then sends 
    the file in 1024-byte chunks. Closes the connection. Prints the status of
    the file transfer.
    @param filename the requested file
    @throws FileNotFoundException if the file requested is not found.
    @throws IOException if an error occurs when reading from the file or 
            writing to the output stream.
    */
    private void SendFile(String filename) throws FileNotFoundException, 
                                                  IOException
    {
        boolean no_error = OpenDataConnection();
        if(no_error)
        {
            if(client_files.contains(filename))
            {
                String path = "Files\\" + filename;
                File file = new File(path);
                FileInputStream f_in = new FileInputStream(file);
                output.append("Sending the file...\n");
                byte[] buffer = new byte[CHUNK_SIZE];
                int buff_size = f_in.read(buffer);
                while(buff_size != -1)
                {
                    d_out.write(buffer, 0, buff_size);
                    buff_size = f_in.read(buffer);
                }
                output.append("File: " + filename + "\n");
                output.append(file.length() + " bytes sent\n");
                CloseDataConnection();
                f_in.close();
                RefreshLists();
            }
            else
            {
                output.append("File requested does not exist.\n");
            }
        } 
    } //end of SendFile()
    
    /**
    Downloads the file sent by the client. Opens a data connection, and if the
    connection is opened successfully then downloads the file in 1024-byte 
    chunks. Closes the connection. Prints the status of the file transfer. 
    @param filename the sent file
    @throws FileNotFoundException if the file sent is not found.
    @throws IOException if an error occurs when writing to the file or 
            reading from the input stream.
    */
    private void GetFile(String filename) throws FileNotFoundException, 
                                                 IOException
    {
        boolean no_error = OpenDataConnection();
        if(no_error)
        {
            output.append("Receiving the file...\n");
            String path = "Files\\" + filename;
            File file = new File(path); 
            FileOutputStream f_out = new FileOutputStream(file);
            byte[] buffer = new byte[CHUNK_SIZE];
            int buff_size = d_in.read(buffer);
            while(buff_size != -1)
            {
                f_out.write(buffer, 0, buff_size);
                f_out.flush();
                buff_size = d_in.read(buffer);
            }
            f_out.close();
            output.append("File: " + filename + "\n");
            output.append("Size: " + file.length() + " bytes\n");
            CloseDataConnection();
            RefreshLists();
        }
    } //end of GetFile()
    
    /**
    Opens a data connection. Uses the port sent by the server.
    Catches an IOException if an error occurs when establishing the new 
    connection or outputting through the control output stream. 
    @return true if connected successfully, false otherwise
    */
    private boolean OpenDataConnection()
    {
        boolean result = true;
        try 
        {
            int d_port = Integer.parseInt(in.readLine());
            InetAddress ip = InetAddress.getByName(hostTxt.getText());
            d_socket = new Socket(ip, d_port);
            d_in = new DataInputStream(d_socket.getInputStream());
            d_out = new DataOutputStream(d_socket.getOutputStream());
            output.append("Data Connection opened\n");
        } 
        catch (IOException ex) 
        {
            output.append("IOException: " + ex.getMessage() + "\n");
            result = false;
        }
        return result;
    } //end of OpenDataConnection()
    
    /**
    Closes the data connection.
    Catches an IOExcpetion if an error occurs when attempting to
    close the data connection and its I/O stream.
    */
    private void CloseDataConnection()
    {
        try 
        {
            if(d_socket != null && !d_socket.isClosed())
            {
                d_in.close();
                d_out.close();
                d_socket.close();
            }
            output.append("Data Connection closed.\n");
        } 
        catch (IOException ex) 
        {
            output.append("IOException: " + ex.getMessage() + "\n");
        }
    } //end of CloseDataConnection()
    
    /**
    Refreshes the available lists of files for the client and server.
    */
    private void RefreshLists() 
    {
       server_files.clear();
       client_files.clear();
       ListLocalFiles();
       ListRemoteFiles();
    } //end of RefreshLists()
    
    /**
    Runs the frame.
    @param args the command line arguments
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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cfLbl;
    private javax.swing.JList clientFiles;
    private javax.swing.JButton connectBtn;
    private javax.swing.JButton getBtn;
    private javax.swing.JLabel hostLbl;
    private javax.swing.JTextField hostTxt;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea output;
    private javax.swing.JLabel outputLbl;
    private javax.swing.JLabel portLbl;
    private javax.swing.JTextField portTxt;
    private javax.swing.JButton putBtn;
    private javax.swing.JList serverFiles;
    private javax.swing.JLabel sfLbl;
    // End of variables declaration//GEN-END:variables
}
