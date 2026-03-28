#include <string>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> tree[18];
vector<int> infoGlobal;
int ans = 0;
int n;

void dfs(int sheep, int wolf, vector<int>& candidates, vector<bool>& visited) {
    ans = max(ans, sheep);

    for (int i = 0; i < (int)candidates.size(); ++i) {
        int node = candidates[i];
        //늑대,양 수 업데이트
        int nsheep = sheep;
        int nwolf = wolf;
        if (infoGlobal[node] == 0) nsheep++;
        else nwolf++;

        if (nwolf >= nsheep) continue;
        
        vector<int> nextCandidates = candidates;
        nextCandidates.erase(nextCandidates.begin() + i);
        for (int c : tree[node]) {
            if (!visited[c]) nextCandidates.push_back(c);
        }

        visited[node] = true;
        dfs(nsheep, nwolf, nextCandidates, visited);
        visited[node] = false;
    }
}

int solution(vector<int> info, vector<vector<int>> edges) {
    infoGlobal = info;
    n = info.size();
    ans = 0;
    for (int i = 0; i < 18; ++i) tree[i].clear();

    for (auto &e : edges) {
        int parent = e[0], child = e[1];
        tree[parent].push_back(child);
    }

    //초기
    vector<bool> visited(n, false);
    visited[0] = true;
    vector<int> candidates;
    for (int c : tree[0]) candidates.push_back(c);

    //시작할 때 양 1, 늑대 0
    dfs(1, 0, candidates, visited);

    return ans;
}
