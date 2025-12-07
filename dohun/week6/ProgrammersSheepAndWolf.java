import java.util.*;

class ProgrammersSheepAndWolf {
    // 트리 구조를 저장할 인접 리스트
    List<Integer>[] tree;

    // 각 노드에 있는 동물 정보 (0 = 양, 1 = 늑대)
    int[] info;

    // 최대로 모을 수 있는 양의 수 (DFS 과정에서 계속 갱신)
    int answer = 0;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        int n = info.length;

        // 트리 초기화 (각 노드의 자식 리스트 생성)
        tree = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        // 부모 → 자식 간선 연결
        for(int[] e : edges) {
            tree[e[0]].add(e[1]);
        }

        // 방문 가능한 노드 목록 (처음에는 루트(0)만 가능)
        List<Integer> next = new ArrayList<>();
        next.add(0);

        // DFS 시작: 현재 양=0, 늑대=0, 방문 가능한 노드= {0}
        dfs(0, 0, next);

        return answer;
    }


    // DFS 탐색 함수
    // sheep     현재까지 모은 양 수
    // wolf      현재까지 만난 늑대 수
    // possible  현재 상태에서 앞으로 "방문할 수 있는 모든 노드"
    void dfs(int sheep, int wolf, List<Integer> possible) {

        // 매 탐색마다 현재까지 모은 양의 수로 최대값을 갱신
        answer = Math.max(answer, sheep);

        // 현재 방문 가능한 모든 노드를 하나씩 선택해 이동해 본다
        for(int i = 0; i < possible.size(); i++) {

            int node = possible.get(i); // 이번에 방문할 노드

            int newSheep = sheep;
            int newWolf = wolf;

            // 선택한 노드의 동물 종류에 따라 양 또는 늑대 증가
            if(info[node] == 0) {
                newSheep++;  // 양
            } else {
                newWolf++;   // 늑대
            }

            // 규칙: 늑대가 양 이상이면 양이 모두 잡아먹혀서 해당 경로는 중단
            if(newWolf >= newSheep) {
                continue;
            }

            // 새로운 possible 리스트 만들기
            // 현재 방문한 노드를 제외하고 그 노드의 자식들을 추가
            // 새 리스트가 필요한 이유
            // DFS 특성상 각 분기마다 possible 목록이 달라야 하기 때문
            // 하나의 리스트를 공용으로 쓰면 다른 경로 탐색에 영향을 끼침
            List<Integer> newPossible = new ArrayList<>(possible);
            newPossible.remove(i); // 이번에 방문한 노드는 제거

            // 방문한 노드의 자식들은 앞으로 방문 가능한 후보가 됨
            for(int child : tree[node]) {
                newPossible.add(child);
            }

             // DFS 재귀 호출
             // 상태를 업데이트한 뒤 다음 가능한 모든 노드들로 다시 탐색
             // 여기서 중요한 점:
             // 현재 노드의 자식뿐만 아니라 기존에 방문하지 않은 다른 후보도 계속 남아있음
             // 즉 트리를 직선으로 탐색하는 것이 아니라 '방문 가능한 상태 집합'을 들고 다니는 DFS라고 생각하면 됨
            dfs(newSheep, newWolf, newPossible);
        }
    }
}
