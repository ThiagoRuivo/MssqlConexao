
package conexaomssql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAcessTeste extends DataAcess{
  /*
    Campos
  */

  /*
    Construtores
  */

  /*
    Get's & Set's
  */

  /*
    Demais métodos
  */
  public String selecionar (int pk) {
    String retorno = "";
    try {
      Statement sessao = this.getOpenClose().abrirConexao();
      String sql = String.format("exec dbo.spcTarefasSel @pCodigo = %d", pk);
      ResultSet resultado = sessao.executeQuery(sql);
      while (resultado.next()) {
        retorno = resultado.getString("titulo");
      }
    } catch (SQLException ex) {
      Logger.getLogger(DataAcessTeste.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
  }
}
