
package conexaomssql;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
  /*
    Campos
  */
  
  /*
    Construtores
  */
  public PropertyReader () {  
  }
  
  /*
    Get's & Set's
  */

  /*
  Demais métodos
   */
  public Properties getFileProperty(String diretorio) throws IOException {
    Properties props = new Properties();
    FileInputStream file = new FileInputStream(diretorio);
    props.load(file);
    return props;
  }
  public String getProperty (String diretorio, String base, String propriedade) throws IOException {
    PropertyReader meuLeitor = new PropertyReader();
    Properties prop = meuLeitor.getFileProperty(diretorio);
    return prop.getProperty("prop." + base + "." + propriedade);
  }
  
}
