import java.util.ArrayList;
import java.util.Arrays;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Random;

public class RPG_alt {
    static boolean createNextChampionBool = true;
    static Integer createNextChampionIter = 1;
    static Integer menuInput;

    static List<Personagem> championsList = new ArrayList<>();
    static List<Arma> primaryWeaponsList = new ArrayList<>();
    static List<Arma> secondaryWeaponsList = new ArrayList<>();
    static List<Habilidade> skillsList = new ArrayList<>();
    
    public static Integer mainMenu(){
        return Integer.parseInt(
            JOptionPane.showInputDialog(
                "======= MENU =======\n" +
                    "1 - Criar personagem\n" +
                    "2 - Adicionar uma arma secundária a um personagem\n" +
                    "3 - Adicionar habilidade a um personagem\n" +
                    "4 - Visualizar informações de um personagem\n" +
                    "5 - Consultar lista de personagens\n" +
                    "6 - Batalhar\n" +
                    "7 - Adicionar pontos a um atributo\n" +
                    "8 - Alterar inventário de armas\n" +
                    "9 - Sair\n" +
                    "Escolha uma opção:"
            )
        );
    }
    public static void main (String[] args){
        JOptionPane.showMessageDialog(
            null,
            "Olá, bem-vindo ao D&D. Seu novo RPG, escrito em Java.\n"
        );
        
        // Running helpers
        createWeaponsPreDefinedKit();
        createSkillsPreDefinedKit();
        menuInput = mainMenu();

        while (menuInput != 9){
            switch (menuInput) {
                case 1:
                    createChampion();
                    menuInput = mainMenu();
                    break;
                case 2:
                    addSecondaryWeapon();
                    menuInput = mainMenu();
                    break;
                case 3:
                    addSkillToChampion();
                    menuInput = mainMenu();
                    break;
                case 4:
                    showChampionInfo();
                    menuInput = mainMenu();
                    break;
                case 5:
                    showChampionsList();
                    menuInput = mainMenu();
                    break;
                case 6:
                    battle();
                    menuInput = mainMenu();
                    break;
                case 7:
                    addAttributesPointsToChampion();
                    menuInput = mainMenu();
                    break;
                case 8:
                    createInventaryMenu();
                    menuInput = mainMenu();
                    break; 
            }
        }
    }

    // Helpers
    public static void createWeaponsPreDefinedKit(){
        Arma machado = new Arma(25, 15, "Machado", "primária");
        Arma espada = new Arma(15, 5, "Espada", "primária");
        Arma escopeta = new Arma(30, 20, "Escopeta", "primária");
        primaryWeaponsList.add(machado);
        primaryWeaponsList.add(espada);
        primaryWeaponsList.add(escopeta);
    }

    public static void createSkillsPreDefinedKit(){
        Habilidade detectarArmadilhas = new Habilidade("Detectar Armadilhas", "Permite ao personagem detectar armadilhas ocultas em seu ambiente.", "Poder", 1);
        Habilidade furtividade = new Habilidade("Furtividade", "Permite ao personagem se mover silenciosamente e passar despercebido pelos inimigos.", "Destreza", 3);
        Habilidade ataqueDuplo = new Habilidade("Ataque Duplo", "Permite ao personagem realizar dois ataques consecutivos em um único turno de combate.", "Força", 7);
        Habilidade curaRapida = new Habilidade("Cura Rápida", "Permite ao personagem recuperar pontos de vida de forma mais eficiente durante a cura.", "Vitalidade", 5);
        Habilidade magiaElemental = new Habilidade("Magia Elemental", "Permite ao personagem lançar magias de elementos como fogo, água, ar ou terra.", "Poder", 10);
        skillsList.addAll(Arrays.asList(detectarArmadilhas, furtividade, ataqueDuplo, curaRapida, magiaElemental));
    }

