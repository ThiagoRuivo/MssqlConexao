
package conexaomssql;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MssqlCreateConnection {
  /*
    Campos
  */
  private static MssqlCreateConnection uniqueInstance;
  private String driver;
  private String host;
  private String stringConexao;
  private String usuario;
  private String senha;
  private String baseDados;
  private String jdbc;
  private int porta;
  private boolean intintegratedSecurity;

  /*
    Construtores
  */
  public MssqlCreateConnection(String arquivo) {
    PropertyReader pr = new PropertyReader();
    try {
      this.baseDados = pr.getProperty(arquivo, "server", "databaseName");
      this.driver = pr.getProperty(arquivo, "server", "driver");
      this.host = pr.getProperty(arquivo, "server", "host");
      this.jdbc = pr.getProperty(arquivo, "server", "jdbc");
      this.senha = pr.getProperty(arquivo, "server", "password");
      this.usuario = pr.getProperty(arquivo, "server", "user");
      this.porta = Integer.parseInt(pr.getProperty(arquivo, "server", "port"));
      this.intintegratedSecurity = Boolean.parseBoolean(pr.getProperty(arquivo, "server", "integratedSecurity"));
    } catch (IOException ex) {
      Logger.getLogger(MssqlCreateConnection.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /*
    Get's & Set's
  */
  public static MssqlCreateConnection getUniqueInstance() {
    return uniqueInstance;
  }
  public static void setUniqueInstance(MssqlCreateConnection uniqueInstance) {
    MssqlCreateConnection.uniqueInstance = uniqueInstance;
  }
  public String getDriver() {
    return driver;
  }
  public void setDriver(String driver) {
    this.driver = driver;
  }
  public String getHost() {
    return host;
  }
  public void setHost(String host) {
    this.host = host;
  }
  public String getStringConexao() {
    return stringConexao;
  }
  public void setStringConexao(String stringConexao) {
    this.stringConexao = stringConexao;
  }
  public String getUsuario() {
    return usuario;
  }
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }
  public String getSenha() {
    return senha;
  }
  public void setSenha(String senha) {
    this.senha = senha;
  }
  public String getBaseDados() {
    return baseDados;
  }
  public void setBaseDados(String baseDados) {
    this.baseDados = baseDados;
  }
  public String getJdbc() {
    return jdbc;
  }
  public void setJdbc(String jdbc) {
    this.jdbc = jdbc;
  }
  public int getPorta() {
    return porta;
  }
  public void setPorta(int porta) {
    this.porta = porta;
  }
  public boolean isIntintegratedSecurity() {
    return intintegratedSecurity;
  }
  public void setIntintegratedSecurity(boolean intintegratedSecurity) {
    this.intintegratedSecurity = intintegratedSecurity;
  }

  /*
    Demais métodos
  */
  public static synchronized  MssqlCreateConnection getInstance (String arquivo) {
    if (uniqueInstance == null) {
      uniqueInstance = new MssqlCreateConnection(arquivo);
    }
    return uniqueInstance;
  }
  public MssqlConexao configurarConexao () {
    MssqlConexao novaConexao = new MssqlConexao();
    novaConexao.configurarConexao(driver, host, usuario, senha, baseDados, jdbc, intintegratedSecurity, porta);
    return novaConexao;
  }
}
