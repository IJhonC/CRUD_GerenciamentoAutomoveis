import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class TelaPesquisa extends JFrame {
    veiculo_DAO dao = new veiculo_DAO();
    // Painel dos componentes
    private static JPanel pnlPesquisa;

    // Componentes
    private static JTextField tPesquisa;
    private static JTable tblPesquisa;
    private static JButton btnPesquisa;
    private static JButton btnFechar;
    private static JButton btnLimpar;

    // Lista Para Pesquisa
    ArrayList<Veiculo> listData = new ArrayList<>();;

    public TelaPesquisa() {
        // Formulário de Pesquisa
        super("Pesquisa de Automóveis");
        setSize(708, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        // Adiciona um listenar para atulizar os botões da tela principal caso o usuário
        // aperta no X para
        // fechar a tela de pesquisa
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                App.btnPesquisar.setEnabled(true);
                ;
            }
        });

        // Bordas e Cores
        Border gray = BorderFactory.createLineBorder(Color.WHITE);
        Color cinza = new Color(211, 211, 211);
        getContentPane().setBackground(cinza);

        // Configuraçãp do painel dos componentes
        pnlPesquisa = new JPanel();
        pnlPesquisa.setBounds(10, 10, 672, 90);
        pnlPesquisa.setLayout(null);
        pnlPesquisa.setBorder(gray);
        pnlPesquisa.setVisible(true);
        add(pnlPesquisa);

        // Componentes do campo de pesquisa

        JLabel lblPesquisa = new JLabel("Insira o chassi do carro:");
        lblPesquisa.setBounds(10, 15, 200, 15);
        pnlPesquisa.add(lblPesquisa);

        tPesquisa = new JTextField();
        tPesquisa.setBounds(10, 35, 650, 30);
        pnlPesquisa.add(tPesquisa);

        // Listener para tratar quando o usuário aperta ENTER para a pesquisa
        tPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });

        // Tabela de Rotorno da pesquisa
        tblPesquisa = new JTable();
        atualizarTabela();

        // ScrollPane da tabela de retorno da pesquisa
        JScrollPane scrlTbCli = new JScrollPane(tblPesquisa);
        scrlTbCli.setBounds(10, 120, 673, 230);
        add(scrlTbCli);

        // Botões
        btnPesquisa = new JButton("Pesquisar");
        btnPesquisa.setBounds(10, 370, 120, 30);
        btnPesquisa.setBorder(new App.RoundedBorder(8));
        add(btnPesquisa);

        btnFechar = new JButton("Fechar");
        btnFechar.setBounds(140, 370, 120, 30);
        btnFechar.setBorder(new App.RoundedBorder(8));
        add(btnFechar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(270, 370, 120, 30);
        btnLimpar.setBorder(new App.RoundedBorder(8));
        add(btnLimpar);

        // Listeners para a função dos botões

        // Botão fechar
        btnFechar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App.btnPesquisar.setEnabled(true);
            }
        });

        // Botão Pesquisar
        btnPesquisa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });

        // Botão Limpar
        btnLimpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tPesquisa.setText("");

                DefaultTableModel modeloPesquisaLimpo = new DefaultTableModel(new Object[] {
                        "Chassi",
                        "Modelo",
                        "Ano",
                        "Cor",
                        "Final da Placa" }, 0);

                tblPesquisa.setModel(modeloPesquisaLimpo);
            }
        });

        /*
         * Adiciona o listner na tabela para quando der duplo clique, fechar a tela
         * de pesquisa e resgatar o veiculo selecionado
         */
        tblPesquisa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int linhaSelecioanda = tblPesquisa.rowAtPoint(e.getPoint());
                    if (linhaSelecioanda != -1) {
                        String chassiSelecionado = String.valueOf(tblPesquisa.getValueAt(linhaSelecioanda, 0));
                        setVisible(false);

                        App.btnPesquisar.setEnabled(true);
                        App.btnAlterar.setEnabled(true);
                        App.btnExcluir.setEnabled(true);
                        App.btnCadastrar.setEnabled(false);

                        Veiculo carSelecionado = dao.pesquisarCadastro(chassiSelecionado).get(0);
                        App.atualizarCampos(carSelecionado);
                    }
                }
            }
        });
    }

    // Método para atualizar a tabela de retorno da pesquisa
    public void atualizarTabela() {
        // Modelo da Tabela de Retorno da Pesquisa
        DefaultTableModel modeloPesquisa = new DefaultTableModel(new Object[] {
                "Chassi",
                "Modelo",
                "Ano",
                "Cor",
                "Final da Placa" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // Esse laço percorre toda a lista de carros e adiciona cada carro em uma linha
        // da tebela
        for (Veiculo car : listData) {
            Object linha[] = new Object[] {
                    car.getChassi(),
                    car.getModelo(),
                    car.getAno(),
                    car.getCor(),
                    car.getFinalPlaca() };
            modeloPesquisa.addRow(linha);
        }
        tblPesquisa.setModel(modeloPesquisa);

    }

    // Método para verificação e pesquisa
    private void pesquisar() {
        // Verificação se o campo de pesquisa está em branco
        if (tPesquisa.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo de pesquisa em branco!");
            return;
        }
        // Verificação se o campo de pesquisa contem pelo menos 3 carcteres
        if (tPesquisa.getText().trim().length() < 3) {
            JOptionPane.showMessageDialog(null,
                    "Digite pelo menos 3 carcteres numéricos");
            return;
        }
        listData = dao.pesquisarCadastro(tPesquisa.getText());
        atualizarTabela();
    }
}
