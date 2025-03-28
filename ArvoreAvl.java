public class ArvoreAvl<Tipo extends Comparable<Tipo>> {
    private Elemento<Tipo> raiz;

    public ArvoreAvl() {
        raiz = null;
    }

    public void insert(Tipo valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser nulo");
        }
        raiz = insertRec(raiz, valor);
    }

    private Elemento<Tipo> insertRec(Elemento<Tipo> node, Tipo valor) {
        if (node == null) {
            return new Elemento<>(valor);
        }

        int compareTo = valor.compareTo(node.getValor());
        if (compareTo < 0) {
            node.setLeft(insertRec(node.getLeft(), valor));
        } else if (compareTo > 0) {
            node.setRight(insertRec(node.getRight(), valor));
        } else {
            throw new IllegalArgumentException("Valor duplicado");
        }

        calcularAltura(node);
        return balancear(node);
    }

    public boolean remove(Tipo valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor nao pode ser nulo");
        }
        raiz = removeRec(raiz, valor);
        return true;
    }

    private Elemento<Tipo> removeRec(Elemento<Tipo> node, Tipo valor) {
        if (node == null) {
            throw new IllegalArgumentException("Valor nao encontrado");
        }

        int compareTo = valor.compareTo(node.getValor());
        if (compareTo < 0) {
            node.setLeft(removeRec(node.getLeft(), valor));
        } else if (compareTo > 0) {
            node.setRight(removeRec(node.getRight(), valor));
        } else {

            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }


            Elemento<Tipo> sucessor = encontrarMin(node.getRight());
            node.setValor(sucessor.getValor());
            node.setRight(removeRec(node.getRight(), sucessor.getValor()));
        }


        calcularAltura(node);
        return balancear(node);
    }

    public Elemento<Tipo> busca(Tipo valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser nulo");
        }
        return buscaRec(raiz, valor);
    }

    private Elemento<Tipo> buscaRec(Elemento<Tipo> node, Tipo valor) {
        if (node == null) {
            return null;
        }

        int compareTo = valor.compareTo(node.getValor());
        if (compareTo < 0) {
            return buscaRec(node.getLeft(), valor);
        } else if (compareTo > 0) {
            return buscaRec(node.getRight(), valor);
        } else {
            return node;
        }
    }

        private Elemento<Tipo> encontrarMin (Elemento < Tipo > node) {
            if (node == null) {
                return null;
            }
            while (node.getLeft() != null) {
                node = node.getLeft();
            }
            return node;
        }


    public void emOrdem(Elemento<Tipo> atual) {
        if (atual != null) {
            emOrdem(atual.getLeft());
            System.out.println(atual.getValor());
            emOrdem(atual.getRight());
        }

    }
    public void preOrdem(Elemento<Tipo> atual) {
        if (atual != null) {
            System.out.println(atual.getValor());
            preOrdem(atual.getLeft());
            preOrdem(atual.getRight());
        }
    }
    public void posOrdem(Elemento<Tipo> atual) {
        if (atual != null) {
            posOrdem(atual.getLeft());
            posOrdem(atual.getRight());
            System.out.println(atual.getValor());
        }
    }



    private void calcularAltura(Elemento<Tipo> node) {
        int alturaLeft = (node.getLeft() != null) ? node.getLeft().getAltura() : -1;
        int alturaRight = (node.getRight() != null) ? node.getRight().getAltura() : -1;
        node.setAltura(1 + Math.max(alturaLeft, alturaRight));
    }

    private int calcularFb(Elemento<Tipo> node) {
        if (node == null) {
            return 0;
        }

        int alturaEsquerda = (node.getLeft() != null) ? node.getLeft().getAltura() : -1;
        int alturaDireita = (node.getRight() != null) ? node.getRight().getAltura() : -1;

        return alturaEsquerda - alturaDireita;
    }

    private Elemento<Tipo> balancear(Elemento<Tipo> node) {
        int fb = calcularFb(node);

        if (fb > 1) {
            if (calcularFb(node.getLeft()) >= 0) {
                return rotacaoDireita(node); // Caso LL
            } else {
                return rotacaoLeftRight(node); // Caso LR
            }
        } else if (fb < -1) {
            if (calcularFb(node.getRight()) <= 0) {
                return rotacaoEsquerda(node); // Caso RR
            } else {
                return rotacaoRightLeft(node); // Caso RL
            }
        }

        return node;
    }

    private Elemento<Tipo> rotacaoDireita(Elemento<Tipo> atual) {
        Elemento<Tipo> novaRaiz = atual.getLeft();
        atual.setLeft(novaRaiz.getRight());
        novaRaiz.setRight(atual);

        calcularAltura(atual);
        calcularAltura(novaRaiz);

        return novaRaiz;
    }

    private Elemento<Tipo> rotacaoEsquerda(Elemento<Tipo> atual) {
        Elemento<Tipo> novaRaiz = atual.getRight();
        atual.setRight(novaRaiz.getLeft());
        novaRaiz.setLeft(atual);

        calcularAltura(atual);
        calcularAltura(novaRaiz);

        return novaRaiz;
    }

    private Elemento<Tipo> rotacaoRightLeft(Elemento<Tipo> atual) {
        atual.setRight(rotacaoDireita(atual.getRight()));
        return rotacaoEsquerda(atual);
    }

    private Elemento<Tipo> rotacaoLeftRight(Elemento<Tipo> atual) {
        atual.setLeft(rotacaoEsquerda(atual.getLeft()));
        return rotacaoDireita(atual);
    }

    public Elemento<Tipo> getRaiz() {
        return raiz;
    }

    public void setRaiz(Elemento<Tipo> raiz) {
        this.raiz = raiz;
    }
}





