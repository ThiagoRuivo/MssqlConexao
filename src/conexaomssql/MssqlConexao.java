
package conexaomssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MssqlConexao {
  /*
    Campos
  */
  public Connection conn;
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
  public MssqlConexao() {
    this.porta = 0;
    this.intintegratedSecurity = false;
  }
  public MssqlConexao(Connection conn, String driver, String usuario, String host, String senha, String baseDados, String jdbc, boolean intintegratedSecurity, int porta) {
    this.conn = conn;
    this.driver = driver;
    this.jdbc = jdbc;
    this.usuario = usuario;
    this.senha = senha;
    this.baseDados = baseDados;
    this.intintegratedSecurity = intintegratedSecurity;
    this.porta = porta;
    this.host = host;
  }
  
  /*
    Get's & Set's
  */
  public Connection getConexao () {
    return this.conn;
  }
  public void setConexao(Connection conn) {
    this.conn = conn;
  }
  public String getDriver() {
    return driver;
  }
  public void setDriver(String driver) {
    this.driver = driver;
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
  public boolean isIntintegratedSecurity() {
    return intintegratedSecurity;
  }
  public void setIntintegratedSecurity(boolean intintegratedSecurity) {
    this.intintegratedSecurity = intintegratedSecurity;
  }
  public int getPorta() {
    return porta;
  }
  public void setPorta(int porta) {
    this.porta = porta;
  }
  public Connection getConn() {
    return conn;
  }
  public void setConn(Connection conn) {
    this.conn = conn;
  }
  public String getJdbc() {
    return jdbc;
  }
  public void setJdbc(String jdbc) {
    this.jdbc = jdbc;
  }
  public String getHost() {
    return host;
  }
  public void setHost(String host) {
    this.host = host;
  }

  /*
    Demais métodos
  */
  public void configurarConexao (String driver, String host, String usuario, String senha, String baseDados, String jdbc, boolean intintegratedSecurity, int porta) {
    this.driver = driver;
    this.usuario = usuario;
    this.senha = senha;
    this.baseDados = baseDados;
    this.jdbc = jdbc;
    this.intintegratedSecurity = intintegratedSecurity;
    this.porta = porta;
    this.host = host;
    this.stringConexao = String.format("%s%s:%d;databaseName=%s;integratedSecurity=%s;", this.jdbc, this.host, this.porta, this.baseDados, this.intintegratedSecurity);
  }
  private boolean verificarConfiguracoes () {
    boolean[] validade = new boolean[8];
    boolean valido = true;
    validade[0] = this.baseDados != null;
    validade[1] = this.driver != null;
    validade[2] = this.host != null;
    validade[3] = this.jdbc != null;
    validade[4] = this.porta != 0;
    validade[5] = this.senha != null;
    validade[6] = this.stringConexao != null;
    validade[7] = this.usuario != null;
    for (int i = 0; i < validade.length; i++) {
      if (!validade[i]) {
        valido = false;
      }
    }
    return valido;
  }
  public String conectar () {
    if (verificarConfiguracoes()) {
      try {
        Class.forName(this.driver);
        conn = (Connection) DriverManager.getConnection(this.stringConexao, this.usuario, this.senha);
        return "Conectado com sucesso";
      } catch (SQLException e) {
        Logger.getLogger(MssqlConexao.class.getName()).log(Level.SEVERE, null, e);
        return "Erro de conexao\n Erro: " + e.getMessage();
      } catch (ClassNotFoundException e) {
        Logger.getLogger(MssqlConexao.class.getName()).log(Level.SEVERE, null, e);
        return "Erro de conexao\n Erro: " + e.getMessage();
      }
    } else {
      return "Um ou mais parâmetros de configurações está vazio, verifique (sem conexão)";
    }
  }
  public String desconectar () {
    if (this.conn == null) {
      return "Conexão nula";
    } else {
      try {
        conn.close();
        return "Desconectado com sucesso";
      } catch (SQLException ex) {
        Logger.getLogger(MssqlConexao.class.getName()).log(Level.SEVERE, null, ex);
        return "Erro ao desconectar\n Erro: " + ex.getMessage();
      }
    }
  }
  
}
