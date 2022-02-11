/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.mak.ujdevvalt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Math.ceil;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author szalaijo
 * Adott mapában levő vagy kitallózott deviza árfolyamokat tartalmazó XML
 * állomány alapján a kiválasztott devizát Ft-ra váltja
 * 
 * Az XML frissíthető a https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml linkről
 * kiválasztott mappába
 */
public class DevizaValt extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form DevizaValt
     */
    private static float forintArfolyam;
    private static String XMLFileName = "D:\\szalaijo\\Documents\\arfolyam.xml";

    public DevizaValt() {
        initComponents();
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        jButton4.addActionListener(this);
        jTextField1.setText(XMLFileName);
        xmlBetolt();
    }
    
    /**
     * A form-on levő vezérlők eseménykezelője
     * @param ae 
     */
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (command.equals("talloz")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Az XML helyének kiválasztása");
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML fájlok", "xml");
            fileChooser.addChoosableFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                XMLFileName = fileChooser.getSelectedFile().getPath();
                jTextField1.setText(XMLFileName);
                xmlBetolt();
            }
        } else if (command.equals("atvalt")) {
            float valtando;
            float arfolyam;
            float valtott;

            valtando = Float.valueOf(jTextField2.getText());
            arfolyam = Float.valueOf(jComboBox1.getSelectedItem().toString().substring(jComboBox1.getSelectedItem().toString().indexOf("-") + 1));
            valtott = (valtando / arfolyam) * forintArfolyam;
            jTextField3.setText(String.valueOf(ceil(valtott)));
        } else if (command.equals("letolt")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Letöltött XML helyének kiválasztása");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                XMLFileName = fileChooser.getSelectedFile().getPath() + "\\arfolyam_" +
                        new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".xml";
                jTextField1.setText(XMLFileName);
            }
            try {
                URL myUrl = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
                
                //Az alábbi is egy lehetséges megoldás
                //ReadableByteChannel rbc = Channels.newChannel(myUrl.openStream());
                //FileOutputStream fos = new FileOutputStream(XMLFileName);
                //FileChannel fc = fos.getChannel();
                //fc.transferFrom(rbc, 0, Long.MAX_VALUE);

                InputStream stream = myUrl.openStream();
                FileOutputStream fos = new FileOutputStream(XMLFileName);
                fos.write(stream.readAllBytes());
                fos.close();
            } catch (Exception e) {
                jTextField1.setText(e.getMessage());
                Logger.getLogger(DevizaValt.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                
            }
        } else if (command.equals("kilep")) {
            System.exit(0);
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
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Deviza átváltó");
        jLabel1.setToolTipText("");

        jTextField1.setToolTipText("");

        jButton1.setText("Tallóz");
        jButton1.setToolTipText("");
        jButton1.setActionCommand("talloz");

        jTextField2.setToolTipText("");

        jLabel2.setText("Összeg:");
        jLabel2.setToolTipText("");

        jLabel3.setText("Valuta:");
        jLabel3.setToolTipText("");

        jButton2.setText("Átvált");
        jButton2.setToolTipText("");
        jButton2.setActionCommand("atvalt");

        jLabel4.setText("Átváltott:");
        jLabel4.setToolTipText("");

        jTextField3.setToolTipText("");

        jLabel5.setText("Árfolyam XML:");
        jLabel5.setToolTipText("");

        jButton3.setText("Letölt");
        jButton3.setToolTipText("");
        jButton3.setActionCommand("letolt");

        jButton4.setText("Kilép");
        jButton4.setToolTipText("");
        jButton4.setActionCommand("kilep");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(30, 30, 30)
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton2)))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DevizaValt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DevizaValt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DevizaValt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DevizaValt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        new CertificatValidator();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DevizaValt().setVisible(true);
            }
        });
    }
    
    /**
     * Az XMLFileName sztringben levő fájlnév alapján betölti az árfolyamokat a combo box-ba
     */
    public void xmlBetolt() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        jComboBox1.removeAllItems();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(XMLFileName);
            doc.getDocumentElement().normalize();
            NodeList nl = doc.getElementsByTagName("Cube");
            for (int i = 0; i < nl.getLength(); i++) {
                Element elem = (Element) nl.item(i);
                if (elem.getAttribute("currency") != null && !elem.getAttribute("currency").isEmpty()) {
                    jComboBox1.addItem(elem.getAttribute("currency") + "-" + elem.getAttribute("rate"));
                    if (elem.getAttribute("currency").equals("HUF")) {
                        forintArfolyam = Float.valueOf(elem.getAttribute("rate"));
                    }
                }

            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            jTextField1.setText(e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
