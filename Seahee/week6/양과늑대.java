/*
dfs 한 번 호출 시 O(N^2)
dfs 호출 수 O(2^N) 
=> O(N^2 x 2^N)
*/

import java.util.ArrayList;
import java.util.List;

class Solution {

    private List<Integer>[] children; // 각 노드의 자식 리스트
    private int maxSheep = 0;

    public int solution(int[] info, int[][] edges) {
        int n = info.length;

        // 자식 리스트 초기화
        children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        // 트리 구성 (부모 -> 자식)
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            children[parent].add(child);
        }

        int sheep = 1;
        int wolf = 0;

        // 처음에 갈 수 있는 후보 노드들 (루트의 자식들)
        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.addAll(children[0]);

        dfs(info, sheep, wolf, nextNodes);

        return maxSheep;
    }

    private void dfs(int[] info, int sheep, int wolf, List<Integer> nextNodes) {
        // 현재 상태에서 모은 양 수로 최대값 갱신
        maxSheep = Math.max(maxSheep, sheep);

        // 다음에 갈 수 있는 후보 노드들 중 하나를 선택해 보면서 완전 탐색
        for (int i = 0; i < nextNodes.size(); i++) {
            int node = nextNodes.get(i);

            int nextSheep = sheep; //다음 dfs에서의 양, 늑대 수
            int nextWolf = wolf;

            // 현재 노드가 양인지 늑대인지에 따라 카운트 증가
            if (info[node] == 0) {
                nextSheep++;
            } else {
                nextWolf++;
            }

            // 늑대 수가 양 수 이상이면 이 경로는 더 진행 불가
            if (nextWolf >= nextSheep) {
                continue;
            }

            // 다음 후보 리스트 생성
            // - 현재 선택한 노드는 후보에서 제거
            // - 현재 노드의 자식들을 새 후보에 추가
            List<Integer> newNextNodes = new ArrayList<>(nextNodes);
            newNextNodes.remove(i); // 현재 방문한 노드는 후보에서 제거
            newNextNodes.addAll(children[node]); // 자식들 추가

            // 다음 상태로 DFS
            dfs(info, nextSheep, nextWolf, newNextNodes);
        }
    }
}
