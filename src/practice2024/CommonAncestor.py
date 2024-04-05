
import random


class Node:

    NextNodeId = 0

    def __init__(self, val: int, parent: 'Node' = None):
        self.parent = parent
        self.val = val
        self.id = Node.NextNodeId
        Node.NextNodeId += 1
    

def gen(parent, all_nodes: list[Node], depth: int = 5):
    
    print(f"Node #{parent.id} = {parent.val}, parent is #{'None' if parent.parent is None else parent.parent.id }")

    if depth == 0:
        return

    childrenCount = random.randint(1, 3) # TODO extract magic numbers
    for i in range(childrenCount):
        child = Node(random.randint(1, 100), parent)
        all_nodes.append(child)
        gen(child, all_nodes, depth - 1)
        

tree = Node(0)
nodes = []
gen(tree, nodes, 5)

print("---")

def find_common_asc(node1: Node, node2: Node) -> Node:

    node1_path = set()
    it_node = node1
    while it_node is not None:
        node1_path.add(it_node)
        it_node = it_node.parent

    it_node = node2
    while it_node is not None:
        if it_node in node1_path:
            return it_node
        it_node = it_node.parent
        
    return None


node1 = nodes[-1]
node2 = nodes[-5]
common_asc = find_common_asc(node1, node2)
print(f"Common asc of nodes #{node1.id} and #{node2.id} is #{common_asc.id}")




