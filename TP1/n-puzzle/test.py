

truc = load_puzzle("./test/npuzzle_4x4_len9_0.txt")
print(im.heuristic(truc))
print(im.solve_bfs(truc))