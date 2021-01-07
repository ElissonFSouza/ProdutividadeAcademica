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
        String nomeColab, nomeColab2;

        Projeto proj;
        String tituloProj;
        Date dataInicio = null;
        Date dataFim = null;
        String agenciaFinanciadora;
        float valorFinanciado;
        String objetivo;
        String descricao;
        String status;

        Orientacao ori;

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
                            dataInicio = sdf.parse(entradaString.nextLine());
                            concluido = true;
                        } catch (ParseException e) {
                            System.out.println("A data precisa ser inserida no formato DD/MM/AAAA.");
                        }
                    } while (!concluido);

                    concluido = false;
                    do {
                        try {
                            System.out.print("Digite a data de término (DD/MM/AAAA): ");
                            dataFim = sdf.parse(entradaString.nextLine());
                            concluido = true;
                        } catch (ParseException e) {
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
                    System.out.println("\nConsulte o projeto através do menu principal para acessar opções adicionais.");
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
                                    if (Laboratorio.verificarStatusProj(tituloProj, "Em elaboração")) {     //Verifica se o status do projeto é "Em elaboração"
                                        do {
                                            System.out.println("\nDigite o nome do colaborador:");
                                            nomeColab = entradaString.nextLine();

                                            if (Laboratorio.verificarColaborador(nomeColab)) {
                                                Laboratorio.associarColabProj(tituloProj, nomeColab);
                                                System.out.println("\nColaborador alocado com sucesso.");
                                                break;
                                            } else {
                                                System.out.println("\nColaborador não encontrado.\nCadastre o colaborador no sistema antes de alocá-lo a um projeto.\n");
                                                do {
                                                    System.out.println("1 - Tentar novamente");
                                                    System.out.println("2 - Voltar");
                                                    System.out.print("=====> Escolha uma opção: ");
                                                    menu = entrada.nextInt();
                                                    if (menu != 1 & menu != 2) {
                                                        System.out.println("\nOpção inválida.\n");
                                                    }
                                                } while (menu != 1 & menu != 2);
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
                                    } else if (!Laboratorio.verificarStatusProj(tituloProj, "Em elaboração")) {
                                        System.out.println("\nO projeto já foi iniciado anteriormente.");
                                    } else if (!Laboratorio.verificarSituacaoAluno(tituloProj)) {
                                        System.out.println("\nO projeto não pode ser iniciado. Um aluno não pode participar de mais de 2 projetos em andamento.");
                                    } else {
                                        Laboratorio.alterarStatus(tituloProj);
                                        System.out.println("\nProjeto iniciado com sucesso.\nStatus atual: Em andamento");
                                    }
                                    break;

                                case 3:     //3 - Concluir projeto
                                    if (!Laboratorio.verificarStatusProj(tituloProj, "Em andamento")) {
                                        if (Laboratorio.verificarStatusProj(tituloProj, "Em elaboração")) {
                                            System.out.println("\nProjetos em elaboração não podem ser concluídos.");
                                        } else if (Laboratorio.verificarStatusProj(tituloProj, "Concluído")) {
                                            System.out.println("\nO projeto já foi concluído anteriormente.");
                                        }
                                    } else if (!Laboratorio.verificarPubProjeto(tituloProj)) {
                                        System.out.println("\nO projeto não pode ser concluído. Deve existir publicações associadas ao projeto.");
                                    } else {
                                        Laboratorio.alterarStatus(tituloProj);
                                        System.out.println("\nProjeto concluído com sucesso.\nStatus atual: Concluído");
                                    }
                                    break;

                                case 4: //4 - Exibir dados do projeto
                                    System.out.println(Laboratorio.pesquisarProjeto(tituloProj));     //Imprime os dados do projeto
                                    break;

                                case 5:     //Voltar
                                    break;

                                default:
                                    System.out.println("\nOpção inválida.\n");
                            }
                        } while (menu != 5);
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
                                do {
                                    System.out.println("\nQue tipo de aluno?");
                                    System.out.println("1 - Aluno de graduação");
                                    System.out.println("2 - Aluno de mestrado");
                                    System.out.println("3 - Aluno de doutorado");
                                    System.out.println("4 - Cancelar");
                                    System.out.print("=====> Escolha uma opção: ");
                                    menu = entrada.nextInt();
                                    switch (menu) {
                                        case 1:
                                            cadastrarColaborador("Aluno de graduação");
                                            menu = 4;
                                            break;
                                        case 2:
                                            cadastrarColaborador("Aluno de mestrado");
                                            menu = 4;
                                            break;
                                        case 3:
                                            cadastrarColaborador("Aluno de doutorado");
                                            menu = 4;
                                            break;
                                        case 4:
                                            break;
                                        default:
                                            System.out.println("\nOpção inválida.\n");
                                    }
                                } while (menu != 4);
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
                                System.out.println("\nOpção inválida.\n");
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
                                } while (menu != 1 && menu != 2);
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
                                System.out.println("\n====== Cadastrar Publicação ======");
                                System.out.print("Digite o título da publicação: ");
                                tituloPub = entradaString.nextLine();
                                System.out.print("Digite o nome da conferência onde foi publicada: ");
                                conferencia = entradaString.nextLine();
                                System.out.print("Digite o ano de publicação: ");
                                anoPub = entrada.nextInt();

                                pub = new Publicacao(tituloPub, conferencia, anoPub);

                                Laboratorio.adicionarPublicacao(pub);
                                System.out.println("\nPublicação adicionada com sucesso.");
                                System.out.println(pub.imprimir());
                                System.out.println("\nConsulte a publicação através do menu principal para acessar opções adicionais,\ncomo adicionar autores e associar a um projeto.");

                                menu = 3;
                                break;

                            case 2:     // Cadastrar orientação
                                System.out.println("\n====== Cadastrar Orientação ======");
                                do {
                                    System.out.println("Digite o nome do colaborador orientador:");
                                    nomeColab = entradaString.nextLine();

                                    if (Laboratorio.verificarColaborador(nomeColab)) {
                                        if (Laboratorio.verificarOcupacaoColab(nomeColab, "Professor")) {
                                            do {
                                                System.out.println("Digite o nome do colaborador a ser orientado:");
                                                nomeColab2 = entradaString.nextLine();

                                                if (Laboratorio.verificarColaborador(nomeColab2)) {
                                                    if (Laboratorio.verificarOcupacaoColab(nomeColab2, "Aluno de graduação")
                                                    || Laboratorio.verificarOcupacaoColab(nomeColab2, "Aluno de mestrado")
                                                    || Laboratorio.verificarOcupacaoColab(nomeColab2, "Aluno de doutorado")) {
                                                        do {
                                                            System.out.println("\nDeseja associar esta orientação a um projeto?");
                                                            System.out.println("1 - Sim");
                                                            System.out.println("2 - Não");
                                                            System.out.print("=====> Escolha uma opção: ");
                                                            menu = entrada.nextInt();
                                                            if (menu != 1 & menu != 2) {
                                                                System.out.println("\nOpção inválida.\n");
                                                            }
                                                        } while (menu != 1 & menu != 2);
                                                        if (menu == 1) {
                                                            do {
                                                                System.out.println("\nDigite o título do projeto:");
                                                                tituloProj = entradaString.nextLine();

                                                                if (Laboratorio.verificarProjeto(tituloProj)) {
                                                                    if (Laboratorio.verificarStatusProj(tituloProj, "Em andamento")) {
                                                                        ori = new Orientacao(nomeColab, nomeColab2, tituloProj);
                                                                        Laboratorio.adicionarOrientacao(ori, nomeColab, tituloProj);
                                                                        System.out.println("\nOrientação cadastrada com sucesso.");
                                                                        break;
                                                                    } else {
                                                                        System.out.println("\nOrientações só podem ser associadas a projetos em andamento.");
                                                                    }
                                                                } else {
                                                                    System.out.println("\nProjeto não encontrado.");
                                                                    do {
                                                                        System.out.println("1 - Tentar novamente");
                                                                        System.out.println("2 - Voltar");
                                                                        System.out.print("=====> Escolha uma opção: ");
                                                                        menu = entrada.nextInt();
                                                                        if (menu != 1 & menu != 2) {
                                                                            System.out.println("\nOpção inválida.\n");
                                                                        }
                                                                    } while (menu != 1 & menu != 2);
                                                                }
                                                            } while (menu != 2);
                                                        } else {
                                                            ori = new Orientacao(nomeColab, nomeColab2, null);
                                                            Laboratorio.adicionarOrientacao(ori, nomeColab, null);
                                                            System.out.println("\nOrientação cadastrada com sucesso.");
                                                        }
                                                    } else {
                                                        System.out.println("\nO colaborador não é um professor. Operação cancelada.");
                                                    }
                                                    break;
                                                } else {
                                                    System.out.println("\nColaborador não encontrado.");
                                                    do {
                                                        System.out.println("1 - Tentar novamente");
                                                        System.out.println("2 - Voltar");
                                                        System.out.print("=====> Escolha uma opção: ");
                                                        menu = entrada.nextInt();
                                                        if (menu != 1 & menu != 2) {
                                                            System.out.println("\nOpção inválida.\n");
                                                        }
                                                    } while (menu != 1 & menu != 2);
                                                }
                                            } while (menu != 2);
                                        } else {
                                            System.out.println("\nO colaborador não é um professor. Operação cancelada.");
                                        }
                                        break;
                                    } else {
                                        System.out.println("\nColaborador não encontrado.");
                                        do {
                                            System.out.println("1 - Tentar novamente");
                                            System.out.println("2 - Voltar");
                                            System.out.print("=====> Escolha uma opção: ");
                                            menu = entrada.nextInt();
                                            if (menu != 1 & menu != 2) {
                                                System.out.println("\nOpção inválida.\n");
                                            }
                                        } while (menu != 1 & menu != 2);
                                    }
                                } while (menu != 2);
                                menu = 3;
                                break;

                            case 3:     //Cancelar
                                break;

                            default:
                                System.out.println("\nOpção inválida.\n");
                        }
                    } while (menu != 3);
                    break;

                case 6:     //6 - Consultar publicação
                    if (!Laboratorio.getListaPublicacoes().isEmpty()){     //Verifica se existem publicações cadastrados
                        System.out.println("\nDigite o título da publicação a ser consultada:");
                        tituloPub = entradaString.nextLine();

                        if (Laboratorio.verificarPublicacao(tituloPub)) {     //Verifica se a publicação existe
                            System.out.println(Laboratorio.pesquisarPublicacao(tituloPub));     //Imprime os dados da publicação
                        } else {
                            System.out.println("\nPublicação não encontrada.");
                            break;
                        }

                        do {
                            exibirMenu6();
                            menu = entrada.nextInt();

                            switch (menu) {
                                case 1:     //1 - Incluir autores
                                    do {
                                        System.out.println("\nDigite o nome do colaborador:");
                                        nomeColab = entradaString.nextLine();

                                        if (Laboratorio.verificarColaborador(nomeColab)) {
                                            Laboratorio.associarColabPub(tituloPub, nomeColab);
                                            System.out.println("\nO colaborador agora é um autor da publicação.");
                                            do {
                                                System.out.println("1 - Adicionar outro autor");
                                                System.out.println("2 - Finalizar");
                                                System.out.print("=====> Escolha uma opção: ");
                                                menu2 = entrada.nextInt();
                                                if (menu2 != 1 & menu2 != 2) {
                                                    System.out.println("\nOpção inválida.\n");
                                                }
                                            } while (menu2 != 1 & menu2 != 2);
                                        } else {
                                            System.out.println("\nColaborador não encontrado.\nCadastre o colaborador no sistema antes de associá-lo a uma publicação.\n");
                                            do {
                                                System.out.println("1 - Tentar novamente");
                                                System.out.println("2 - Voltar");
                                                System.out.print("=====> Escolha uma opção: ");
                                                menu2 = entrada.nextInt();
                                                if (menu2 != 1 & menu2 != 2) {
                                                    System.out.println("\nOpção inválida.\n");
                                                }
                                            } while (menu2 != 1 & menu2 != 2);
                                        }
                                    } while (menu2 != 2);
                                    break;

                                case 2:     //2 - Associar a um projeto
                                    do {
                                        System.out.println("\nDigite o título do projeto:");
                                        tituloProj = entradaString.nextLine();

                                        if (Laboratorio.verificarProjeto(tituloProj)) {
                                            if (Laboratorio.verificarStatusProj(tituloProj, "Em andamento")) {
                                                Laboratorio.associarPubProj(tituloProj, tituloPub);
                                                System.out.println("\nProjeto associado com sucesso.");
                                                break;
                                            } else {
                                                System.out.println("\nPublicações só podem ser associadas a projetos em andamento.");
                                            }
                                        } else {
                                            System.out.println("\nProjeto não encontrado.");
                                            do {
                                                System.out.println("1 - Tentar novamente");
                                                System.out.println("2 - Voltar");
                                                System.out.print("=====> Escolha uma opção: ");
                                                menu = entrada.nextInt();
                                                if (menu != 1 & menu != 2) {
                                                    System.out.println("\nOpção inválida.\n");
                                                }
                                            } while (menu != 1 & menu != 2);
                                        }
                                    } while (menu != 2);
                                    break;

                                case 3:
                                    break;

                                default:
                                    System.out.println("\nOpção inválida.\n");
                            }
                        } while (menu != 3);
                    } else {
                        System.out.println("\nNão existem publicações cadastradas.");
                    }
                    break;

                case 7:     //7 - Relatório do laboratório
                    System.out.println("\n====== Dados Gerais do Laboratório ======");
                    System.out.println("Colaboradores: " + Laboratorio.getListaColaboradores().size());
                    System.out.println("Projetos em elaboração: " + Laboratorio.qtdProjetosElaboracao());
                    System.out.println("Projetos em andamento: " + Laboratorio.qtdProjetosAndamento());
                    System.out.println("Projetos concluídos: " + Laboratorio.qtdProjetosConcluidos());
                    System.out.println("Total de projetos: " + Laboratorio.getListaProjetos().size());
                    System.out.println("Publicações: " + Laboratorio.getListaPublicacoes().size());
                    System.out.println("Orientações: " + Laboratorio.getListaOrientacoes().size());
                    break;

                case 8:     //8 - Encerrar
                    System.out.println("\nSistema encerrado.");
                    break;

                default:
                    System.out.println("\nOpção inválida.\n");
            }
        } while (menu != 8);

    }

    static void cadastrarColaborador(String ocupacao) {
        Scanner entradaString = new Scanner(System.in);
        Colaborador colab;
        Professor prof;
        String nomeColab;
        String email;

        System.out.println("\n====== Cadastrar Colaborador ======");
        System.out.print("Digite o nome: ");
        nomeColab = entradaString.nextLine();
        System.out.print("Digite o email: ");
        email = entradaString.nextLine();

        System.out.println("\nColaborador cadastrado com sucesso.");

        if (ocupacao.equals("Professor")) {
            prof = new Professor(nomeColab, email, ocupacao);
            Laboratorio.adicionarColaborador(prof);
            System.out.println(prof.imprimir());
        } else {
            colab = new Colaborador(nomeColab, email, ocupacao);
            Laboratorio.adicionarColaborador(colab);
            System.out.println(colab.imprimir());
        }
    }

    static void exibirMenu() {     //Menu principal
        System.out.println("\n====== LABORATÓRIO ======");
        System.out.println("1 - Criar projeto");
        System.out.println("2 - Consultar projeto");
        System.out.println("3 - Cadastrar colaborador");
        System.out.println("4 - Consultar colaborador");
        System.out.println("5 - Cadastrar produção acadêmica");
        System.out.println("6 - Consultar publicação");
        System.out.println("7 - Relatório do laboratório");
        System.out.println("8 - Encerrar");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu2() {     //Menu de "Consultar projeto"
        System.out.println("\n====== OPÇÕES DO PROJETO ======");
        System.out.println("1 - Alocar participante");
        System.out.println("2 - Iniciar projeto");
        System.out.println("3 - Concluir projeto");
        System.out.println("4 - Exibir dados do projeto");
        System.out.println("5 - Voltar");
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
        System.out.println("1 - Incluir autores");
        System.out.println("2 - Associar a um projeto");
        System.out.println("3 - Voltar");
        System.out.print("=====> Escolha uma opção: ");
    }
}
