
# Set Image of database postgresql to GUI JavaFX

```java

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

```
