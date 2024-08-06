
//Essa é classe principal do programa, aqui a tela seus botões, textField, labels são formatados.
//Aqui são criados todas as máscaras e exceções do programa

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

public class App extends JFrame {

    // Painel em que são adicionados os campos de texto comuns
    private static JPanel painelDados;

    // Botôes da tela principal
    public static JButton btnCadastrar;
    public static JButton btnAlterar;
    public static JButton btnPesquisar;
    public static JButton btnExcluir;
    private static JButton btnLimpar;

    // Todos os componentes da tela principal
    private static JLabel tChassi;
    private static JTextField chassi;
    private static JLabel tModelo;
    private static JTextField modelo;
    private static JLabel tFabricante;
    private static JTextField fabricante;
    private static JLabel tAno;
    private static JFormattedTextField ano;
    private static JLabel tKmAtual;
    private static JFormattedTextField kmAtual;
    private static JLabel tMotorizacao;
    private static JComboBox<String> motorizacao;
    private static JLabel tFinalPlaca;
    private static JTextField finalPlaca;
    private static JLabel tCor;
    private static JComboBox<String> cor;
    private static JLabel tCombustivel;
    private static JComboBox<String> combustivel;
    private static JLabel tCidade;
    private static JTextField cidade;
    private static JLabel tCarroceria;
    private static JComboBox<String> carroceria;
    private static JLabel tValor;
    private static JTextField valor;
    private static JLabel tOpcionais;
    private static JTextField opcionais;
    private static JLabel TDescricao;
    private static JTextField descricao;

    // Objeto Veiculo que for selecioando após pesquisa
    private static Veiculo carSelecionado = null;