    public static void createUserInputedWeapon(String weaponType){
        Integer attackDefense = Integer.parseInt(JOptionPane.showInputDialog("Qual será o valor de ataque e defesa de sua arma?"));
        Integer weigth = Integer.parseInt(JOptionPane.showInputDialog("Qual será o peso da sua arma?"));
        String name = JOptionPane.showInputDialog("Qual será o nome da sua arma?");

       Arma userWeapon = new Arma(attackDefense, weigth, name, weaponType);
        if (weaponType.equalsIgnoreCase("primária")){
            primaryWeaponsList.add(userWeapon);
        }
        else{
            secondaryWeaponsList.add(userWeapon);
        }
    }

    public static void createUserInputedSkill(){
        String name = JOptionPane.showInputDialog("Qual será o nome da sua habilidade?");
        Integer power = Integer.parseInt(JOptionPane.showInputDialog("Qual será o valor de poder mínimo de sua habilidade?"));
        String type = JOptionPane.showInputDialog("Qual será o tipo de poder de sua habilidade?\n (Força, Vitalidade, Destreza ou Poder)");
        String description = JOptionPane.showInputDialog("O que essa habilidade faz?");

        Habilidade userSkill = new Habilidade(name, description, type, power);
        skillsList.add(userSkill);
    }

    public static String createPrimaryWeaponsMenu(){
        String weaponsMessage = "Armas disponíveis:\n0. Criar nova arma\n";
        for (int i = 0; i < primaryWeaponsList.size(); i++) {
            Arma arma = primaryWeaponsList.get(i);
            weaponsMessage += (i + 1) + ". " + arma.getTipo() + " - Ataque/Defesa: " + arma.getAtaqueDefesa() + " | Peso: " + arma.getPeso() + " | Posição: " + arma.getPosicaoArma() + "\n";
        }

        return weaponsMessage;
    }

    public static String createSecondaryWeaponsMenu(){
        String weaponsMessage = "Armas secundárias disponíveis:\n0. Criar nova arma\n";
        for (int i = 0; i < secondaryWeaponsList.size(); i++) {
            Arma arma = secondaryWeaponsList.get(i);
            weaponsMessage += (i + 1) + ". " + arma.getTipo() + " - Ataque/Defesa: " + arma.getAtaqueDefesa() + " | Peso: " + arma.getPeso() + " | Posição: " + arma.getPosicaoArma() + "\n";
        }

        return weaponsMessage;
    }

    public static String createSkillMenu(){
        String skillMessage = "Habilidades disponíveis:\n0. Criar nova habilidade\n";
        for (int i = 0; i < skillsList.size(); i++) {
            Habilidade skill = skillsList.get(i);
            skillMessage += (i + 1) + ". " + skill.getNome() + " - Poder mínimo: " + skill.getPoderMinimo() + " | Descrição: " + skill.getDescricao() + " | Tipo: " + skill.getTipoAtributo() + "\n";
        }

        return skillMessage;
    }

    public static String createChampionsMenu(){
        String championsMessage = "Personagens disponíveis:\n0. Criar novo personagem\n";
        for (int i = 0; i < championsList.size(); i++) {
            Personagem champion = championsList.get(i);
            Integer xpToNextLevel = 1000 * champion.getNivel();

            String mainWeapon = "";
            String secondaryWeapon = "";

            if (champion.getArmaPrimaria() == null){mainWeapon = "Não há";}
            else {mainWeapon = champion.getArmaPrimaria().getTipo();}
            if (champion.getArmaSecundaria() == null){secondaryWeapon = "Não há";}
            else {secondaryWeapon = champion.getArmaSecundaria().getTipo();}

            championsMessage += "Personagem " + (i + 1) + ". " 
                + "Nome: " + champion.getNome() 
                + " - Nível: " + champion.getNivel() 
                + " - Status XP: " + champion.getXpExcedente() + "/" + xpToNextLevel 
                + " - Pontos de Atributo Disponíveis: " + champion.getPontosDisponiveis() + "\n"
                + " - Armas do personagem: " + mainWeapon + " - " + secondaryWeapon;
            xpToNextLevel = 0;
        }

        return championsMessage;
    }

