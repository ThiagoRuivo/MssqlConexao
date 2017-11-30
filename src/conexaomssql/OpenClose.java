
package conexaomssql;

import java.sql.SQLException;
import java.sql.Statement;

public class OpenClose {
  /*
    Campos
  */
  private final MssqlConexao conexao;

  /*
    Construtores
  */
  public OpenClose(MssqlConexao conexao) {
    this.conexao = conexao;
  }
  
  /*
    Get's & Set's
  */

  /*
    Demais métodos
  */
  public Statement abrirConexao () throws SQLException {
    System.out.println("Tentando conectar ao banco de dados...");
    String status = this.conexao.conectar();
    System.out.println(status);
    Statement sessao = this.conexao.getConexao().createStatement();
    return sessao;
  }
  public void fecharConexao () {
    System.out.println("Fechando conexão com o banco de dados");
    String status = this.conexao.desconectar();
    System.out.println(status);
  }
  
}
