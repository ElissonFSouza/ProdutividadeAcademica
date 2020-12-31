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
        String nome;
        String ocupacao;

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

                    status = "Em elaboração.";

                    proj = new Projeto(tituloProj, dataInicio, dataFim, agenciaFinanciadora,
                            valorFinanciado, objetivo, descricao, status);

                    Laboratorio.adicionarProjeto(proj);

                    System.out.println("\nProjeto criado com sucesso.");
                    System.out.println(proj.imprimir());
                    break;

                case 2:     //2 - Buscar projeto
                    System.out.println("\nDigite o título do projeto a ser buscado:");
                    tituloProj = entradaString.nextLine();

                    if (Laboratorio.verificarProjeto(tituloProj)) {
                        System.out.println(Laboratorio.pesquisarProjeto(tituloProj));
                    } else {
                        System.out.println("\nProjeto não encontrado.");
                        break;
                    }

                    do {
                        exibirMenu2();
                        menu = entrada.nextInt();

                        switch (menu) {
                            case 1:     //1 - Alocar participantes
                                do {
                                    System.out.println("\nDigite o nome do colaborador:");
                                    nome = entradaString.nextLine();

                                    if (Laboratorio.verificarColaborador(nome)) {
                                        menu = 1;
                                    } else {
                                        menu = 0;
                                    }
                                } while (menu != 1);

                                if (Laboratorio.associar(tituloProj, nome)) {
                                    System.out.println("\nColaborador alocado com sucesso.");
                                } else {
                                    System.out.println("\nInsucesso na alocação.");
                                }
                                break;

                            default:
                                System.out.println("\nOpção inválida.");
                        }
                    } while (menu != 7);
                    break;

                case 3:     //3 - Cadastrar colaborador
                    exibirMenu3();
                    menu = entrada.nextInt();

                    System.out.println("\n====== Cadastrar Colaborador ======");
                    System.out.println("Digite o nome:");

                    switch (menu) {

                        case 1:
                            nome = entradaString.nextLine();
                            ocupacao = "Aluno";

                            colab = new Colaborador(nome, ocupacao);

                            Laboratorio.adicionarColaborador(colab);
                            break;

                        case 2:
                            nome = entradaString.nextLine();
                            ocupacao = "Professor";

                            colab = new Colaborador(nome, ocupacao);

                            Laboratorio.adicionarColaborador(colab);
                            break;

                        case 3:
                            nome = entradaString.nextLine();
                            ocupacao = "Pesquisador";

                            colab = new Colaborador(nome, ocupacao);

                            Laboratorio.adicionarColaborador(colab);
                            break;

                        default:
                            System.out.println("\nOpção inválida.");
                    }

                    System.out.println("\nColaborador cadastrado com sucesso.");
                    break;

                case 4:
                    System.out.println("\nSistema encerrado.");
                    break;

                default:
                    System.out.println("\nOpção inválida.");
            }
        } while (menu != 4);

    }

    static void exibirMenu() {
        System.out.println("\n====== LABORATÓRIO ======");
        System.out.println("1 - Criar projeto"); // Done
        System.out.println("2 - Buscar projeto");
        System.out.println("3 - Cadastrar colaborador"); // Done
        System.out.println("4 - Sair");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu2() {
        System.out.println("\n====== OPÇÕES DO PROJETO ======");
        System.out.println("1 - Alocar participante");
        System.out.println("2 - ");
        System.out.println("3 - ");
        System.out.println("4 - ");
        System.out.println("5 - ");
        System.out.println("6 - ");
        System.out.println("7 - Sair");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu3(){
        System.out.println("\nQuem você deseja cadastrar? ");
        System.out.println("1 - Aluno");
        System.out.println("2 - Professor");
        System.out.println("3 - Pesquisador");
        System.out.println("4 - Sair");
        System.out.print("=====> Escolha uma opção: ");
    }
}
