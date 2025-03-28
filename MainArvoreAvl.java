import java.util.Scanner;

public class MainArvoreAvl {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArvoreAvl<Integer> arvore = new ArvoreAvl<>(); // Especificando o tipo Integer

        while (true) {
            System.out.println("Digite um comando (i [valor], r [valor], b [valor], pre, em, pos, sair):");
            String comando = input.nextLine().trim();

            if (comando.startsWith("i")) {
                try {
                    int valor = Integer.parseInt(comando.split(" ")[1]);
                    arvore.insert(valor);
                    System.out.println("Valor " + valor + " inserido com sucesso.");
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Use o formato 'i [valor]'.");
                }
            } else if (comando.startsWith("r")) {
                try {
                    int valor = Integer.parseInt(comando.split(" ")[1]);
                    arvore.remove(valor);
                    System.out.println("Valor " + valor + " removido com sucesso.");
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Use o formato 'r [valor]'.");
                }
            } else if (comando.startsWith("b")) {
                try {
                    int valor = Integer.parseInt(comando.split(" ")[1]);
                    Elemento<Integer> resultado = arvore.busca(valor);
                    if (resultado != null) {
                        System.out.println("Valor " + valor + " encontrado na árvore.");
                    } else {
                        System.out.println("Valor " + valor + " não encontrado na árvore.");
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Use o formato 'b [valor]'.");
                }
            } else if (comando.equals("pre")) {
                System.out.println("Travessia pré-ordem:");
                arvore.preOrdem(arvore.getRaiz());
            } else if (comando.equals("em")) {
                System.out.println("Travessia em-ordem:");
                arvore.emOrdem(arvore.getRaiz());
            } else if (comando.equals("pos")) {
                System.out.println("Travessia pós-ordem:");
                arvore.posOrdem(arvore.getRaiz());
            } else if (comando.equals("sair")) {
                System.out.println("Encerrando o programa.");
                break;
            } else {
                System.out.println("Comando inválido. Tente novamente.");
            }
        }

        input.close();
    }
}
