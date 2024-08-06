
//Classe de conexão com o banco de dados
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class veiculo_DAO {

    private final String url = "jdbc:mysql://localhost:3306/cadastro_veiculos";
    private final String user = "root";
    private final String password = "";

    public boolean chassiExiste(String chassi, Veiculo carSelecionado) {
        String SQL = "";
        if (carSelecionado != null) {
            SQL = "SELECT COUNT(*) FROM cadastro WHERE chassi = ? and codigo <> ?";
        } else {
            SQL = "SELECT COUNT(*) FROM cadastro WHERE chassi = ?";
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, chassi);
            if (carSelecionado != null) {
                pstmt.setInt(2, carSelecionado.getCod());
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void salvarCadastro(Veiculo veiculo) {

        String SQL = "INSERT INTO cadastro (chassi, modelo, ano, fabricante, kmAtual, opcionais, descricao, motorizacao, combustivel, cor, fimPlaca, carroceria, cidade, valor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, veiculo.getChassi());
            pstmt.setString(2, veiculo.getModelo());
            pstmt.setInt(3, veiculo.getAno());
            pstmt.setString(4, veiculo.getFabricante());
            pstmt.setInt(5, veiculo.getKmAtual());
            pstmt.setString(6, veiculo.getOpcionais());
            pstmt.setString(7, veiculo.getDescricao());
            pstmt.setString(8, veiculo.getMotorizacao());
            pstmt.setString(9, veiculo.getCombustivel());
            pstmt.setString(10, veiculo.getCor());
            pstmt.setString(11, veiculo.getFinalPlaca());
            pstmt.setString(12, veiculo.getCarroceria());
            pstmt.setString(13, veiculo.getCidade());
            pstmt.setDouble(14, veiculo.getValor());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Cadastro salvo com sucesso!");
                JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao salvar o cadastro.");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao salvar cadastro: " + ex.getMessage());
        }
    }

    // Método que retorna uma lista de autmóveis que batem com o chassi pesquisado
    public ArrayList<Veiculo> pesquisarCadastro(String chassi) {
        ArrayList<Veiculo> listCar = new ArrayList<>();
        String SQL = "select * from cadastro where chassi like '" + chassi + "%'";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Veiculo car = new Veiculo();
                car.setChassi(rs.getString("chassi"));
                car.setModelo(rs.getString("modelo"));
                car.setFabricante(rs.getString("fabricante"));
                car.setKmAtual(rs.getInt("kmAtual"));
                car.setMotorizacao(rs.getString("motorizacao"));
                car.setCombustivel(rs.getString("combustivel"));
                car.setCidade(rs.getString("cidade"));
                car.setCarroceria(rs.getString("carroceria"));
                car.setValor(rs.getDouble("valor"));
                car.setAno(rs.getInt("ano"));
                car.setCor(rs.getString("cor"));
                car.setOpcionais(rs.getString("opcionais"));
                car.setDescricao(rs.getString("descricao"));
                car.setFinalPlaca(rs.getString("fimPlaca"));
                car.setCod(rs.getInt("codigo"));
                listCar.add(car);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO(listarProduto - Erro: +)" + e);
        }

        if (listCar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Autmóvel não cadastrado!");
        }
        return listCar;
    }

    // Método para atualizar um cadastro existente
    public void atualizarVeiculo(Veiculo carAlterado, Veiculo carParaSerAlterado) {
        String SQL = "update cadastro set chassi=?, modelo=?, fabricante=?, kmAtual=?, motorizacao=?, combustivel=?, cidade=?, carroceria=?, valor=?, ano=?, cor=?, opcionais=?, descricao=?, fimPlaca=? where codigo=?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, carAlterado.getChassi());
            pstmt.setString(2, carAlterado.getModelo());
            pstmt.setString(3, carAlterado.getFabricante());
            pstmt.setString(4, String.valueOf(carAlterado.getKmAtual()));
            pstmt.setString(5, carAlterado.getMotorizacao());
            pstmt.setString(6, carAlterado.getCombustivel());
            pstmt.setString(7, carAlterado.getCidade());
            pstmt.setString(8, carAlterado.getCarroceria());
            pstmt.setString(9, String.valueOf(carAlterado.getValor()));
            pstmt.setString(10, String.valueOf(carAlterado.getAno()));
            pstmt.setString(11, carAlterado.getCor());
            pstmt.setString(12, carAlterado.getOpcionais());
            pstmt.setString(13, carAlterado.getDescricao());
            pstmt.setString(14, String.valueOf(carAlterado.getFinalPlaca()));
            pstmt.setString(15, String.valueOf(carParaSerAlterado.getCod()));

            System.out.println(pstmt);
            boolean linhasAfetadas = pstmt.execute();

            if (!linhasAfetadas) {
                System.out.println("Cadastro alterado com sucesso!");
                JOptionPane.showMessageDialog(null, "Veículo alterado com sucesso!");
                App.limparCampos();
            } else {
                System.out.println("Falha ao alterar o cadastro.");
                JOptionPane.showMessageDialog(null, "Falha ao alterar o cadastro.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao alterar o cadastro: " + ex.getMessage());
        }
    }

    public void excluirVeiculo(Veiculo car) {
        String SQL = "delete from cadastro where codigo = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, car.getCod());
            boolean result = pstmt.execute();
            pstmt.close();

            

            if (!result) {
                JOptionPane.showMessageDialog(null, "Registro Excluido!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao excluir o veículo!");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao excluir o cadastro: " + ex.getMessage());
        }
    }
}