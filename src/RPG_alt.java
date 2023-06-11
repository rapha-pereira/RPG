import java.util.ArrayList;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RPG_alt {
    static boolean createNextChampionBool = true;
    static Integer createNextChampionIter = 1;
    static Integer menuInput;
    static List<Personagem> championsList = new ArrayList<>();
    static List<Arma> weaponsList = new ArrayList<>();
    
    
    public static Integer mainMenu(){
        return Integer.parseInt(
            JOptionPane.showInputDialog(
                "======= MENU =======\n" +
                    "1 - Criar personagem\n" +
                    "2 - Adicionar habilidade a um personagem\n" +
                    "3 - Visualizar informações de um personagem\n" +
                    "4 - Consultar lista de personagens\n" +
                    "5 - Batalhar\n" +
                    "6 - Sair\n" +
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

        menuInput = mainMenu();
        while (menuInput != 6){
            switch (menuInput) {
                case 1:
                    createChampion();
                    menuInput = mainMenu();
                    break;
                case 2:
                    adicionarHabilidade();
                    menuInput = mainMenu();
                    break;
                // case 3:
                //     visualizarInformacoes(personagens);
                //     break;
                case 4:
                    showChampionsList();
                    menuInput = mainMenu();
                    break;
                //     break;
                // case 5:
                //     batalhar(personagens);
                //     break;
                // case 6:
                //     JOptionPane.showMessageDialog(null, "Obrigado por jogar! Até mais!");
                //     break;
                // default:
                //     JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, escolha uma opção válida.");
                //     break;
            }
        }
    }

    // Helpers
    public static void createWeaponsPreDefinedKit(){
        Arma machado = new Arma(25, 15, "Machado", "primária");
        Arma espada = new Arma(15, 5, "Espada", "primária");
        Arma escopeta = new Arma(30, 20, "Escopeta", "primária");
        weaponsList.add(machado);
        weaponsList.add(espada);
        weaponsList.add(escopeta);
    }

    public static void createUserInputedWeapon() {
        Integer attackDefense = Integer.parseInt(JOptionPane.showInputDialog("Qual será valor de ataque e defesa de sua arma?"));
        Integer weigth = Integer.parseInt(JOptionPane.showInputDialog("Qual será o peso da sua arma?"));
        String name = JOptionPane.showInputDialog("Qual será o nome da sua arma?");
        String position = JOptionPane.showInputDialog("Sua arma será primária ou secundária?\n(Favor digitar primária ou secundária com acentos)");

        Arma userWeapon = new Arma(attackDefense, weigth, name, position);
        weaponsList.add(userWeapon);
    }

    public static String createWeaponsMenu(){
        String mensagemArmas = "Armas disponíveis:\n0. Criar nova arma\n";
        for (int i = 0; i < weaponsList.size(); i++) {
            Arma arma = weaponsList.get(i);
            mensagemArmas += (i + 1) + ". " + arma.getTipo() + " - Ataque/Defesa: " + arma.getAtaqueDefesa() + " | Peso: " + arma.getPeso() + " | Posição: " + arma.getPosicaoArma() + "\n";
        }

        return mensagemArmas;
    }

    public static Boolean weaponsMenuCheck(Integer userInput){
        try {
            weaponsList.get(userInput);
            return true;
        }
        catch (IndexOutOfBoundsException nexc) {
            return false;
        }
    }

    // Menu functions
    public static void createChampion(){
        String name = JOptionPane.showInputDialog("Digite o nome do personagem:");
        String description = JOptionPane.showInputDialog("Digite a descrição do personagem:");
        Integer age = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade do personagem:"));
        Integer weaponMenuUserOption = 1;

        weaponMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createWeaponsMenu() + "Digite o número correspondente à arma desejada:"));
        while(weaponMenuUserOption == 0){
            createUserInputedWeapon();
            weaponMenuUserOption = Integer.parseInt(JOptionPane.showInputDialog(createWeaponsMenu() + "Digite o número correspondente à arma desejada:"));
        }

        Personagem championObj = new Personagem(name, description, age, weaponsList.get(weaponMenuUserOption - 1));
        championsList.add(championObj);
        JOptionPane.showMessageDialog(null, "Personagem criado com sucesso.");
    }

    public static void showChampionsList(){
        String returnMessage = "======= Lista de personagens atual =======";
        for (int i = 0; i < championsList.size(); i++) {
            Personagem champion = championsList.get(i);
            String mainWeapon = "";
            String secondaryWeapon = "";

            if (champion.getArmaPrimaria() == null){mainWeapon = "Não há";}
            else {mainWeapon = champion.getArmaPrimaria().getTipo();}
            if (champion.getArmaSecundaria() == null){secondaryWeapon = "Não há";}
            else {secondaryWeapon = champion.getArmaSecundaria().getTipo();}

            returnMessage += "\nPersonagem " + (i + 1) + ": \n";
            returnMessage += "  Nome: " + champion.getNome() + "\n";
            returnMessage += "  Armas atuais: (Primária: " + mainWeapon + " / Secundária: " + secondaryWeapon + ")";
        }
        JOptionPane.showMessageDialog(null, returnMessage);
    }
}

        // Integer userWelcomeChoice = scan.nextInt();

        // if (userWelcomeChoice == 1){
        //     scan.nextLine();
        //     while (createNextChampionBool){
        //         Weapon weaponObj = new Weapon();
        //         List<String> userWeaponTypes = new ArrayList<>();
        //         //Habilities habilitiesObj = new Habilities();

        //         System.out.println(
        //             "Coloque aqui o nome que você gostaria de dar ao seu personagem:"
        //         );
        //         String championName = scan.nextLine();

        //         System.out.println(
        //             "Coloque aqui o nível inicial do seu personagem:"
        //         );
        //         Integer championLevel = scan.nextInt();

        //         scan.nextLine(); // cleaning scanner for the next input

        //         System.out.println(
        //             "Coloque aqui a descrição que você gostaria de dar ao seu personagem:"
        //         );
        //         String championDescription = scan.nextLine();

        //         System.out.println(
        //             "Coloque aqui a idade de seu personagem:"
        //         );
        //         Integer championAge = scan.nextInt();

        //         System.out.println(
        //             "Coloque aqui o valor de força inicial do seu personagem:"
        //         );
        //         Integer championPower = scan.nextInt();

        //         System.out.println(
        //             "Coloque aqui o valor de vitalidade inicial do seu personagem:"
        //         );
        //         Integer championVitality = scan.nextInt();

        //         System.out.println(
        //             "Coloque aqui o valor de destreza inicial do seu personagem:"
        //         );
        //         Integer championDexterity = scan.nextInt();

        //         System.out.println(
        //             "Coloque aqui o dano de ataque inicial do seu personagem:" // dano de ataque = atributo de força
        //         );
        //         Integer championAttackDmg = scan.nextInt();

        //         System.out.println(
        //             "Escolha uma ou mais, separado por vírgulas, sem espaço entre cada uma, "
        //             + "tipos de armas primarias que seu personagem terá." +
        //             "\nTipos de armas possíveis são: espada, machado, pistola, escopeta"
        //         );
        //         userWeaponTypes.addAll(Arrays.asList(scan.next().split("\\,")));
        //         DefWeapon(userWeaponTypes, weaponObj);

        //         Champion championObj = new Champion(
        //             championName, 
        //             championLevel, 
        //             championDescription, 
        //             championAge, 
        //             championPower,
        //             championVitality,
        //             championDexterity,
        //             championAttackDmg,
        //             weaponObj
        //         );
        //         userChampionsList.add(championObj);
                
        //         System.out.println(
        //             "\nDigite: 1 - Para criar mais um personagem | 2 - Para listar os personagens existentes e seus atributos" +
        //             " | 3 - Para sair"
        //         );
        //         Integer userChoice = scan.nextInt();

        //         if (userChoice.equals(3)){
        //             createNextChampionBool = false;
        //         }
        //         if (userChoice.equals(2)){
        //             Integer i = 1;
        //             for (Champion champion: userChampionsList){
        //                 System.out.println(
        //                     "\nPersonagem " + i.toString() + ": \n"
        //                     + "Nome: " + champion.getChampionName()
        //                     + "\nDescrição: " + champion.getChampionDescription()
        //                     + "\nIdade: " + champion.getChampionAge()
        //                     + "\nNível: " + champion.getChampionLevel()
        //                     + "\nArma(s) primária(s): " + champion.getChampionPrimaryWeapon()
        //                     + "\nForça: " + champion.getChampionPower()
        //                     + "\nVitalidade: " + champion.getChampionVitality()
        //                     + "\nDestreza: " + champion.getChampionDexterity()
        //                     + "\nDano de ataque básico: " + champion.getChampionAttackDmg()
        //                 );
        //                 i++;
        //             }
        //             createNextChampionBool = false;
        //         }
        //         else{
        //             System.out.println("");
        //             scan.nextLine();
        //         }
        //     }
        // }
    
