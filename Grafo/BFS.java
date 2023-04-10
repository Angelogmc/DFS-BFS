package Grafo;
import java.util.*;
 
// Uma classe para armazenar uma aresta do gráfico
class Edge {
    int source, dest;
 
    public Edge(int source, int dest)
    {
        this.source = source;
        this.dest = dest;
    }
}

// Uma classe para representar um objeto gráfico
class Graph
{
    // Uma lista de listas para representar uma lista de adjacências
    List<List<Integer>> adjList = null;
 
    // Construtor
    Graph(List<Edge> edges, int n)
    {
        adjList = new ArrayList<>();
 
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
 
        // adiciona arestas ao grafo não direcionado
        for (Edge edge: edges)
        {
            int src = edge.source;
            int dest = edge.dest;
 
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
    }
}
 
class Main
{
    // Efetua BFS no gráfico a partir do vértice `v`
    public static void BFS(Graph graph, int v, boolean[] discovered)
    {
        // cria uma queue para fazer BFS
        Queue<Integer> q = new ArrayDeque<>();
 
        // marca o vértice fonte como descoberto
        discovered[v] = true;
 
        // enfileira o vértice de origem
        q.add(v);
 
        // faz um loop até que a queue esteja vazia
        while (!q.isEmpty())
        {
            // desenfileira o nó frontal e o imprime
            v = q.poll();
            System.out.print(v + " ");
 
            // faz para cada aresta (v, u)
            for (int u: graph.adjList.get(v))
            {
                if (!discovered[u])
                {
                    // marca como descoberto e enfileira
                    discovered[u] = true;
                    q.add(u);
                }
            }
        }
    }
 
    public static void main(String[] args)
    {
        // Lista de arestas do gráfico conforme o diagrama acima
        List<Edge> edges = Arrays.asList(
                new Edge(1, 2), new Edge(1, 3), new Edge(1, 4), new Edge(2, 5),
                new Edge(2, 6), new Edge(5, 9), new Edge(5, 10), new Edge(4, 7),
                new Edge(4, 8), new Edge(7, 11), new Edge(7, 12)
                // vértice 0, 13 e 14 são nós únicos
        );
 
        // número total de nós no gráfico (rotulado de 0 a 14)
        int n = 15;
 
        // constrói um gráfico a partir das arestas fornecidas
        Graph graph = new Graph(edges, n);
 
        // para acompanhar se um vértice é descoberto ou não
        boolean[] discovered = new boolean[n];
 
        // Realiza a travessia BFS de todos os nós não descobertos para
        // cobre todos os componentes conectados de um grafo
        for (int i = 0; i < n; i++)
        {
            if (discovered[i] == false)
            {
                // inicia a travessia do BFS a partir do vértice `i`
                BFS(graph, i, discovered);
            }
        }
    }
}