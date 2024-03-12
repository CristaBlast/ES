import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class CampoMinado {
    private boolean[][] minas;
    public static final int VAZIO = 0;
    /* de 1 a 8 são o número de minas à volta */
    public static final int TAPADO = 9;
    public static final int DUVIDA = 10;
    public static final int MARCADO = 11;
    public static final int REBENTADO = 12;
    private int[][] estado;
    private int nrLinhas; // ou largura
    private int nrColunas; // ou altura
    private int nrMinas;
    private boolean jogoTerminado;
    private boolean jogadorDerrotado;
    private boolean primeiraJogada;
    private long instanteInicioJogo;
    private long duracaoJogo;

    public CampoMinado(int nrLinhas, int nrColunas, int nrMinas) {
        this.minas = new boolean[nrLinhas][nrColunas]; // Valores começam a false
        this.estado = new int[nrLinhas][nrColunas];  // Valores começam a 0
        this.nrLinhas = nrLinhas;
        this.nrColunas = nrColunas;
        this.nrMinas = nrMinas;
        for (var x = 0; x < nrLinhas; ++x) {
            for (var y = 0; y < nrColunas; ++y) {
                estado[x][y] = TAPADO;
            }
        }
        this.primeiraJogada=true;
        this.jogoTerminado=false;
        this.jogadorDerrotado=false;
    }

    public CampoMinado() {

    }

    public int getEstadoQuadricula(int x, int y) {
        return estado[x][y];
    }

    public boolean hasMina(int x, int y) {
        return minas[x][y];
    }

    public int getNrLinhas() {
        return nrLinhas;
    }

    public void setNrLinhas(int nrLinhas) {
        this.nrLinhas = nrLinhas;
    }

    public int getNrColunas() {
        return nrColunas;
    }

    public void setNrColunas(int nrColunas) {
        this.nrColunas = nrColunas;
    }

    public void revelarQuadricula(int x, int y) {
        if (jogoTerminado || estado[x][y] < TAPADO) {
            return;
        }
        if (primeiraJogada) {
            primeiraJogada = false;
            colocarMinas(x, y);
            instanteInicioJogo=currentTimeMillis();
            duracaoJogo = System.currentTimeMillis() - instanteInicioJogo;
        }
        else {
            if(contarMinasVizinhas(x, y)==0)
            {
                estado[x][y]=VAZIO;
                revelarQuadriculasVizinhas(x,y);
            }
            else
            {
                estado[x][y]=contarMinasVizinhas(x, y);
            }
            if (isVitoria()) {
                jogoTerminado = true;
                jogadorDerrotado = false;
                duracaoJogo = System.currentTimeMillis() - instanteInicioJogo;
            }
        }
        if(minas[x][y])
        {
            estado[x][y]=REBENTADO;
            jogoTerminado=true;
            jogadorDerrotado=true;
        }
    }

    private boolean isVitoria() {
        for (int i = 0; i < nrLinhas; ++i) {
            for (var j = 0 ; j < nrColunas; ++j) {
                if (!minas[i][j] && estado[i][j] >= TAPADO) {
                    return false;
                }
            }
        }
        return true;
    }

    private void colocarMinas(int exceptoX, int exceptoY) {
        var aleatorio = new Random();
        var x = 0;
        var y = 0;
        for (var i = 0; i < nrMinas; ++i) {
            do {
                x = aleatorio.nextInt(nrLinhas);
                y = aleatorio.nextInt(nrColunas);
            } while (minas[x][y] || (x == exceptoX && y == exceptoY));
            minas[x][y] = true;
        }
    }

    private int contarMinasVizinhas(int x, int y) {
        var numMinasVizinhas = 0;
        for (var i = Math.max(0, x - 1); i < Math.min(nrLinhas, x + 2); ++i) {
            for (var j = Math.max(0, y - 1); j < Math.min(nrColunas, y + 2); ++j)
            {
                if (minas[i][j]) {
                    ++numMinasVizinhas;
                }
            }
        }
        return numMinasVizinhas;
    }

    private void marcarComoTendoMina(int x,int y)
    {
        estado[x][y]=MARCADO;
    }

    private void marcarComoSuspeita(int x,int y)
    {
        estado[x][y]=DUVIDA;
    }

    private void desmarcarQuadricula(int x,int y)
    {
        estado[x][y]=TAPADO;
    }

    public long getDuracaoJogo() {
        if (primeiraJogada) {
            return 0;
        }
        if (!jogoTerminado) {
            return System.currentTimeMillis() - instanteInicioJogo;
        }

        return duracaoJogo;
    }

    public boolean isJogoTerminado()
    {
        return jogoTerminado;
    }

    public boolean isJogadorDerrotado()
    {

        return jogadorDerrotado;
    }

    public void  revelarQuadriculasVizinhas(int x,int y)
    {
        //TODO crashes the program
        int a;
        for (a=x; a <= nrLinhas; a++) {
            if(contarMinasVizinhas(a, y)==0)
            {
                estado[a][y]=VAZIO;
            } else if (contarMinasVizinhas(x,y)!=REBENTADO) { //TODO only once
                estado[a][y]=contarMinasVizinhas(a, y);

            }
            for (;a > x; a--) {
                if(contarMinasVizinhas(a, y)==0)
                {
                    estado[a][y]=VAZIO;
                } else if (contarMinasVizinhas(x,y)!=REBENTADO) { //TODO only once
                    estado[a][y]=contarMinasVizinhas(a, y);

                }
            }
        }
        /*for (int a = x; a > nrLinhas; a--) {
            //for (int b = y; b > nrColunas; b--) {
                if(contarMinasVizinhas(a, y)==0)
                {
                    estado[a][y]=VAZIO;
                }
                else if (contarMinasVizinhas(x,y)!=REBENTADO) {
                    revelarQuadricula(x, y);

                }
            //}
        }*/
    }


}
