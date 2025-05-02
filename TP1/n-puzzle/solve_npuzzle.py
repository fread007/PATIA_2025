
from npuzzle import (Solution,
                     State,
                     Move,
                     UP, 
                     DOWN, 
                     LEFT, 
                     RIGHT,
                     get_children,
                     is_goal,
                     is_solution,
                     load_puzzle,
                     to_string)
from node import Node
from typing import Literal, List
import argparse
import math
import time

BFS = 'bfs'
DFS = 'dfs'
ASTAR = 'astar'

Algorithm = Literal['bfs', 'dfs', 'astar']

def solve_bfs(open : List[Node]) -> Solution:
    '''Solve the puzzle using the BFS algorithm'''
    dimension = int(math.sqrt(len(open[0].get_state())))
    moves = [UP, DOWN, LEFT, RIGHT]
    while open:
        node = open.pop(0)
        if is_goal(node.get_state()):
            return node.get_path()
        puzzle = node.get_state()
        k = node.cost
        #print('k = ', k)
        children = get_children(puzzle, moves, dimension)
        for child in children:
            n = Node(state = child[0], move = child[1], parent = node, cost = k + 1)
            open.append(n)
    return []

def solve_dfs(open : List[Node]) -> Solution:
    '''Solve the puzzle using the DFS algorithm'''
    close : List[State] = []
    dimension = int(math.sqrt(len(open[0].get_state())))
    moves = [UP, DOWN, LEFT, RIGHT]
    while open:
        node = open.pop(0)
        if is_goal(node.get_state()):
            return node.get_path()
        puzzle = node.get_state()
        if(puzzle in close):
            continue
        close.append(puzzle)
        k = node.cost
        children = get_children(puzzle, moves, dimension)
        for child in children:
            n = Node(state = child[0], move = child[1], parent = node, cost = k + 1)
            open.insert(0, n)
    return []

def solve_astar(open : List[Node], close : List[State]) -> Solution:
    '''Solve the puzzle using the A* algorithm'''
    dimension = int(math.sqrt(len(open[0].get_state())))
    moves = [UP, DOWN, LEFT, RIGHT]
    while open:
        node = open.pop(0)
        if is_goal(node.get_state()):
            return node.get_path()
        puzzle = node.get_state()
        k = node.cost
        children = get_children(puzzle, moves, dimension)
        for child in children:
            if(child[0] in close):
                continue
            close.append(child[0])
            n = Node(state = child[0], move = child[1], parent = node, cost = k + 1)
            n.set_heuristic(heuristic(n))
            open.append(n)
            open.sort(key = lambda x : x.get_heuristic() + n.cost)
        
    return []

def heuristic(node : Node) -> int:
    '''Calculate the heuristic value of the puzzle'''
    res : int = 0
    puzzle = node.get_state()
    dimension = int(math.sqrt(len(puzzle)))
    i : int
    j : int
    for i in range(dimension) :
        for j in range(dimension):
            if puzzle[dimension*i + j] != 0 :
                res += abs(i - (puzzle[dimension*i + j] // dimension)) + abs(j - (puzzle[dimension*i + j] % dimension))
    return res

def main():
    parser = argparse.ArgumentParser(description='Load an n-puzzle and solve it.')
    parser.add_argument('filename', type=str, help='File name of the puzzle')
    parser.add_argument('-a', '--algo', type=str, choices=['bfs', 'dfs', 'astar'], required=True, help='Algorithm to solve the puzzle')
    parser.add_argument('-v', '--verbose', action='store_true', help='Increase output verbosity')
    
    args = parser.parse_args()
    
    puzzle = load_puzzle(args.filename)
    
    if args.verbose:
        print('Puzzle:\n')
        print(to_string(puzzle))
    
    if not is_goal(puzzle):   
         
        root = Node(state = puzzle, move = None)
        open = [root]
        
        if args.algo == BFS:
            print('BFS\n')
            start_time = time.time()
            solution = solve_bfs(open)
            duration = time.time() - start_time
            if solution:
                print('Solution:', solution)
                print('Valid solution:', is_solution(puzzle, solution))
                print('Duration:', duration)
            else:
                print('No solution')
        elif args.algo == DFS:
            print('DFS')
            start_time = time.time()
            solution = solve_dfs(open)
            duration = time.time() - start_time
            if solution:
                print('Solution:', solution)
                print('Valid solution:', is_solution(puzzle, solution))
                print('Duration:', duration)
            else:
                print('No solution')
        elif args.algo == ASTAR:
            print('A*')
            start_time = time.time()
            solution = solve_astar(open,[])
            duration = time.time() - start_time
            if solution:
                print('Solution:', solution)
                print('Valid solution:', is_solution(puzzle, solution))
                print('Duration:', duration)
            else:
                print('No solution')
    else:
        print('Puzzle is already solved')
    
if __name__ == '__main__':
    main()