    public App() {

        // Tela principal
        super("Cadastro de Veículos");
        setSize(712, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Bordas e cores
        Border gray = BorderFactory.createLineBorder(Color.WHITE);
        Color cinza = new Color(211, 211, 211);
        getContentPane().setBackground(cinza);

        // Painel com os campos para entrada de dados
        painelDados = new JPanel();
        painelDados.setBounds(10, 10, 672, 200);
        painelDados.setLayout(null);
        painelDados.setBorder(gray);
        painelDados.setVisible(true);
        add(painelDados);

        // TextFields e Labels

        tChassi = new JLabel("Chassi *");
        tChassi.setBounds(15, 10, 50, 30);
        painelDados.add(tChassi);

        try {
            chassi = new JFormattedTextField(new MaskFormatter("*****************"));
            chassi.setBounds(15, 40, 150, 25);
            painelDados.add(chassi);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tModelo = new JLabel("Modelo *");
        tModelo.setBounds(180, 10, 50, 30);
        painelDados.add(tModelo);

        modelo = new JTextField();
        modelo.setBounds(180, 40, 150, 25);
        painelDados.add(modelo);

        tFabricante = new JLabel("Fabricante *");
        tFabricante.setBounds(345, 10, 100, 30);
        painelDados.add(tFabricante);

        fabricante = new JTextField();
        fabricante.setBounds(345, 40, 150, 25);
        painelDados.add(fabricante);

        tMotorizacao = new JLabel("Motorização *");
        tMotorizacao.setBounds(510, 10, 100, 30);
        painelDados.add(tMotorizacao);

        motorizacao = new JComboBox<String>();
        motorizacao.addItem("");
        motorizacao.addItem("1.0 Turbo Em linha");
        motorizacao.addItem("1.0 Aspirado Em linha");
        motorizacao.addItem("1.0 Turbo V");
        motorizacao.addItem("1.0 Turbo V");
        motorizacao.addItem("1.4 Turbo Em linha");
        motorizacao.addItem("1.4 Aspirado Em linha");
        motorizacao.addItem("1.4 Turbo V");
        motorizacao.addItem("1.4 Aspirado V\"");
        motorizacao.addItem("1.6 Turbo Em linha");
        motorizacao.addItem("1.6 Aspirado Em linha");
        motorizacao.addItem("1.6 Turbo V");
        motorizacao.addItem("1.6 Aspirado V");
        motorizacao.addItem("1.8 Turbo Em linha");
        motorizacao.addItem("1.8 Aspirado Em linha");
        motorizacao.addItem("1.8 Turbo V");
        motorizacao.addItem("1.8 Aspirado V");
        motorizacao.addItem("2.0 Turbo Em linha");
        motorizacao.addItem("2.0 Aspirado Em linha");
        motorizacao.addItem("2.0 Turbo V");
        motorizacao.addItem("2.0 Aspirado V");
        motorizacao.addItem("Elétrico");
        motorizacao.addItem("Minivan 1.4 Turbo");
        motorizacao.addItem("Minivan 1.8 Aspirado");
        motorizacao.addItem("Van 2.0 Turbo");
        motorizacao.addItem("Van 2.0 Aspirado");

        motorizacao.setBounds(510, 40, 150, 25);
        painelDados.add(motorizacao);

        tKmAtual = new JLabel("KM Atual *");
        tKmAtual.setBounds(15, 70, 100, 30);
        painelDados.add(tKmAtual);

        // Aqui é a formatação do campo KM Atual, colocando . como separador e não
        // permite letras
        NumberFormat format = NumberFormat.getInstance();
        CustomNumberFormatter formatter = new CustomNumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMaximum(9999999);
        formatter.setAllowsInvalid(false);

        kmAtual = new JFormattedTextField(formatter);
        kmAtual.setBounds(15, 100, 150, 25);
        painelDados.add(kmAtual);

        tFinalPlaca = new JLabel("Final Placa *");
        tFinalPlaca.setBounds(180, 70, 100, 30);
        painelDados.add(tFinalPlaca);

        try {
            finalPlaca = new JFormattedTextField(new MaskFormatter("*"));
            finalPlaca.setBounds(180, 100, 150, 25);
            painelDados.add(finalPlaca);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tCor = new JLabel("Cor *");
        tCor.setBounds(345, 70, 100, 30);
        painelDados.add(tCor);

        cor = new JComboBox<String>();
        cor.addItem("");
        cor.addItem("Branco");
        cor.addItem("Preto");
        cor.addItem("Prata");
        cor.addItem("Cinza");
        cor.addItem("Vermelho");
        cor.addItem("Azul");
        cor.addItem("Verde");
        cor.addItem("Amarelo");
        cor.addItem("Laranja");
        cor.addItem("Marrom");
        cor.addItem("Bege");
        cor.addItem("Dourado");
        cor.addItem("Rosa");
        cor.addItem("Roxo");
        cor.setBounds(345, 100, 150, 25);
        painelDados.add(cor);

        tCombustivel = new JLabel("Combustivel *");
        tCombustivel.setBounds(510, 70, 100, 30);
        painelDados.add(tCombustivel);

        combustivel = new JComboBox<String>();
        combustivel.addItem("");
        combustivel.addItem("Gasolina");
        combustivel.addItem("Diesel");
        combustivel.addItem("Etanol");
        combustivel.addItem("Flex");
        combustivel.addItem("Elétrico");
        combustivel.addItem("Híbrido");
        combustivel.addItem("Hidrogênio");
        combustivel.addItem("Gnv");
        combustivel.setBounds(510, 100, 150, 25);
        painelDados.add(combustivel);

        tAno = new JLabel("Ano *");
        tAno.setBounds(15, 130, 100, 30);
        painelDados.add(tAno);

        // Formatação do campo Ano, apenas permite 4 números e não permite valores acima
        // de 2024 e abaixo de 1900
        NumberFormat formatacao = NumberFormat.getIntegerInstance();
        formatacao.setGroupingUsed(false);
        NumberFormatter formatacaoAno = new NumberFormatter(formatacao) {
            @Override
            public Object stringToValue(String text) throws ParseException {
                if (text == null || text.trim().isEmpty()) {
                    return null;
                }
                return super.stringToValue(text);
            }
        };
        formatacaoAno.setValueClass(Integer.class);
        formatacaoAno.setMaximum(2024);
        formatacaoAno.setAllowsInvalid(false);
        formatacaoAno.setCommitsOnValidEdit(true);

        ano = new JFormattedTextField(formatacaoAno);
        ano.setBounds(15, 160, 150, 25);
        painelDados.add(ano);

        tCidade = new JLabel("Cidade *");
        tCidade.setBounds(180, 130, 100, 30);
        painelDados.add(tCidade);

        cidade = new JTextField();
        cidade.setBounds(180, 160, 150, 25);
        painelDados.add(cidade);

        tCarroceria = new JLabel("Carroceria *");
        tCarroceria.setBounds(345, 130, 100, 30);
        painelDados.add(tCarroceria);

        carroceria = new JComboBox<String>();
        carroceria.addItem("");
        carroceria.addItem("Sedã");
        carroceria.addItem("Hatch");
        carroceria.addItem("SUV");
        carroceria.addItem("Crossver");
        carroceria.addItem("Coupé");
        carroceria.addItem("Conversível");
        carroceria.addItem("Perua");
        carroceria.addItem("Picape");
        carroceria.addItem("Minivan");
        carroceria.addItem("Roadster");
        carroceria.addItem("Liftback");
        carroceria.addItem("Fastback");
        carroceria.addItem("Ute");
        carroceria.addItem("Van");
        carroceria.addItem("Roadster");
        carroceria.addItem("Liftback");
        carroceria.addItem("Fastback");
        carroceria.addItem("Ute");
        carroceria.setBounds(345, 160, 150, 25);
        painelDados.add(carroceria);

        tValor = new JLabel("Valor *");
        tValor.setBounds(510, 130, 100, 30);
        painelDados.add(tValor);

        valor = new JTextField();
        valor.setBounds(510, 160, 150, 25);
        valor.setDocument(new MonetarioDocument());
        painelDados.add(valor);

        TDescricao = new JLabel("Descrição ");
        TDescricao.setBounds(15, 220, 100, 30);
        add(TDescricao);

        descricao = new JTextField();
        descricao.setBounds(15, 250, 665, 25);
        add(descricao);

        tOpcionais = new JLabel("Opcionais *");
        tOpcionais.setBounds(15, 280, 100, 30);
        add(tOpcionais);

        opcionais = new JTextField();
        opcionais.setBounds(15, 310, 665, 25);
        add(opcionais);
        // -----------------------------------------------------------------------------------

        // Botões do sistema
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(10, 360, 120, 30);
        btnCadastrar.setBorder(new RoundedBorder(8));
        add(btnCadastrar);

        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(150, 360, 120, 30);
        btnAlterar.setBorder(new RoundedBorder(8));
        add(btnAlterar);

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setBounds(290, 360, 120, 30);
        btnPesquisar.setBorder(new RoundedBorder(8));
        add(btnPesquisar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(430, 360, 120, 30);
        btnExcluir.setBorder(new RoundedBorder(8));
        add(btnExcluir);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(570, 360, 120, 30);
        btnLimpar.setBorder(new RoundedBorder(8));
        add(btnLimpar);

        // Função botão Cadastrar
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Veiculo veiculo = criarVeiculo();
                veiculo_DAO veiculoDAO = new veiculo_DAO();
                String varChassi = chassi.getText().trim();

                if (!varChassi.isEmpty() && veiculoDAO.chassiExiste(varChassi, carSelecionado)) {
                    JOptionPane.showMessageDialog(null, "Chassi já cadastrado!");
                    return;
                }
                veiculo_DAO dao = new veiculo_DAO();
                dao.salvarCadastro(criarVeiculo());
                limparCampos();
            }
        });

        // Botão Pesquisar
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPesquisa pesquisa = new TelaPesquisa();
                pesquisa.setVisible(true);
                btnPesquisar.setEnabled(false);
            }
        });
        setVisible(true);

        // Botão Alterar
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                veiculo_DAO veiculoDAO = new veiculo_DAO();
                String varChassi = chassi.getText().trim();

                if (!varChassi.isEmpty() && veiculoDAO.chassiExiste(varChassi, carSelecionado)) {
                    JOptionPane.showMessageDialog(null, "Chassi já cadastrado!");
                    return;
                }

                veiculoDAO.atualizarVeiculo(criarVeiculo(), carSelecionado);
            }
        });

        // Botão Limpar
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        // Botão Excluir
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                veiculo_DAO dao = new veiculo_DAO();
                dao.excluirVeiculo(carSelecionado);
                limparCampos();
            }
        });
    }

    // Essa é uma classe personalizada para formatar e validar a entrada numérica em
    // campos de texto formatados, como o campo de quilometragem
    class CustomNumberFormatter extends NumberFormatter {
        public CustomNumberFormatter(NumberFormat format) {
            super(format);
        }

        @Override
        public Object stringToValue(String text) throws java.text.ParseException {
            if (text == null || text.trim().isEmpty()) {
                return null;
            }
            return super.stringToValue(text);
        }
    }

    // Essa é uma classe personalizada para colocar bordas arredondadas nos botões.
    public static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    class MonetarioDocument extends PlainDocument {
        private static final int MAX_DIGITS = 11;

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            String plainText = getText(0, getLength()).replaceAll("\\D", "") + str.replaceAll("\\D", "");
            if (plainText.length() > MAX_DIGITS)
                return;
            super.remove(0, getLength());
            super.insertString(0, "R$ " + formatValue(plainText), attr);
        }

        @Override
        public void remove(int offset, int length) throws BadLocationException {
            String plainText = new StringBuilder(getText(0, getLength())).delete(offset, offset + length).toString()
                    .replaceAll("\\D", "");
            super.remove(0, getLength());
            super.insertString(0, "R$ " + formatValue(plainText.isEmpty() ? "0" : plainText), null);
        }

        private String formatValue(String value) {
            long num = Long.parseLong(value);
            return String.format("%,d", num).replace(',', '.');
        }
    }

    // Esse método verifica todos os campos da tela cadastro e gera o objeto Veiculo
    private static Veiculo criarVeiculo() {
        try {

            // Pega os valores dos campos
            String varChassi = chassi.getText().trim();
            String varModelo = modelo.getText().trim();
            String varFabricante = fabricante.getText().trim();
            String varAno = ano.getText().trim();
            String varKmAtualText = kmAtual.getText().trim().replaceAll("[^0-9]", "");
            String varMotorizacao = (motorizacao.getSelectedItem() != null)
                    ? motorizacao.getSelectedItem().toString().trim()
                    : "";
            String varFinalPlaca = finalPlaca.getText().trim().toUpperCase();
            String varCor = (cor.getSelectedItem() != null) ? cor.getSelectedItem().toString().trim() : "";
            String varCombustivel = (combustivel.getSelectedItem() != null)
                    ? combustivel.getSelectedItem().toString().trim()
                    : "";
            String varCidade = cidade.getText().trim();
            String varCarroceria = (carroceria.getSelectedItem() != null)
                    ? carroceria.getSelectedItem().toString().trim()
                    : "";
            String valorText = valor.getText().replaceAll("[^0-9]", "").trim();
            String varOpcionais = opcionais.getText().trim();
            String varDescricao = descricao.getText().trim();

            // Verificação se todos os campos obrigatórios estão preenchidos
            if (varChassi.isEmpty() || varModelo.isEmpty() || varFabricante.isEmpty() || ano.getText().isEmpty()
                    || kmAtual.getText().isEmpty() || varMotorizacao.isEmpty()
                    || finalPlaca.getText().isEmpty() || varCor.isEmpty() || varCombustivel.isEmpty()
                    || varCidade.isEmpty() || varCarroceria.isEmpty() || valor.getText().isEmpty()
                    || varOpcionais.isEmpty()) {

                JOptionPane.showMessageDialog(null,
                        "Um ou mais campos obrigatórios não foram preenchidos", "Aviso 101",
                        JOptionPane.WARNING_MESSAGE);
                return null;
            }

            // Conversões e verificações de dados após validar que os campos não estão
            // vazios
            int varAnoInt = Integer.parseInt(varAno);
            int varKmAtual = Integer.parseInt(varKmAtualText);
            System.out.println(valorText);
            double varValor = Double.parseDouble(valorText.isEmpty() ? "0" : valorText);
            System.out.println(varValor);
            // Verificação do comprimento do chassi, ele permite apenas valores reais para
            // numeros e letras, nao permite valores nulos
            if (varChassi.length() != 17 || !varChassi.matches("[A-Za-z0-9]{17}")) {
                JOptionPane.showMessageDialog(null,
                        "O chassi deve ter exatamente 17 caracteres alfanuméricos!");
                return null;
            }
            if (varAno.length() != 4) {
                JOptionPane.showMessageDialog(null,
                        "O ano deve ter exatamente 4 números!");
                return null;
            }

            if (varValor == 0.00) {
                JOptionPane.showMessageDialog(null,
                        "O valor não pode ser igual a 0.00");
                return null;
            } else {

                // Continua com o processamento se todas as validações foram bem-sucedidas
                Veiculo veiculo = new Veiculo(varChassi, varModelo, varFabricante, varAnoInt, varKmAtual,
                        varMotorizacao, varFinalPlaca, varCor, varCombustivel, varCidade, varCarroceria,
                        varValor, varOpcionais, varDescricao);
                return veiculo;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conversão de dados: " + ex.getMessage());
            return null;
        }
    }

    // Esse método preenche os campos da tela com base em um objeto do tipo Veículo
    public static void atualizarCampos(Veiculo car) {
        chassi.setText(car.getChassi());
        modelo.setText(car.getModelo());
        fabricante.setText(car.getFabricante());
        ano.setText(String.valueOf(car.getAno()));
        kmAtual.setText(String.valueOf(car.getKmAtual()));
        motorizacao.setSelectedItem(car.getMotorizacao());
        finalPlaca.setText(String.valueOf(car.getFinalPlaca()));
        cor.setSelectedItem(car.getCor());
        combustivel.setSelectedItem(car.getCombustivel());
        cidade.setText(car.getCidade());
        carroceria.setSelectedItem(car.getCarroceria());

        valor.setText(String.valueOf((int) car.getValor()));

        opcionais.setText(car.getOpcionais());
        descricao.setText(car.getDescricao());
        System.out.println(car.getValor());
        carSelecionado = car;
    }

    // Esse método limpa todos os campos da tela e restaura os botões
    public static void limparCampos() {
        carSelecionado = null;
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnCadastrar.setEnabled(true);
        chassi.setText("");
        modelo.setText("");
        fabricante.setText("");
        ano.setText("");
        kmAtual.setText("");
        motorizacao.setSelectedItem("");
        finalPlaca.setText("");
        cor.setSelectedItem("");
        combustivel.setSelectedItem("");
        cidade.setText("");
        carroceria.setSelectedItem("");
        valor.setText("");
        opcionais.setText("");
        descricao.setText("");
    }

    // Método Main
    public static void main(String[] args) throws Exception {

        App form = new App();
        btnExcluir.setEnabled(false);
        btnAlterar.setEnabled(false);
    }

}
