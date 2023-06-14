import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
                    inserirHabilidade(personagens, armas);
                    break;
                case 3:
                    visualizarInformacoes(personagens);
                    break;
                case 4:
                    consultarLista(personagens);
                    break;
                case 5:
                    batalhar(personagens, armas);
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

    private static String createMensagemArmas(ArrayList<Arma> armas) {
        String mensagemArmas = "Armas disponíveis:\n0. Criar nova arma\n";

        for (int i = 0; i < armas.size(); i++) {
            Arma arma = armas.get(i);
            mensagemArmas += (i + 1) + ". " + arma.getTipo() + " - Ataque/Defesa: " + arma.getAtaqueDefesa() + " | Peso: " + arma.getPeso() + " | Posição: " + arma.getPosicaoArma() + "\n";
        }
        return mensagemArmas;
    }

    private static void criarPersonagem(ArrayList<Personagem> personagens, ArrayList<Arma> armas) {
        boolean armaSelecionadaValida = false;
        Arma armaSelecionada = null;
        String nome = JOptionPane.showInputDialog("Digite o nome do personagem:");
        String descricao = JOptionPane.showInputDialog("Digite a descrição do personagem:");
        int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade do personagem:"));
        
        //Escolher, criar e definir a arma do personagem
        boolean armaSelecionadaValida = false;
        Arma armaSelecionada = null;
    
        do {
            String mensagemArmas = createMensagemArmas(armas);

            int indiceArma = Integer.parseInt(JOptionPane.showInputDialog(mensagemArmas + "Digite o número correspondente à arma desejada:"));
            if (indiceArma == 0) {
                criarArma(armas);
            } else if (indiceArma > 0 && indiceArma <= armas.size()) { //escolhendo arma entre as opções
                armaSelecionada = armas.get(indiceArma - 1);
                if (armaSelecionada.getPosicaoArma().equalsIgnoreCase("primária")) { //validando se é primária
                    armaSelecionadaValida = true;
                } else {
                    JOptionPane.showMessageDialog(
                        null, "A arma selecionada não é uma arma primária. Por favor, escolha uma arma primária.");
                }
            } else {
                JOptionPane.showMessageDialog(
                    null, "Opção inválida. Digite um número correspondente à arma disponível.");
            }
        } while (!armaSelecionadaValida);

        //Preencher os atributos
        int pontosDisponiveis, pontosIniciais = 10;
        int forca, vitalidade, destreza, poder;
        int atributos = 4;

        JOptionPane.showMessageDialog(
            null, "Você tem " + pontosIniciais + " pontos iniciais para distribuir entre seus atributos: Força, Vitalidade, Destreza e Poder.");
        
        forca = preencherAtributoInicial("Força", pontosIniciais, atributos);
        pontosDisponiveis = pontosIniciais - forca;
        atributos -= 1;
                
        vitalidade = preencherAtributoInicial("Vitalidade", pontosDisponiveis, atributos);
        pontosDisponiveis -= vitalidade;
        atributos -= 1;
        
        destreza = preencherAtributoInicial("Destreza", pontosDisponiveis, atributos);
        pontosDisponiveis -= destreza;
        atributos -= 1;
                
        poder = preencherAtributoInicial("Poder", pontosDisponiveis, atributos);
        pontosDisponiveis -= poder;
        atributos -= 1;
        
        Personagem p = new Personagem(nome, descricao, idade, armaSelecionada, forca, vitalidade, destreza, poder, pontosDisponiveis);
        personagens.add(p);
    
        JOptionPane.showMessageDialog(
            null, "Personagem criado com sucesso!");
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

    private static int preencherAtributoInicial(String atributo, int pontosDisponiveis, int atributos) {
        int pontosMin = 1;
        int pontosMaxDisponiveis = pontosDisponiveis-atributos+1;
        int atributosSeguintes = atributos-1;
        while (true) {
            int valorAtributo = Integer.parseInt(JOptionPane.showInputDialog(
                "Pontuação mínima por atributo: " + pontosMin + "\n" +
                "Atributos a seguir: " + atributosSeguintes + "\n" +
                "Pontos disponíveis: " + pontosDisponiveis + "\n" +
                "Pontuação disponível para este atributo: " + pontosMaxDisponiveis + "\n" +
                "Digite os pontos que deseja atribuir para " + atributo));
            if (valorAtributo > 0 && valorAtributo <= pontosMaxDisponiveis) {
                /*if (atributo.equalsIgnoreCase("poder") || pontosDisponiveis >= atributos-1) {*/
                    return valorAtributo;
                /*} else {
                    JOptionPane.showMessageDialog(
                        null, "Existem atributos que precisarão de pelo menos 1 ponto. Revise a atribuição de pontos!");
                }*/
            } else {
                if (valorAtributo <= 0) {
                    JOptionPane.showMessageDialog(
                        null, "O valor do atributo deve ser no mínimo " + pontosMin);
                } else if ((atributosSeguintes == 2 && pontosDisponiveis <= 3) || (atributosSeguintes == 1 && pontosDisponiveis <= 2)) {
                    JOptionPane.showMessageDialog(
                        null, "O valor do atributo deve ser " + pontosMin + ", pois existem mais atributos que precisarão de pelo menos 1 ponto cada.");
                } else {
                    JOptionPane.showMessageDialog(
                        null, "O valor do atributo deve ser entre 1 e " + pontosMaxDisponiveis + ", pois existem mais atributos que precisarão de pelo menos 1 ponto cada.");
                }
            }
        }
    }

    private static void inserirHabilidade(ArrayList<Personagem> personagens,ArrayList<Arma> armas) {
        Habilidade detectarArmadilhas = new Habilidade("Detectar Armadilhas", "Permite ao personagem detectar armadilhas ocultas em seu ambiente.", "Poder", 3);
        Habilidade furtividade = new Habilidade("Furtividade", "Permite ao personagem se mover silenciosamente e passar despercebido pelos inimigos.", "Destreza", 2);
        Habilidade ataqueDuplo = new Habilidade("Ataque Duplo", "Permite ao personagem realizar dois ataques consecutivos em um único turno de combate.", "Força", 4);
        Habilidade curaRapida = new Habilidade("Cura Rápida", "Permite ao personagem recuperar pontos de vida de forma mais eficiente durante a cura.", "Vitalidade", 3);
        Habilidade magiaElemental = new Habilidade("Magia Elemental", "Permite ao personagem lançar magias de elementos como fogo, água, ar ou terra.", "Poder", 5);
        
        boolean personagemSelecionadoValido = false;
        Personagem personagemSelecionado = null;
        boolean habilidadeValida = false;
        Habilidade habilidadeSelecionada = null;

        do {
            //Montando mensagem listando os personagens
            String mensagemPersonagens = "Personagens disponíveis:\n";
            for (int i = 0; i < personagens.size(); i++) {
                Personagem personagem = personagens.get(i);
                mensagemPersonagens += personagem.getNome() + " - Nível: " + personagem.getNivel() + " - Poder: " + personagem.getPoder() + "\n";
            }
            mensagemPersonagens += "0. Criar novo personagem\n";
            
            //Exibindo lista de personagens para escolha
            String nomePersonagemSelecionado = JOptionPane.showInputDialog(mensagemPersonagens + "Escolha o nome do seu personagem:");
    
            //Validação do personagem escolhido
            if (nomePersonagemSelecionado.equals("0")) { // Opção criar personagem
            criarPersonagem(personagens, armas);
            } else {
                for (Personagem personagem : personagens) {
                    if (personagem.getNome().equalsIgnoreCase(nomePersonagemSelecionado)) {
                        personagemSelecionado = personagem;
                        personagemSelecionadoValido = true;
                        break;
                    }
                }
            }

            if (!personagemSelecionadoValido && !nomePersonagemSelecionado.equals("0")) {
                JOptionPane.showMessageDialog(null, "Personagem inválido. Digite um nome correspondente a um personagem disponível.");
            }
        } while (!personagemSelecionadoValido);

        do {
            String mensagemHabilidades = personagemSelecionado.getNome() + " - Poder: " + personagemSelecionado.getPoder() + "\n";
            mensagemHabilidades += "Habilidades disponíveis:\n";
            mensagemHabilidades += "1. " + detectarArmadilhas.getNome() + " - Poder mínimo: " + detectarArmadilhas.getPoderMinimo() + " - " + detectarArmadilhas.getDescricao() + "\n";
            mensagemHabilidades += "2. " + furtividade.getNome() + " - Poder mínimo: " + furtividade.getPoderMinimo() + " - " + furtividade.getDescricao() + "\n";
            mensagemHabilidades += "3. " + ataqueDuplo.getNome() + " - Poder mínimo: " + ataqueDuplo.getPoderMinimo() + " - " + ataqueDuplo.getDescricao() + "\n";
            mensagemHabilidades += "4. " + curaRapida.getNome() + " - Poder mínimo: " + curaRapida.getPoderMinimo() + " - " + curaRapida.getDescricao() + "\n";
            mensagemHabilidades += "5. " + magiaElemental.getNome() + " - Poder mínimo: " + magiaElemental.getPoderMinimo() + " - " + magiaElemental.getDescricao() + "\n";
        
        
            // Exibe lista e solicita ao usuário que escolha uma habilidade
            int habilidadeEscolhida = Integer.parseInt(JOptionPane.showInputDialog(mensagemHabilidades + "Escolha uma habilidade:"));
            
            switch (habilidadeEscolhida) {
                case 1:
                    habilidadeSelecionada = detectarArmadilhas;
                    break;
                case 2:
                    habilidadeSelecionada = furtividade;
                    break;
                case 3:
                    habilidadeSelecionada = ataqueDuplo;
                    break;
                case 4:
                    habilidadeSelecionada = curaRapida;
                    break;
                case 5:
                    habilidadeSelecionada = magiaElemental;
                    break;
            }

            if (habilidadeSelecionada != null) {
                if (personagemSelecionado.getPoder() >= habilidadeSelecionada.getPoderMinimo()) {
                    personagemSelecionado.inserirHabilidade(habilidadeSelecionada);
                    habilidadeValida = true;
                    JOptionPane.showMessageDialog(
                        null, "Habilidade adicionada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(
                        null, "O personagem não tem poder suficiente para escolher essa habilidade.");
                }
            }
        }while (!habilidadeValida);
    }

    private static void visualizarInformacoes(ArrayList<Personagem> personagens) {
    }

    private static void consultarLista(ArrayList<Personagem> personagens) {
        //dá pra usar alguma coisa do 'batalhar'
    }

    //verificar quais informações mostrar na escolha do personagem
    private static void batalhar(ArrayList<Personagem> personagens,ArrayList<Arma> armas) {
        boolean personagemSelecionadoValido = false;
        Personagem personagemSelecionado = null;
        do {
            //Montando mensagem listando os personagens
            String mensagemPersonagens = "Personagens disponíveis:\n";
            for (int i = 0; i < personagens.size(); i++) {
                Personagem personagem = personagens.get(i);
                int xpParaProximoNivel = 1000 * personagem.getNivel();
                mensagemPersonagens += personagem.getNome() + " - Nível: " + personagem.getNivel() + " - Status XP: " + personagem.getXpExcedente() + "/" + xpParaProximoNivel + " - Pontos de Atributo Disponíveis:" + personagem.getPontosDisponiveis() + "\n";
                xpParaProximoNivel = 0;
            }
            mensagemPersonagens += "0. Criar novo personagem\n";
            
            //Exibindo lista de personagens para escolha
            String nomePersonagemSelecionado = JOptionPane.showInputDialog(mensagemPersonagens + "Escolha o nome do seu personagem:");
    
            //Validação do personagem escolhido
            if (nomePersonagemSelecionado.equals("0")) { // Opção criar personagem
            criarPersonagem(personagens, armas);
            } else {
                for (Personagem personagem : personagens) {
                    if (personagem.getNome().equalsIgnoreCase(nomePersonagemSelecionado)) {
                        personagemSelecionado = personagem;
                        personagemSelecionadoValido = true;
                        break;
                    }
                }
            }

            if (!personagemSelecionadoValido && !nomePersonagemSelecionado.equals("0")) {
                JOptionPane.showMessageDialog(null, "Personagem inválido. Digite um nome correspondente a um personagem disponível.");
            }
        } while (!personagemSelecionadoValido);
        
        JOptionPane.showMessageDialog(
            null, "Batalha iniciada utilizando o personagem " + personagemSelecionado.getNome() + "!");

        Random random = new Random();
        int xpGanho = random.nextInt(1000) + 1; // +1 para pular o 0
        personagemSelecionado.setXp(personagemSelecionado.getXp() + xpGanho);
        personagemSelecionado.setXpExcedente(personagemSelecionado.getXpExcedente() + xpGanho);
        JOptionPane.showMessageDialog(
            null, "XP ganho na batalha: " + xpGanho);

        // Verificar se o personagem subiu de nível
        int xpParaProximoNivel = 1000 * personagemSelecionado.getNivel();                                                   // Define XP necessário para o próximo nível
        if (personagemSelecionado.getXpExcedente() >= xpParaProximoNivel) {                                                 // Verifica o personagem passou de nível
            personagemSelecionado.setNivel(personagemSelecionado.getNivel() + 1);                                           // Sobe o nível do personagem 
            int xpExcedente = personagemSelecionado.getXpExcedente() - xpParaProximoNivel;                                  // Calcula o saldo de XP após subir de nível     
            personagemSelecionado.setXpExcedente(xpExcedente);                                                              // Atualiza a evolução do XP no nível atual
            int pontosGanhos = 1;
            personagemSelecionado.setPontosDisponiveis(personagemSelecionado.getPontosDisponiveis() + pontosGanhos);        // Acrescenta ponto ganho aos pontos disponíveis para atirbutos
            JOptionPane.showMessageDialog(
                null, "Parabéns! O personagem " + personagemSelecionado.getNome() + " subiu para o nível " + personagemSelecionado.getNivel() + " e ganhou " + pontosGanhos + " ponto de atributo.");
        }
    }
}