    public static List<Integer> createInitialAttributesMenu(){
        List<Integer> returnList = new ArrayList<>();

        Integer initialPoints = 10;
        Integer remainingPoints = 10;
        Integer strength, vitality, dexterity, power;
        Integer attributes = 4;
     
        JOptionPane.showMessageDialog(
            null, 
            "Você tem " + initialPoints + 
            " pontos iniciais para distribuir entre seus atributos: Força, Vitalidade, Destreza e Poder."
        );

        strength = setAttributes("Força", attributes, initialPoints);
        remainingPoints = initialPoints - strength;
        attributes -= 1;
                
        vitality = setAttributes("Vitalidade", attributes, remainingPoints);
        remainingPoints -= vitality;
        attributes -= 1;
        
        dexterity = setAttributes("Destreza", attributes, remainingPoints);
        remainingPoints -= dexterity;
        attributes -= 1;
                
        power = setAttributes("Poder", attributes, remainingPoints);
        remainingPoints -= power;
        attributes -= 1;

        returnList.addAll(Arrays.asList(dexterity, vitality, power, strength, remainingPoints));
        return returnList;
    }

    private static Integer setAttributes(
        String attribute,
        Integer attributesCount,
        Integer points
    ) {
        Integer minimumPoints = 1;
        Integer maximumPoints = points - attributesCount + 1;
        Integer sequentialAttribute = attributesCount - 1;

        while (true) {
            Integer attributeUserValue = Integer.parseInt(
                JOptionPane.showInputDialog(
                    "Pontuação mínima por atributo: " + minimumPoints + "\n" +
                    "Atributos a seguir: " + sequentialAttribute + "\n" +
                    "Pontos disponíveis: " + points + "\n" +
                    "Pontuação disponível para este atributo: " + maximumPoints + "\n" +
                    "Digite os pontos que deseja atribuir para " + attribute
                )
            );
            if (attributeUserValue > 0 && attributeUserValue <= maximumPoints) {
                return attributeUserValue;
            }
            else {
                if (attributeUserValue <= 0) {
                    JOptionPane.showMessageDialog(
                        null, "O valor do atributo deve ser no mínimo " + minimumPoints
                    );
                } 
                else if ((sequentialAttribute == 2 && points <= 3) || (sequentialAttribute == 1 && points <= 2)) {
                    JOptionPane.showMessageDialog(
                        null, "O valor do atributo deve ser " + minimumPoints + ", pois existem mais atributos que precisarão de pelo menos 1 ponto cada."
                    );
                } 
                else {
                    JOptionPane.showMessageDialog(
                        null, "O valor do atributo deve ser entre 1 e " + maximumPoints + ", pois existem mais atributos que precisarão de pelo menos 1 ponto cada."
                    );
                }
            }
        }
    }

