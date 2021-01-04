import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AppSistema {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Scanner entradaString = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int menu, menu2;
        boolean concluido;
        String nomeColab;

        Projeto proj;
        String tituloProj;
        Date dataInicio = null; String dataInicio2;
        Date dataFim = null; String dataFim2;
        String agenciaFinanciadora;
        float valorFinanciado;
        String objetivo;
        String descricao;
        String status;

        Publicacao pub;
        String tituloPub;
        String conferencia;
        int anoPub;

        do {
            exibirMenu();
            menu = entrada.nextInt();

            switch (menu) {
                case 1:     //1 - Criar projeto
                    System.out.println("\n====== Criar Projeto ======");
                    System.out.print("Digite o título: ");
                    tituloProj = entradaString.nextLine();

                    concluido = false;
                    do {
                        try {
                            System.out.print("Digite a data de inicio (DD/MM/AAAA): ");
                            dataInicio2 = entradaString.nextLine();
                            dataInicio = sdf.parse(dataInicio2);
                            concluido = true;
                        } catch (ParseException e) {
                            //e.printStackTrace();
                            System.out.println("A data precisa ser inserida no formato DD/MM/AAAA.");
                        }
                    } while (!concluido);

                    concluido = false;
                    do {
                        try {
                            System.out.print("Digite a data de término (DD/MM/AAAA): ");
                            dataFim2 = entradaString.nextLine();
                            dataFim = sdf.parse(dataFim2);
                            concluido = true;
                        } catch (ParseException e) {
                            //e.printStackTrace();
                            System.out.println("A data precisa ser inserida no formato DD/MM/AAAA.");
                        }
                    } while (!concluido);

                    System.out.print("Digite a agência financiadora: ");
                    agenciaFinanciadora = entradaString.nextLine();
                    System.out.print("Digite o valor financiado: ");
                    valorFinanciado = entrada.nextFloat();
                    System.out.print("Digite o objetivo do projeto: ");
                    objetivo = entradaString.nextLine();
                    System.out.print("Digite a descrição do projeto: ");
                    descricao = entradaString.nextLine();

                    status = "Em elaboração";

                    proj = new Projeto(tituloProj, dataInicio, dataFim, agenciaFinanciadora,
                            valorFinanciado, objetivo, descricao, status);

                    Laboratorio.adicionarProjeto(proj);

                    System.out.println("\nProjeto adicionado com sucesso.");
                    System.out.println(proj.imprimir());
                    break;

                case 2:     //2 - Consultar projeto
                    if (!Laboratorio.getListaProjetos().isEmpty()){     //Verifica se existem projetos cadastrados
                        System.out.println("\nDigite o título do projeto a ser consultado:");
                        tituloProj = entradaString.nextLine();

                        if (Laboratorio.verificarProjeto(tituloProj)) {     //Verifica se o projeto existe
                            System.out.println(Laboratorio.pesquisarProjeto(tituloProj));     //Imprime os dados do projeto
                        } else {
                            System.out.println("\nProjeto não encontrado.");
                            break;
                        }

                        do {
                            exibirMenu2();
                            menu = entrada.nextInt();

                            switch (menu) {
                                case 1:     //1 - Alocar participantes
                                    if (Laboratorio.verificarStatusProj(tituloProj)) {     //Verifica se o status do projeto é "Em elaboração"
                                        do {
                                            System.out.println("\nDigite o nome do colaborador:");
                                            nomeColab = entradaString.nextLine();

                                            if (Laboratorio.verificarColaborador(nomeColab)) {
                                                Laboratorio.associarProj(tituloProj, nomeColab);
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

                                case 2:     //2 - Iniciar projeto
                                    if (!Laboratorio.verificarProfessorProj(tituloProj)) {
                                        System.out.println("\nO projeto não pode ser iniciado.\nDeve existir pelo menos 1 professor alocado ao projeto.");
                                    } else if (!Laboratorio.verificarDadosProj(tituloProj)) {
                                        System.out.println("\nO projeto não pode ser iniciado.\nPreencha todos os dados para poder iniciar.");
                                    } else if (!Laboratorio.verificarStatusProj(tituloProj)) {
                                        System.out.println("\nO projeto já foi iniciado anteriormente.");
                                    } else if (!Laboratorio.verificarSituacaoAluno(tituloProj)) {
                                        System.out.println("\nO projeto não pode ser iniciado. Um aluno não pode participar de mais de 2 projetos em andamento.");
                                    } else {
                                        Laboratorio.alterarStatus(tituloProj);
                                        System.out.println("\nProjeto iniciado com sucesso.\nStatus Atual: Em andamento");
                                    }
                                    break;

                                case 3:     //Voltar
                                    break;

                                default:
                                    System.out.println("\nOpção inválida.");
                            }
                        } while (menu != 3);
                    } else {
                        System.out.println("\nNão existem projetos cadastrados.");
                    }
                    break;

                case 3:     //3 - Cadastrar colaborador
                    do {
                        exibirMenu3();
                        menu = entrada.nextInt();
                        switch (menu) {
                            case 1:
                                cadastrarColaborador("Aluno");
                                menu = 4;
                                break;
                            case 2:
                                cadastrarColaborador("Professor");
                                menu = 4;
                                break;
                            case 3:
                                cadastrarColaborador("Pesquisador");
                                menu = 4;
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("\nOpção inválida.");
                        }
                    } while (menu != 4);
                    break;

                case 4:     //4 - Consultar colaborador
                    if (!Laboratorio.getListaColaboradores().isEmpty()){     //Verifica se existem colaboradores cadastrados
                        do {
                            System.out.println("\nDigite o nome do colaborador a ser consultado:");
                            nomeColab = entradaString.nextLine();

                            if (Laboratorio.verificarColaborador(nomeColab)) {     //Verifica se o colaborador existe
                                System.out.println(Laboratorio.pesquisarColaborador(nomeColab));     //Imprime os dados do colaborador
                                break;
                            } else {
                                System.out.println("\nColaborador não encontrado.");
                                do {
                                    System.out.println("1 - Tentar novamente");
                                    System.out.println("2 - Voltar");
                                    System.out.print("=====> Escolha uma opção: ");
                                    menu = entrada.nextInt();

                                    switch (menu) {
                                        case 1:
                                            continue;
                                        case 2:
                                            break;
                                        default:
                                            System.out.println("\nOpção inválida.\n");
                                    }
                                } while (menu != 2 && menu != 1);
                            }
                        } while (menu == 1);
                    } else {
                        System.out.println("\nNão existem colaboradores cadastrados.");
                    }
                    break;

                case 5:     //5 - Cadastrar produção acadêmica
                    do {
                        exibirMenu5();
                        menu = entrada.nextInt();

                        switch (menu) {
                            case 1:     //Cadastrar publicação
                                System.out.println("\n====== Cadastrar Produção Acadêmica ======");
                                System.out.print("Digite o título da publicação: ");
                                tituloPub = entradaString.nextLine();
                                System.out.print("Digite o nome da conferência onde foi publicada: ");
                                conferencia = entradaString.nextLine();
                                System.out.print("Digite o ano de publicação: ");
                                anoPub = entrada.nextInt();

                                pub = new Publicacao(tituloPub, conferencia, anoPub);

                                Laboratorio.adicionarPublicacao(pub);
                                System.out.println("\nPublicação adicionada com sucesso.");

                                menu = 3;
                                break;

                            case 2:     // Cadastrar orientação

                                break;

                            case 3:     //Cancelar
                                break;

                            default:
                                System.out.println("\nOpção inválida.");
                        }
                    } while (menu != 3);
                    break;

                case 6:     //6 - Consultar publicação
                    if (!Laboratorio.getListaPublicacoes().isEmpty()){     //Verifica se existem publicações cadastrados
                        do {
                            System.out.println("\nDigite o título da publicação a ser consultada:");
                            tituloPub = entradaString.nextLine();

                            if (Laboratorio.verificarPublicaao(tituloPub)) {     //Verifica se a publicação existe
                                System.out.println(Laboratorio.pesquisarPublicacao(tituloPub));     //Imprime os dados da publicação
                                break;
                            } else {
                                System.out.println("\nPublicação não encontrada.");
                                do {
                                    System.out.println("1 - Tentar novamente");
                                    System.out.println("2 - Voltar");
                                    System.out.print("=====> Escolha uma opção: ");
                                    menu = entrada.nextInt();

                                    switch (menu) {
                                        case 1:
                                            continue;
                                        case 2:
                                            break;
                                        default:
                                            System.out.println("\nOpção inválida.\n");
                                    }
                                } while (menu != 2 && menu != 1);
                            }

                            do {
                                exibirMenu6();
                                menu2 = entrada.nextInt();

                                switch (menu2) {
                                    case 1:     //1 - Incluir autores
                                        do {
                                            System.out.println("\nDigite o nome do colaborador:");
                                            nomeColab = entradaString.nextLine();

                                            if (Laboratorio.verificarColaborador(nomeColab)) {
                                                Laboratorio.associarPub(tituloPub, nomeColab);
                                                System.out.println("\nO colaborador agora é um autor da publicação.");
                                                break;
                                            } else {
                                                System.out.println("\nColaborador não encontrado.\nCadastre o colaborador no sistema antes de associá-lo a uma publicação.\n");
                                                System.out.println("1 - Tentar novamente");
                                                System.out.println("2 - Voltar");
                                                System.out.print("=====> Escolha uma opção: ");
                                                menu2 = entrada.nextInt();
                                            }
                                        } while (menu2 != 2);
                                        break;

                                    case 2:
                                        break;

                                    default:
                                        System.out.println("\nOpção inválida.");
                                }
                            } while (menu2 != 2);

                        } while (menu == 1);
                    } else {
                        System.out.println("\nNão existem publicações cadastradas.");
                    }



                    break;

                case 7:     //7 - Relatório do laboratório
                    System.out.println("\n====== Dados Gerais do Laboratório ======");
                    System.out.println("Número de colaboradores: " + Laboratorio.getListaColaboradores().size());
                    System.out.println("Número de projetos em elaboração: " + Laboratorio.qtdProjetosElaboracao());
                    System.out.println("Número de projetos em andamento: " + Laboratorio.qtdProjetosAndamento());
                    System.out.println("Número de porjetos concluídos: " + Laboratorio.qtdProjetosConcluidos());
                    System.out.println("Número total de projetos: " + Laboratorio.getListaProjetos().size());

                    //System.out.println(""); //Número de produção acadêmica por tipo de produção
                    break;

                case 8:     //8 - Encerrar
                    System.out.println("\nSistema encerrado.");
                    break;

                default:
                    System.out.println("\nOpção inválida.");
            }
        } while (menu != 8);

    }

    static void cadastrarColaborador(String ocupacao) {
        Scanner entradaString = new Scanner(System.in);
        Colaborador colab;
        String nomeColab;
        String email;

        System.out.println("\n====== Cadastrar Colaborador ======");
        System.out.print("Digite o nome: ");
        nomeColab = entradaString.nextLine();
        System.out.print("Digite o email: ");
        email = entradaString.nextLine();

        colab = new Colaborador(nomeColab, email, ocupacao);
        Laboratorio.adicionarColaborador(colab);
        System.out.println("\nColaborador cadastrado com sucesso.");
    }

    static void exibirMenu() {     //Menu principal
        System.out.println("\n====== LABORATÓRIO ======");
        System.out.println("1 - Criar projeto");
        System.out.println("2 - Consultar projeto");
        System.out.println("3 - Cadastrar colaborador");
        System.out.println("4 - Consultar colaborador");
        System.out.println("5 - Cadastrar produção acadêmica");
        System.out.println("6 - Consultar produção acadêmica");
        System.out.println("7 - Relatório do laboratório");
        System.out.println("8 - Encerrar");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu2() {     //Menu de "Consultar projeto"
        System.out.println("\n====== OPÇÕES DO PROJETO ======");
        System.out.println("1 - Alocar participante");
        System.out.println("2 - Iniciar projeto");
        System.out.println("3 - Voltar");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu3() {     //Menu de "Cadastrar colaborador"
        System.out.println("\nQuem você deseja cadastrar?");
        System.out.println("1 - Cadastrar um aluno");
        System.out.println("2 - Cadastrar um professor");
        System.out.println("3 - Cadastrar um pesquisador");
        System.out.println("4 - Cancelar");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu5() {     //Menu de "Cadastrar produção acadêmica"
        System.out.println("\nQue tipo de produção acadêmica você deseja cadastrar?");
        System.out.println("1 - Publicação");
        System.out.println("2 - Orientação");
        System.out.println("3 - Cancelar");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu6() {     //Menu de "Consultar produção acadêmica"
        System.out.println("\n====== OPÇÕES DA PUBLICAÇÃO ======");
        System.out.println("1 - Inlcuir autores");
        System.out.println("2 - Voltar");
        System.out.print("=====> Escolha uma opção: ");
    }
}
