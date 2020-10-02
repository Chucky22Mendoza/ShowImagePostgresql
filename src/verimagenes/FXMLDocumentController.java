/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verimagenes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 *
 * @author yimibus
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ImageView imgPanel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        peticionDB();
    }    
    
    /**
     * Convert Byte array to Image from JavaFX
     * 
     * @param arrayBytes
     * @return 
     */
    public Image proccessImage(byte[] arrayBytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(arrayBytes);
            BufferedImage image = ImageIO.read(bais);
            
            return SwingFXUtils.toFXImage(image, null);
        } catch (IOException ex) {
            Logger.getLogger(VerImagenes.class.getName()).log(Level.SEVERE, null, ex);
            
            return getImageGeneral();
        }
    }
    
    /**
     * Default Image
     * 
     * @return 
     */
    public Image getImageGeneral() {
        return new Image(getClass().getResourceAsStream("/me.PNG"));
    }
    
    /**
     * Set any image to GUI
     */
    public void peticionDB() {
        ConnectionDatabase connection = new ConnectionDatabase();
        connection.createConnection();
        Connection conn = connection.getConnection();
        
        try {
            String sql = "SELECT imagen FROM usuario WHERE id_usuario = '79118159-f381-11ea-8a15-7a791911bcec'::UUID";

            PreparedStatement preparedStatement = conn.prepareStatement(sql); //Evitar inyeccion SQL

            ResultSet result = preparedStatement.executeQuery();
            
            if (result.next()) {
                Image img = proccessImage(result.getBytes("imagen"));
                imgPanel.setImage(img);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        connection.closeConnection();
    }
}
