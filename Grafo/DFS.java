package Grafo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
// Uma classe para armazenar uma aresta do gráfico
class Edge
{
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
    // Função para realizar a travessia DFS no gráfico em um gráfico
    public static void DFS(Graph graph, int v, boolean[] discovered)
    {
        // marca o nó atual como descoberto
        discovered[v] = true;
 
        // imprime o nó atual
        System.out.print(v + " ");
 
        // faz para cada aresta (v, u)
        for (int u: graph.adjList.get(v))
        {
            // se `u` ainda não foi descoberto
            if (!discovered[u]) {
                DFS(graph, u, discovered);
            }
        }
    }
 
    public static void main(String[] args)
    {
        // Lista de arestas do gráfico conforme o diagrama acima
        List<Edge> edges = Arrays.asList(
                // Observe que o nó 0 está desconectado
                new Edge(1, 2), new Edge(1, 7), new Edge(1, 8), new Edge(2, 3),
                new Edge(2, 6), new Edge(3, 4), new Edge(3, 5), new Edge(8, 9),
                new Edge(8, 12), new Edge(9, 10), new Edge(9, 11)
            );
 
        // número total de nós no gráfico (rotulado de 0 a 12)
        int n = 13;
 
        // constrói um gráfico a partir das arestas fornecidas
        Graph graph = new Graph(edges, n);
 
        // para acompanhar se um vértice é descoberto ou não
        boolean[] discovered = new boolean[n];
 
        // Realiza a travessia DFS de todos os nós não descobertos para
        // cobre todos os componentes conectados de um grafo
        for (int i = 0; i < n; i++)
        {
            if (!discovered[i]) {
                DFS(graph, i, discovered);
            }
        }
    }
}