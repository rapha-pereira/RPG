package rpg;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class RPG {
    public static void main(String[] args) {
        ArrayList<Personagem> personagens = new ArrayList<>();
        ArrayList<Arma> armas = new ArrayList<>();

        int opcao;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    "======= MENU =======\n" +
                            "1 - Criar personagem\n" +
                            "2 - Adicionar habilidade a um personagem\n" +
                            "3 - Visualizar informações de um personagem\n" +
                            "4 - Consultar lista de personagens\n" +
                            "5 - Batalhar\n" +
                            "6 - Sair\n" +
                            "Escolha uma opção:"
            ));

            switch (opcao) {
                case 1:
                    criarPersonagem(personagens, armas);
                    break;
                case 2:
                    adicionarHabilidade(personagens);
                    break;
                case 3:
                    visualizarInformacoes(personagens);
                    break;
                case 4:
                    consultarLista(personagens);
                    break;
                case 5:
                    batalhar(personagens);
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Obrigado por jogar! Até mais!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (opcao != 6);
    }

    private static void criarPersonagem(ArrayList<Personagem> personagens, ArrayList<Arma> armas) {
        String nome = JOptionPane.showInputDialog("Digite o nome do personagem:");
        String descricao = JOptionPane.showInputDialog("Digite a descrição do personagem:");
        int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade do personagem:"));
    
        boolean armaSelecionadaValida = false;
        Arma armaSelecionada = null;
    
        do {
            String mensagemArmas = "Armas disponíveis:\n";
            for (int i = 0; i < armas.size(); i++) {
                Arma arma = armas.get(i);
                mensagemArmas += (i + 1) + ". " + arma.getTipo() + " - Ataque/Defesa: " + arma.getAtaqueDefesa() + ", Peso: " + arma.getPeso() + ", Posição: " + arma.getPosicaoArma() + "\n";
            }
            mensagemArmas += "0. Criar nova arma\n";
    
            int indiceArma = Integer.parseInt(JOptionPane.showInputDialog(mensagemArmas + "Digite o número correspondente à arma desejada:"));
    
            if (indiceArma == 0) {
                criarArma(armas);
            } else if (indiceArma > 0 && indiceArma <= armas.size()) {
                armaSelecionada = armas.get(indiceArma - 1);
                if (armaSelecionada.getPosicaoArma().equalsIgnoreCase("primária")) {
                    armaSelecionadaValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "A arma selecionada não é uma arma primária. Por favor, escolha uma arma primária.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida. Digite um número correspondente à arma disponível.");
            }
        } while (!armaSelecionadaValida);
    
        Personagem p = new Personagem(nome, descricao, idade, armaSelecionada);
        personagens.add(p);
    
        JOptionPane.showMessageDialog(null, "Personagem criado com sucesso!");
    }

    private static void criarArma(ArrayList<Arma> armas) {
        String tipo = JOptionPane.showInputDialog("Digite o nome da arma:");
        int ataqueDefesa = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor de ataque/defesa da arma:"));
        double peso = Double.parseDouble(JOptionPane.showInputDialog("Digite o peso da arma:"));
        
        String posicaoArma = "";
        boolean posicaoArmaValida = false;
        while (!posicaoArmaValida) {
            posicaoArma = JOptionPane.showInputDialog("Digite a posição da arma (primária/secundária):");
            if (posicaoArma.equalsIgnoreCase("primária") || posicaoArma.equalsIgnoreCase("secundária")) {
                posicaoArmaValida = true;
            } else {
                JOptionPane.showMessageDialog(null, "Posição da arma inválida. Verifique a grafia da palavra.");
            }
        }
        
        Arma a = new Arma(ataqueDefesa, peso, tipo, posicaoArma);
        armas.add(a);
    
        JOptionPane.showMessageDialog(null, "Arma criada com sucesso!");
    }  

    private static void adicionarHabilidade(ArrayList<Personagem> personagens) {
    }

    private static void visualizarInformacoes(ArrayList<Personagem> personagens) {
    }

    private static void consultarLista(ArrayList<Personagem> personagens) {
        //dá pra usar alguma coisa do 'batalhar'
    }

    //verificar quais informações mostrar na escolha do personagem
    private static void batalhar(ArrayList<Personagem> personagens) {
        boolean personagemSelecionadoValido = false;
        Personagem personagemSelecionado = null;
        do {
            //Mensagem listando os personagens
            String mensagemPersonagens = "Personagens disponíveis:\n";
            for (int i = 0; i < personagens.size(); i++) {
                Personagem personagem = personagens.get(i);
                mensagemPersonagens += personagem.getNome() + " - Nível: " + personagem.getNivel() + ", XP: " + personagem.getXp() + "\n";
            }
            mensagemPersonagens += "0. Criar novo personagem\n";
            
            //JOptionPane com lista de personagens e escolha
            String nomePersonagemSelecionado = JOptionPane.showInputDialog(mensagemPersonagens + "Escolha o nome do seu personagem:");
    
            //Validação do personagem escolhido
            for (Personagem personagem : personagens) {
            if (personagem.getNome().equalsIgnoreCase(nomePersonagemSelecionado)) {
                personagemSelecionado = personagem;
                personagemSelecionadoValido = true;
                break;
            }
            }

            if (!personagemSelecionadoValido) {
                JOptionPane.showMessageDialog(null, "Personagem inválido. Digite um nome correspondente a um personagem disponível.");
            }
        } while (!personagemSelecionadoValido);
        
        JOptionPane.showMessageDialog(
            null, "Batalha iniciada utilizando o personagem " + personagemSelecionado.getNome() + "!");

        Random random = new Random();
        int xp = random.nextInt(1000) + 1; //+1 para pular o 0
        personagemSelecionado.setXp(xp);

        JOptionPane.showMessageDialog(
            null, "XP ganho na batalha: " + personagemSelecionado.getXp());
    }
}