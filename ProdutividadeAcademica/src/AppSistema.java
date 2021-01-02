import java.util.Scanner;

public class AppSistema {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Scanner entradaString = new Scanner(System.in);

        int menu;

        Projeto proj;
        String tituloProj;
        String dataInicio;
        String dataFim;
        String agenciaFinanciadora;
        float valorFinanciado;
        String objetivo;
        String descricao;
        String status;

        Colaborador colab;
        String nomeColab;
        String email;
        String ocupacao = null;

        do {
            exibirMenu();
            menu = entrada.nextInt();

            switch (menu) {
                case 1:     //1 - Criar projeto
                    System.out.println("\n====== Criar Projeto ======");
                    System.out.println("Digite o título:");
                    tituloProj = entradaString.nextLine();
                    System.out.println("Digite a data de inicio:");
                    dataInicio = entradaString.nextLine();
                    System.out.println("Digite a data de término:");
                    dataFim = entradaString.nextLine();
                    System.out.println("Digite a agência financiadora:");
                    agenciaFinanciadora = entradaString.nextLine();
                    System.out.println("Digite o valor financiado:");
                    valorFinanciado = entrada.nextFloat();
                    System.out.println("Digite o objetivo do projeto:");
                    objetivo = entradaString.nextLine();
                    System.out.println("Digite a descrição do projeto:");
                    descricao = entradaString.nextLine();

                    status = "Em elaboração";

                    proj = new Projeto(tituloProj, dataInicio, dataFim, agenciaFinanciadora,
                            valorFinanciado, objetivo, descricao, status);

                    Laboratorio.adicionarProjeto(proj);

                    System.out.println("\nProjeto criado com sucesso.");
                    System.out.println(proj.imprimir());
                    break;

                case 2:     //2 - Buscar projeto
                    if (!(Laboratorio.getListaProjetos().isEmpty())){ //Verifica se existem projetos cadastrados
                        System.out.println("\nDigite o título do projeto a ser buscado:");
                        tituloProj = entradaString.nextLine();

                        if (Laboratorio.verificarProjeto(tituloProj)) { //Verifica se o projeto existe
                            System.out.println(Laboratorio.pesquisarProjeto(tituloProj)); //Imprime os dados do projeto
                        } else {
                            System.out.println("\nProjeto não encontrado.");
                            break;
                        }

                        do {
                            exibirMenu2();
                            menu = entrada.nextInt();

                            switch (menu) {
                                case 1:     //1 - Alocar participantes
                                    if (Laboratorio.verificarStatus(tituloProj)) { //Verifica se o status do projeto é "Em elaboração"
                                        do {
                                            System.out.println("\nDigite o nome do colaborador:");
                                            nomeColab = entradaString.nextLine();

                                            if (Laboratorio.verificarColaborador(nomeColab)) {
                                                Laboratorio.associar(tituloProj, nomeColab);
                                                System.out.println("\nColaborador alocado com sucesso.");
                                                break;
                                            } else {
                                                System.out.println("\nColaborador não encontrado.\nCadastre o colaborador no sistema antes de alocá-lo a um projeto.\n");
                                                System.out.println("1 - Tentar novamente");
                                                System.out.println("2 - Voltar");
                                                System.out.print("=====> Escolha uma opção: ");
                                                menu = entrada.nextInt();
                                            }
                                        } while (menu != 2);
                                    } else {
                                        System.out.println("\nColaboradores só podem ser alocados em projetos em elaboração.");
                                    }
                                    break;

                                case 2: //2 - Iniciar projeto
                                    if (!Laboratorio.verificarProfessor(tituloProj)) {
                                        System.out.println("\nO projeto não pode ser iniciado.\nDeve existir pelo menos 1 professor alocado ao projeto.");
                                    } else if (!Laboratorio.verificarDados(tituloProj)) {
                                        System.out.println("\nO projeto não pode ser iniciado.\nPreencha todos os dados para poder iniciar.");
                                    } else if (!Laboratorio.verificarStatus(tituloProj)) {
                                        System.out.println("\nO projeto já foi iniciado anteriormente.");
                                    } else if (!Laboratorio.verificarSituacaoAluno(tituloProj)) {
                                        System.out.println("\nO projeto não pode ser iniciado. Um aluno não pode participar de mais de 2 projetos em andamento.");
                                    } else {
                                        Laboratorio.alterarStatus(tituloProj);
                                        System.out.println("\nProjeto iniciado com sucesso.\nStatus Atual: Em andamento");
                                    }
                                    break;

                                case 7:
                                    break;

                                default:
                                    System.out.println("\nOpção inválida.");
                            }
                        } while (menu != 7);
                    } else {
                        System.out.println("\nNão existem projetos cadastrados.");
                    }
                    break;

                case 3:     //3 - Cadastrar colaborador
                    exibirMenu3();
                    menu = entrada.nextInt();

                    System.out.println("\n====== Cadastrar Colaborador ======");
                    System.out.println("Digite o nome:");
                    nomeColab = entradaString.nextLine();
                    System.out.println("Digite o email:");
                    email = entradaString.nextLine();

                    switch (menu) {
                        case 1:
                            ocupacao = "Aluno";
                            break;
                        case 2:
                            ocupacao = "Professor";
                            break;
                        case 3:
                            ocupacao = "Pesquisador";
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("\nOpção inválida.");
                    }

                    colab = new Colaborador(nomeColab, email, ocupacao);
                    Laboratorio.adicionarColaborador(colab);
                    System.out.println("\nColaborador cadastrado com sucesso.");
                    break;

                case 4: //4 - Buscar colaborador
                    if (!(Laboratorio.getListaColaboradores().isEmpty())){ //Verifica se existem colaboradores cadastrados
                        do {
                            System.out.println("\nDigite o nome do colaborador a ser buscado:");
                            nomeColab = entradaString.nextLine();

                            if (Laboratorio.verificarColaborador(nomeColab)) { //Verifica se o colaborador existe
                                System.out.println(Laboratorio.pesquisarColaborador(nomeColab)); //Imprime os dados do colaborador
                                break;
                            } else {
                                System.out.println("\nColaborador não encontrado.");
                                System.out.println("1 - Tentar novamente");
                                System.out.println("2 - Voltar");
                                System.out.print("=====> Escolha uma opção: ");
                                menu = entrada.nextInt();
                            }
                        } while (menu != 2);
                    } else {
                        System.out.println("\nNão existem colaboradores cadastrados.");
                    }
                    break;

                case 5:
                    System.out.println("\nSistema encerrado.");
                    break;

                default:
                    System.out.println("\nOpção inválida.");
            }
        } while (menu != 5);

    }

    static void exibirMenu() { //Menu principal
        System.out.println("\n====== LABORATÓRIO ======");
        System.out.println("1 - Criar projeto");
        System.out.println("2 - Buscar projeto");
        System.out.println("3 - Cadastrar colaborador");
        System.out.println("4 - Buscar colaborador");
        System.out.println("5 - Sair");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu2() { //Menu de "Buscar projeto"
        System.out.println("\n====== OPÇÕES DO PROJETO ======");
        System.out.println("1 - Alocar participante");
        System.out.println("2 - Iniciar projeto");
        System.out.println("3 - ");
        System.out.println("4 - ");
        System.out.println("5 - ");
        System.out.println("6 - ");
        System.out.println("7 - Voltar");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu3(){ //Menu de "Cadastrar colaborador"
        System.out.println("\nQuem você deseja cadastrar? ");
        System.out.println("1 - Cadastrar um aluno");
        System.out.println("2 - Cadastrar um professor");
        System.out.println("3 - Cadastrar um pesquisador");
        System.out.println("4 - Cancelar");
        System.out.print("=====> Escolha uma opção: ");
    }
}
