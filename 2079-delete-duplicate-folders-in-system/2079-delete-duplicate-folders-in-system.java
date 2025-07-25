class Solution {
    static class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        String key;
        boolean isDeleted = false;
    }

    Map<String, List<TrieNode>> serialMap = new HashMap<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TrieNode root = new TrieNode();

        for (List<String> path : paths) {
            TrieNode curr = root;
            for (String folder : path) {
                curr.children.putIfAbsent(folder, new TrieNode());
                curr = curr.children.get(folder);
                curr.key = folder;
            }
        }

        serialize(root);

        for (List<TrieNode> nodes : serialMap.values()) {
            if (nodes.size() > 1) {
                for (TrieNode node : nodes) {
                    node.isDeleted = true;
                }
            }
        }

 
        List<List<String>> res = new ArrayList<>();
        dfs(root, new ArrayList<>(), res);
        return res;
    }

    private String serialize(TrieNode node) {
        if (node.children.isEmpty()) return "";

        List<String> subs = new ArrayList<>();
        for (String key : node.children.keySet()) {
            TrieNode child = node.children.get(key);
            subs.add(key + "(" + serialize(child) + ")");
        }
        Collections.sort(subs);
        String serial = String.join("", subs);
        serialMap.computeIfAbsent(serial, k -> new ArrayList<>()).add(node);
        return serial;
    }

    private void dfs(TrieNode node, List<String> path, List<List<String>> res) {
        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            TrieNode child = entry.getValue();
            if (child.isDeleted) continue;
            path.add(entry.getKey());
            res.add(new ArrayList<>(path));
            dfs(child, path, res);
            path.remove(path.size() - 1);
        }
    }
}
