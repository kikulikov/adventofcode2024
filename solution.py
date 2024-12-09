def is_valid_order(pages, rules):
    # Create graph of dependencies
    graph = {}
    for rule in rules:
        before, after = map(int, rule.split('|'))
        graph.setdefault(before, set())
        graph.setdefault(after, set())
        graph[after].add(before)

    # Check if each page is correctly positioned
    for i in range(len(pages)):
        curr_page = pages[i]

        # Check if current page depends on pages that come after it
        for j in range(i+1, len(pages)):
            if curr_page in graph.get(pages[j], set()):
                return False

    return True

def solve():
    # Read input file
    with open('in.txt', 'r') as f:
        content = f.read().strip().split('\n\n')

    # Parse rules and updates
    rules = content[0].split('\n')
    updates = [list(map(int, line.split(','))) for line in content[1].split('\n')]

    # Calculate middle page numbers of valid updates
    middle_pages_sum = sum(
        update[len(update)//2]
        for update in updates
        if is_valid_order(update, rules)
    )

    print(middle_pages_sum)

solve()