    // Menu functions
    public static void createChampion(){
        String name = JOptionPane.showInputDialog("Digite o nome do personagem:");
        String description = JOptionPane.showInputDialog("Digite a descrição do personagem:");
        Integer age = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade do personagem:"));

        Integer weaponMenuUserOption = 1;
        weaponMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createPrimaryWeaponsMenu() + "Digite o número correspondente à arma desejada:"));

        while(weaponMenuUserOption == 0){
            createUserInputedWeapon("primária");
            weaponMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createPrimaryWeaponsMenu() + "Digite o número correspondente à arma desejada:"));
        }
    
        List<Integer> attributesList = createInitialAttributesMenu();

        Personagem championObj = new Personagem(
            name, 
            description, 
            age,
            attributesList.get(0),
            attributesList.get(1),
            attributesList.get(2),
            attributesList.get(3),
            attributesList.get(4),
            primaryWeaponsList.get(weaponMenuUserOption - 1)
        );
        championsList.add(championObj);

        JOptionPane.showMessageDialog(null, "Personagem criado com sucesso.");
    }

    public static void addSecondaryWeapon(){
        Integer weaponMenuUserOption = 1;
        String returnMessage = "======= Selecione o personagem que irá ter a arma secundária =======\n0. Retornar para o menu";

        for (int i = 0; i < championsList.size(); i++) {
            Personagem champion = championsList.get(i);
            returnMessage += "\nPersonagem " + (i + 1) + ": \n";
            returnMessage += "  Nome: " + champion.getNome();
        }

        Integer userInput = Integer.parseInt(JOptionPane.showInputDialog(null, returnMessage));
        if (userInput != 0){
            weaponMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createSecondaryWeaponsMenu() + "Digite o número correspondente à arma desejada:"));
            while(weaponMenuUserOption == 0){
                createUserInputedWeapon("secundária");
                weaponMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createSecondaryWeaponsMenu() + "Digite o número correspondente à arma desejada:"));
            }

            Personagem champion = championsList.get(userInput - 1);
            champion.definirArmaPersonagem(secondaryWeaponsList.get(weaponMenuUserOption - 1));

            JOptionPane.showMessageDialog(null, "Arma secundária atribuida com sucesso ao personagem " + champion.getNome());
        }
    }

    public static void showChampionsList(){
        String returnMessage = "======= Lista de personagens atual =======";

        for (int i = 0; i < championsList.size(); i++) {
            Personagem champion = championsList.get(i);
            Integer xpParaProximoNivel = 1000 * champion.getNivel();
            String mainWeapon = "";
            String secondaryWeapon = "";
            String skills = "";

            if (champion.getArmaPrimaria() == null){mainWeapon = "Não há";}
            else {mainWeapon = champion.getArmaPrimaria().getTipo();}
            if (champion.getArmaSecundaria() == null){secondaryWeapon = "Não há";}
            else {secondaryWeapon = champion.getArmaSecundaria().getTipo();}

            if (champion.getHabilidades() == null){skills = "Não há";}
            else {
                for (Habilidade skill : champion.getHabilidades()) {
                    skills += "\n       Habilidade: " + skill.getNome() + "\n";
                    skills += "       Tipo: " + skill.getTipoAtributo() + "\n";
                    skills += "       Poder mínimo: " + skill.getPoderMinimo() + "\n";
                    skills += "       Descrição: " + skill.getDescricao()+ "\n";
                }
            }
            
            returnMessage += "\nPersonagem " + (i + 1) + ": \n";
            returnMessage += "  Nome: " + champion.getNome() + "  Nível: " + champion.getNivel() + " - XP: " + champion.getXpExcedente() + "/" + xpParaProximoNivel + "\n";
            returnMessage += "  Armas atuais: (Primária: " + mainWeapon + " / Secundária: " + secondaryWeapon + ")\n";
            returnMessage += "  Habilidades atuais: " + skills;
        }

        JOptionPane.showMessageDialog(null, returnMessage);
    }

    public static void showChampionInfo(){
        String championReturnMessage = "======= Selecione o personagem =======\n0. Retornar para o menu";

        for (int i = 0; i < championsList.size(); i++) {
            Personagem champion = championsList.get(i);
            championReturnMessage += "\nPersonagem " + (i + 1) + ": \n";
            championReturnMessage += "  Nome: " + champion.getNome();
        }

        Integer userChampionInput = Integer.parseInt(JOptionPane.showInputDialog(null, championReturnMessage));
        if (userChampionInput != 0){
            Personagem champion = championsList.get(userChampionInput - 1);
            Integer xpParaProximoNivel = 1000 * champion.getNivel();
            String returnMessage = "";
            String mainWeapon = "";
            String secondaryWeapon = "";
            String skills = "";

            if (champion.getArmaPrimaria() == null){mainWeapon = "Não há";}
            else {mainWeapon = champion.getArmaPrimaria().getTipo();}
            if (champion.getArmaSecundaria() == null){secondaryWeapon = "Não há";}
            else {secondaryWeapon = champion.getArmaSecundaria().getTipo();}

            if (champion.getHabilidades() == null){skills = "Não há";}
            else {
                for (Habilidade skill : champion.getHabilidades()) {
                    skills += "\n       Habilidade: " + skill.getNome() + "\n";
                    skills += "       Tipo: " + skill.getTipoAtributo() + "\n";
                    skills += "       Poder mínimo: " + skill.getPoderMinimo() + "\n";
                    skills += "       Descrição: " + skill.getDescricao()+ "\n";
                }
            }
            returnMessage += "Nome: " + champion.getNome() + "\n";
            returnMessage += "  Armas atuais: (Primária: " + mainWeapon + " / Secundária: " + secondaryWeapon + ")\n";
            returnMessage += "  Descrição: " + champion.getDescricao() + "\n";
            returnMessage += "  Idade: " + champion.getIdade() + "\n";
            returnMessage += "  Nível: " + champion.getNivel() + " - XP: " + champion.getXpExcedente() + "/" + xpParaProximoNivel + "\n";
            returnMessage += "  Poder: " + champion.getPoder() + "\n";
            returnMessage += "  Força: " + champion.getForca() + "\n";
            returnMessage += "  Destreza: " + champion.getDestreza() + "\n";
            returnMessage += "  Vitalidade: " + champion.getVitalidade() + "\n";
            returnMessage += "  Habilidades atuais: " + skills;
    
            JOptionPane.showMessageDialog(null, returnMessage);
        }
    }

    public static void addSkillToChampion(){
        Integer skillMenuUserOption = 1;
        String championReturnMessage = "======= Selecione o personagem que irá ter a habilidade =======\n0. Retornar para o menu";

        for (int i = 0; i < championsList.size(); i++) {
            Personagem champion = championsList.get(i);
            championReturnMessage += "\nPersonagem " + (i + 1) + ": \n";
            championReturnMessage += "  Nome: " + champion.getNome();
        }

        Integer userChampionInput = Integer.parseInt(JOptionPane.showInputDialog(null, championReturnMessage));

        if (userChampionInput != 0){
            Personagem champion = championsList.get(userChampionInput - 1);

            skillMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createSkillMenu() + "Digite o número correspondente à habilidade desejada:"));
            while(skillMenuUserOption == 0){
                createUserInputedSkill();
                skillMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createSkillMenu() + "Digite o número correspondente à habilidade desejada:"));
            }
            Habilidade skill = skillsList.get(skillMenuUserOption - 1);
            if (champion.getPoder() >= skill.getPoderMinimo()) {
                champion.inserirHabilidade(skill);
                JOptionPane.showMessageDialog(
                    null, "Habilidade de " + skill.getTipoAtributo() + " atribuida com sucesso ao personagem " + champion.getNome());
            } else {
                JOptionPane.showMessageDialog(
                    null, "O personagem não tem poder suficiente para escolher essa habilidade.");
            }
        }
    }
    
    private static void battle(){
        Integer championMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createChampionsMenu()));
        if (championMenuUserOption == 0){
            while (championMenuUserOption == 0){
                createChampion();
                championMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createChampionsMenu()));
            }
        }

        Personagem champion = championsList.get(championMenuUserOption - 1);
        JOptionPane.showMessageDialog(null, "Batalha iniciada utilizando o personagem " + champion.getNome() + "!");

        Random random = new Random();
        int xpGanho = random.nextInt(1000) + 1;

        champion.setXp(champion.getXp() + xpGanho);
        champion.setXpExcedente(champion.getXpExcedente() + xpGanho);
        JOptionPane.showMessageDialog(null, "XP ganho na batalha: " + xpGanho);

        Integer xpParaProximoNivel = 1000 * champion.getNivel();
        if (champion.getXpExcedente() >= xpParaProximoNivel) {
            champion.setNivel(champion.getNivel() + 1);

            int xpExcedente = champion.getXpExcedente() - xpParaProximoNivel;
            champion.setXpExcedente(xpExcedente);

            int pontosGanhos = 1;
            champion.setPontosDisponiveis(champion.getPontosDisponiveis() + pontosGanhos);

            JOptionPane.showMessageDialog(
                null, 
                "Parabéns! O personagem " + champion.getNome() 
                + " subiu para o nível " + champion.getNivel() 
                + " e ganhou " + pontosGanhos + " ponto de atributo."
            );
        }
    }

    private static void addAttributesPointsToChampion(){

        String returnMessage = "======= Selecione o personagem que voce deseja atribuir os pontos =======\n0. Retornar para o menu";
        Integer userRequestedPoints = null;

        for (int i = 0; i < championsList.size(); i++) {
            Personagem champion = championsList.get(i);
            returnMessage += "\nPersonagem " + (i + 1) + ": \n";
            returnMessage += "  Nome: " + champion.getNome();
        }

        Integer userInput = Integer.parseInt(JOptionPane.showInputDialog(null, returnMessage));
        if (userInput != 0){
            Personagem champion = championsList.get(userInput - 1);
            if (champion.getPontosDisponiveis() <= 0){
                JOptionPane.showMessageDialog(null, "Você não tem pontos para atribuir a nenhum atributo. Batalhe.");
            }
            else{
                Integer userOption = Integer.parseInt(
                    JOptionPane.showInputDialog(
                        null, 
                        "Selecione o atributo que você deseja acrescentar os pontos\n" 
                        + "1. Força\n"
                        + "2. Vitalidade\n"
                        + "3. Destreza\n"
                        + "4. Poder\n"
                    )
                );
                if (userOption == 1){
                    userRequestedPoints = Integer.parseInt(
                        JOptionPane.showInputDialog(
                            null, 
                            "Você tem " + champion.getPontosDisponiveis()
                            + " pontos disponiveis para colocar no atributo força\n"
                            + "Digite abaixo a qntde. de pontos que você deseja atribuir a força:"
                        )
                    );
                    while (userRequestedPoints > champion.getPontosDisponiveis()){
                        JOptionPane.showMessageDialog(
                            null, 
                            "Você digitou uma quantidade de pontos acima da que você tem disponível."
                        );
                        userRequestedPoints = Integer.parseInt(
                            JOptionPane.showInputDialog(
                                null, 
                                "Você tem " + champion.getPontosDisponiveis()
                                + " pontos disponiveis para colocar no atributo força\n"
                                + "Digite abaixo a qntde. de pontos que você deseja atribuir a força:"
                            )
                        );
                    }

                    champion.setForca(champion.getForca() + userRequestedPoints);
                    champion.setPontosDisponiveis(champion.getPontosDisponiveis() - userRequestedPoints);
                    JOptionPane.showMessageDialog(
                        null,
                        userRequestedPoints + " pontos atribuidos com sucesso ao atributo força" 
                    );
                }
                if (userOption == 2){
                    userRequestedPoints = Integer.parseInt(
                        JOptionPane.showInputDialog(
                            null, 
                            "Você tem " + champion.getPontosDisponiveis()
                            + " pontos disponiveis para colocar no atributo vitalidade\n"
                            + "Digite abaixo a qntde. de pontos que você deseja atribuir a vitalidade:"
                        )
                    );
                    while (userRequestedPoints > champion.getPontosDisponiveis()){
                        JOptionPane.showMessageDialog(
                            null, 
                            "Você digitou uma quantidade de pontos acima da que você tem disponível."
                        );
                        userRequestedPoints = Integer.parseInt(
                            JOptionPane.showInputDialog(
                                null, 
                                "Você tem " + champion.getPontosDisponiveis()
                                + " pontos disponiveis para colocar no atributo vitalidade\n"
                                + "Digite abaixo a qntde. de pontos que você deseja atribuir a vitalidade:"
                            )
                        );
                    }
                    
                    champion.setVitalidade(champion.getVitalidade() + userRequestedPoints);
                    champion.setPontosDisponiveis(champion.getPontosDisponiveis() - userRequestedPoints);
                    JOptionPane.showMessageDialog(
                        null,
                        userRequestedPoints + " pontos atribuidos com sucesso ao atributo vitalidade" 
                    );
                }
                if (userOption == 3){
                    userRequestedPoints = Integer.parseInt(
                        JOptionPane.showInputDialog(
                            null, 
                            "Você tem " + champion.getPontosDisponiveis()
                            + " pontos disponiveis para colocar no atributo destreza\n"
                            + "Digite abaixo a qntde. de pontos que você deseja atribuir a destreza:"
                        )
                    );
                    while (userRequestedPoints > champion.getPontosDisponiveis()){
                        JOptionPane.showMessageDialog(
                            null, 
                            "Você digitou uma quantidade de pontos acima da que você tem disponível."
                        );
                        userRequestedPoints = Integer.parseInt(
                            JOptionPane.showInputDialog(
                                null, 
                                "Você tem " + champion.getPontosDisponiveis()
                                + " pontos disponiveis para colocar no atributo destreza\n"
                                + "Digite abaixo a qntde. de pontos que você deseja atribuir a destreza:"
                            )
                        );
                    }
                        
                    champion.setDestreza(champion.getDestreza() + userRequestedPoints);
                    champion.setPontosDisponiveis(champion.getPontosDisponiveis() - userRequestedPoints);
                    JOptionPane.showMessageDialog(
                        null,
                        userRequestedPoints + " pontos atribuidos com sucesso ao atributo destreza" 
                    );
                }
                if (userOption == 4){
                    userRequestedPoints = Integer.parseInt(
                        JOptionPane.showInputDialog(
                            null, 
                            "Você tem " + champion.getPontosDisponiveis()
                            + " pontos disponiveis para colocar no atributo poder\n"
                            + "Digite abaixo a qntde. de pontos que você deseja atribuir a poder:"
                        )
                    );
                    while (userRequestedPoints > champion.getPontosDisponiveis()){
                        JOptionPane.showMessageDialog(
                            null, 
                            "Você digitou uma quantidade de pontos acima da que você tem disponível."
                        );
                        userRequestedPoints = Integer.parseInt(
                            JOptionPane.showInputDialog(
                                null, 
                                "Você tem " + champion.getPontosDisponiveis()
                                + " pontos disponiveis para colocar no atributo poder\n"
                                + "Digite abaixo a qntde. de pontos que você deseja atribuir a poder:"
                            )
                        );
                    }
                    
                    champion.setPoder(champion.getPoder() + userRequestedPoints);
                    champion.setPontosDisponiveis(champion.getPontosDisponiveis() - userRequestedPoints);
                    JOptionPane.showMessageDialog(
                        null,
                        userRequestedPoints + " pontos atribuidos com sucesso ao atributo poder"
                    );
                }
            }
        }
    }

    public static void addPrimaryWeapon(){
        Integer weaponMenuUserOption = 1;
        String returnMessage = "======= Selecione o personagem que você deseja alterar a arma primária =======\n0. Retornar para o menu";

        for (int i = 0; i < championsList.size(); i++) {
            Personagem champion = championsList.get(i);
            returnMessage += "\nPersonagem " + (i + 1) + ": \n";
            returnMessage += "  Nome: " + champion.getNome();
        }

        Integer userInput = Integer.parseInt(JOptionPane.showInputDialog(null, returnMessage));
        if (userInput != 0){
            weaponMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createPrimaryWeaponsMenu() + "Digite o número correspondente à arma desejada:"));
            while(weaponMenuUserOption == 0){
                createUserInputedWeapon("primária");
                weaponMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createPrimaryWeaponsMenu() + "Digite o número correspondente à arma desejada:"));
            }

            Personagem champion = championsList.get(userInput - 1);
            champion.definirArmaPersonagem(primaryWeaponsList.get(weaponMenuUserOption - 1));

            JOptionPane.showMessageDialog(null, "Arma primária atribuida com sucesso ao personagem " + champion.getNome());
        }
    }

    public static void createInventaryMenu(){
        String returnMessage = "======= Selecione o tipo de arma que você deseja alterar =======\n0. Retornar para o menu\n";
        returnMessage += "1. Arma primária\n";
        returnMessage += "2. Arma secundária";

        Integer userInput = Integer.parseInt(JOptionPane.showInputDialog(null, returnMessage));
        if (userInput != 0){
            if (userInput == 1){
                addPrimaryWeapon();
            }
            else if (userInput == 2){
                addSecondaryWeapon();
            }
            else if (userInput != 0 | userInput != 1 | userInput != 2){
                changeWeaponsInventaryUserInvalidOption(userInput);
            }
        }
    }

    public static void changeWeaponsInventaryUserInvalidOption(Integer userInput){
        JOptionPane.showConfirmDialog(null, "Digite uma opção válida.");
        createInventaryMenu();
    }
}

